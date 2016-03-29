package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import launcher.InterfaceRejoindrePartie;
import launcher.Joueur;
import ressources.IOOStream;
import ressources.IOOStreamReseau;
import ressources.Information;

/**
 * Classe instanciant Serveur écoute permettant de recevoir la connexion des
 * autres joueurs
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class ServeurEcoute {

	private ServerSocket serveur;
	private List<Joueur> joueurs;
	private int attenteJoueurs;
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
	public ServeurEcoute(int port, List<Joueur> joueurs, int nbJoueurs, InterfaceRejoindrePartie ipartie, String nomPartie) {
		this.joueurs = joueurs;
		this.attenteJoueurs = nbJoueurs;
		this.ipartie = ipartie;
		this.nomPartie = nomPartie;
		try {
			serveur = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ecouter();
	}

	/**
	 * Procédure runnable attendant la connexion des joueurs et créant leurs
	 * entrées et sorties
	 */
	public void ecouter() {
		for (int i = 1; i <= this.attenteJoueurs; i++) {
			try {
				System.out.println("En attente de " + i + " joueurs...");
				Socket temp = serveur.accept();
				IOOStreamReseau ioos = new IOOStreamReseau(temp);
				ioos.writeObject(new Information(this.nomPartie));
				System.out.println("[Client]: " + ioos.readObject());
				joueurs.add(new Joueur("Joueur "+ (i+1), ioos, false));
				this.ipartie.remplirListeJoueur(this.joueurs);
				

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Tous joueurs connectés.");
	}
}
