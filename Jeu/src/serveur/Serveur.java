package serveur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import ressources.IOOStream;
import ressources.IOOStreamLocal;
import ressources.Information;

/**
 * Classe initialisant et instanciant les param�tres pour ServeurEcoute
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
		sock = new LinkedList<IOOStream>();
		
		//Cr�ation du client local
		IOOStreamLocal ioos = new IOOStreamLocal();
		new ClientLocal(ioos);
		ioos.writeObject(new Information("Connexion du client local..."));
		System.out.println("[Client local]: " + ioos.readObject());
		sock.add(ioos);
		
		//Lancement du serveur d'�coute pour la connexion des clients distants
		ServeurEcoute se = new ServeurEcoute(port, this.sock, nbJoueurs);
		
		//Lancement du jeu
		new Thread(this).start();
	}

	/**
	 * Proc�dure statique ex�cutable
	 * 
	 * @param args
	 *            String[]
	 */
	public void run() {
		try {
			//Initialisation du jeu
			
			
			// Interactions avec les clients
			while(true) {
				for(IOOStream j : sock) {
					j.writeObject(new Information("tour"));
					this.tour(j);
					
				}
			}
		} catch (Exception e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
		finally {
			// D�connexion du serveur
			System.out.println("Fermeture du serveur.");
		}
	}

	private void broadcast(Information inf) {
		for (IOOStream i : sock) {
			try {
				i.writeObject(inf);
			} catch (IOException e) {
				// TODO Bloc catch g�n�r� automatiquement
				e.printStackTrace();
			}
		}
	}
	
	public void tour(IOOStream j) {
		try {
			Information i = j.readObject();
			while(!i.toString().equals("fin")) {
				//Traitements
				
				
				i = j.readObject();
			}
			//Fin du tour
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
	}
}
