package serveur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ressources.IOOStream;
import ressources.IOOStreamLocal;
import ressources.InfoEntrante;
import ressources.Information;

/**
 * Classe initialisant et instanciant les paramètres pour ServeurEcoute
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class Serveur implements Runnable {
	private List<IOOStream> sock;
	private final int nbJoueurs = 2;

	/**
	 * Constructeur
	 */
	public Serveur(int port) {
		IOOStreamLocal ioos = new IOOStreamLocal();
		new ClientLocal(ioos);
		ioos.writeObject(new Information("Connexion du client local..."));
		System.out.println("[Client local]: " + ioos.readObject());
		sock = new LinkedList<IOOStream>();
		ServeurEcoute se = new ServeurEcoute(port, this.sock, nbJoueurs);
		sock.add(ioos);
		new Thread(se).start();
		new Thread(this).start();
	}

	/**
	 * Procédure statique exécutable
	 * 
	 * @param args
	 *            String[]
	 */
	public void run() {
		// Interactions avec les clients
		Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine();
		try {
			while (!msg.equals("end")) {
				switch (msg) {
				case ("0"):
					sock.get(0).writeObject(new InfoEntrante(msg, 0));
					System.out.println("[Client]: " + sock.get(0).readObject());
					break;
				case ("1"):
					sock.get(1).writeObject(new InfoEntrante(msg, 1));
					System.out.println("[Client]: " + sock.get(1).readObject());
					break;
				case ("2"):
					sock.get(2).writeObject(new InfoEntrante(msg, 2));
					System.out.println("[Client]: " + sock.get(2).readObject());
					break;
				default:
					toAll(new InfoEntrante(msg, -1));
					break;
				}
				msg = sc.nextLine();
			}
			
			//Déconnexion du serveur
			toAll(new InfoEntrante("end", -1));
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
