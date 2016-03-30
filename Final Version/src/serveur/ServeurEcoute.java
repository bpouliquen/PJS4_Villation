package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import launcher.*;
import ressources.*;

/**
 * Classe instanciant Serveur écoute permettant de recevoir la connexion des
 * autres joueurs
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class ServeurEcoute implements Runnable {

	private ServerSocket serveur;
	private Serveur se;
	private InterfaceRejoindrePartie ipartie;
	private String nomPartie;

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
	public ServeurEcoute(int port, Serveur se, InterfaceRejoindrePartie ipartie, String nomPartie) {
		this.se = se;
		this.ipartie = ipartie;
		this.nomPartie = nomPartie;
		try {
			serveur = new ServerSocket(port);
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Procédure runnable attendant la connexion des joueurs et créant leurs
	 * entrées et sorties
	 */
	public void run() {
		try {
			for (int i = 1; i <= se.getNbJoueurs() - 1; i++) {
				System.out.println("En attente de " + (se.getNbJoueurs() - i) + " joueurs...");
				Socket temp = serveur.accept();
				IOOStreamReseau ioos = new IOOStreamReseau(temp);
				ioos.writeObject(new Information(this.nomPartie));
				System.out.println("[Client]: " + ioos.readObject());
				se.getJoueurs().add(new Joueur("Joueur " + (i + 1), true));
				ioos.writeObject(se.getJoueurs());
				this.ipartie.remplirListeJoueur(se.getJoueurs());
				se.broadcast(new Information("up"));
				System.out.println(se.getJoueurs().size());
				se.broadcast(se.getJoueurs());
				se.getSockets().add(ioos);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Tous joueurs connectés.");
	}
}
