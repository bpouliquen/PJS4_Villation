package serveur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import launcher.*;
import ressources.IOOStreamLocal;
import ressources.Information;

/**
 * Classe initialisant et instanciant les paramètres pour ServeurEcoute
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class Serveur implements Runnable {
	private List<Joueur> joueurs;
	private final int nbJoueurs;

	/**
	 * Constructeur
	 */
	public Serveur(int port, int nbJoueurs, String nomPartie) {
		joueurs = new ArrayList<Joueur>();
		this.nbJoueurs = nbJoueurs;
		
		//Création du client local
		
		IOOStreamLocal ioos = new IOOStreamLocal();
		new ClientLocal(ioos);
		ioos.writeObject(new Information("Connexion du client local..."));
		System.out.println("[Client local]: " + ioos.readObject());
		joueurs.add(new Joueur("Joueur 1", ioos, false));
		
		InterfaceRejoindrePartie ipartie = new InterfaceRejoindrePartie(nomPartie, Integer.toString(port));
		ipartie.setLocationRelativeTo(null);
		ipartie.remplirListeJoueur(this.joueurs);
		
		//Lancement du serveur d'écoute pour la connexion des clients distants
		new ServeurEcoute(port, this.joueurs, this.nbJoueurs - 1, ipartie, nomPartie);
		
		//Lancement du jeu
		new Thread(this).start();
	}

	/**
	 * Procédure statique exécutable
	 * 
	 * @param args
	 *            String[]
	 */
	public void run() {
		try {
			//Initialisation du jeu
			
			
			// Interactions avec les clients
			while(true) {
				for(Joueur j : joueurs) {
					j.getSocket().writeObject(new Information("tour"));
					this.tour(j);
					
				}
			}
		} catch (Exception e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		finally {
			// Déconnexion du serveur
			System.out.println("Fermeture du serveur.");
		}
	}

	private void broadcast(Information inf) {
		for (Joueur j : joueurs) {
			try {
				j.getSocket().writeObject(inf);
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
		}
	}
	
	public void tour(Joueur j) {
		try {
			Information i = j.getSocket().readObject();
			while(!i.toString().equals("fin")) {
				//Traitements
				
				
				i = j.getSocket().readObject();
			}
			//Fin du tour
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
