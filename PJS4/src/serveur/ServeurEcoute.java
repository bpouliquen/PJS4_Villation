package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import socketPerso.*;

/**
 * Classe instanciant Serveur �coute permettant de recevoir la connexion des
 * autres joueurs
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class ServeurEcoute implements Runnable {

	private ServerSocket serveur;
	private List<IOOStream> sock;
	private int attenteJoueurs;

	/**
	 * Constructeur
	 * 
	 * @param port
	 *            int
	 * @param sock
	 *            List IOOStream
	 * @param nbJoueurs
	 *            int
	 */
	public ServeurEcoute(int port, List<IOOStream> sock, int nbJoueurs) {
		this.sock = sock;
		this.attenteJoueurs = nbJoueurs;
		try {
			serveur = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Proc�dure runnable attendant la connexion des joueurs et cr�ant leurs
	 * entr�es et sorties
	 */
	public void run() {
		for (int i = this.attenteJoueurs; i > 0; i--) {
			try {
				System.out.println("En attente de " + i + " joueurs...");
				Socket temp = serveur.accept();
				IOOStreamReseau ioos = new IOOStreamReseau(temp);
				ioos.writeObject(new Information("Connect� au serveur."));
				System.out.println("Client: " + ioos.readObject());
				sock.add(ioos);

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Tous joueurs connect�s.");
	}
}
