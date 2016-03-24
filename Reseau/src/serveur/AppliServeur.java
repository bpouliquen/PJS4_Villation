package serveur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import socketPerso.IOOStream;
import socketPerso.IOOStreamLocal;
import socketPerso.InfoEntrante;
import socketPerso.Information;

/**
 * Classe initialisant et instanciant les paramètres pour ServeurEcoute
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class AppliServeur {
	private static int PORT = 2000;
	private List<IOOStream> sock;
	private final int nbJoueurs = 2;

	/**
	 * Constructeur
	 */
	public AppliServeur() {
		sock = new LinkedList<IOOStream>();
		ServeurEcoute se = new ServeurEcoute(PORT, this.sock, nbJoueurs);
		new Thread(se).start();
	}

	/**
	 * Procédure transférant un packet à tous les clients
	 * 
	 * @param paquet
	 *            Information
	 */
	public void broadcast(Information paquet) {
		for (IOOStream s : sock) {
			try {
				s.writeObject(paquet);
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
		}
	}

	/**
	 * Procédure statique exécutable
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		IOOStreamLocal ioos = new IOOStreamLocal();
		ClientLocal c = new ClientLocal(ioos);
		new Thread(c).start();
		ioos.writeObject(new Information("Connexion du client local..."));
		System.out.println("[Client local]: " + ioos.readObject());
		AppliServeur as = new AppliServeur();
		as.sock.add(ioos);

		// Interactions avec les clients
		Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine();
		try {
			while (!msg.equals("end")) {
				switch (msg) {
				case ("0"):
					as.sock.get(0).writeObject(new InfoEntrante(msg, 0));
					System.out.println("[Client]: " + as.sock.get(0).readObject());
					break;
				case ("1"):
					as.sock.get(1).writeObject(new InfoEntrante(msg, 1));
					System.out.println("[Client]: " + as.sock.get(1).readObject());
					break;
				case ("2"):
					as.sock.get(2).writeObject(new InfoEntrante(msg, 2));
					System.out.println("[Client]: " + as.sock.get(2).readObject());
					break;
				default:
					as.toAll(new InfoEntrante(msg, -1));
					break;
				}
				msg = sc.nextLine();
			}
			
			//Déconnexion du serveur
			as.toAll(new InfoEntrante("end", -1));
			sc.close();
			System.out.println("Fermeture du serveur.");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	private void toAll(InfoEntrante ie) {
		for (IOOStream i : sock) {
			try {
				i.writeObject(ie);
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
		}
	}
}
