package serveur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import socketPerso.IOOStream;
import socketPerso.IOOStreamLocal;
import socketPerso.InfoEntrante;
import socketPerso.InfoSortante;
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
		System.out.println("Client local: " + ioos.readObject());
		AppliServeur as = new AppliServeur();
		as.sock.add(ioos);
		
		//A partir de là, à modifier pour le traitement du GameEngine
		
		InfoSortante is = (InfoSortante) ioos.readObject();
		{
			InfoEntrante ie = new InfoEntrante(is.toString(), is.getEmplacement());
			try {
				switch(is.getEmplacement()) {
					case 0: 
						as.sock.get(0).writeObject(ie);
						break;
					case 1:
						as.sock.get(1).writeObject(ie);
						break;
					case 2:
						as.sock.get(2).writeObject(ie);
						break;
					default:
						for(IOOStream i : as.sock)
							i.writeObject(ie);
						break;
				}
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
		}
	}
}
