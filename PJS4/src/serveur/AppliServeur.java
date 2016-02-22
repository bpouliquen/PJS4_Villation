package serveur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import socketPerso.IOOStream;
import socketPerso.IOOStreamLocal;
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
		new ServeurEcoute(PORT, this.sock, nbJoueurs);
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
		IOOStreamLocal ioos=new IOOStreamLocal();
		Client c=new Client(ioos);
		new Thread(c).start();
		ioos.writeObject(new Information("Connexion au client local..."));
		System.out.println(ioos.readObject());
		System.out.println(ioos.readObject());
	}
}
