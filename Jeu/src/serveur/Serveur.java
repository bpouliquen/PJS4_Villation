package serveur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import launcher.*;
import ressources.*;

/**
 * Classe initialisant et instanciant les paramètres pour ServeurEcoute
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class Serveur implements Runnable {
	private List<Joueur> joueurs;
	private List<IOOStream> sockets;
	private final int nbJoueurs;
	private InterfaceRejoindrePartie ipartie;

	/**
	 * Constructeur
	 */
	public Serveur(int port, int nbJoueurs, String nomPartie) {
		joueurs = new ArrayList<Joueur>();
		sockets = new LinkedList<IOOStream>();
		this.nbJoueurs = nbJoueurs;
		
		//Création du client local
		
		IOOStreamLocal ioos = new IOOStreamLocal();
		new ClientLocal(ioos);
		ioos.writeObject(new Information("Connexion du client local..."));
		System.out.println("[Client local]: " + ioos.readObject());
		joueurs.add(new Joueur("Joueur 1",true));
		sockets.add(ioos);
		
		this.ipartie = new InterfaceRejoindrePartie(nomPartie, Integer.toString(port), this);
		ipartie.setLocationRelativeTo(null);
		ipartie.remplirListeJoueur(this.joueurs);
		
		//Lancement du serveur d'écoute pour la connexion des clients distants
		new ServeurEcoute(port, this, ipartie, nomPartie);
	}

	public List<IOOStream> getSockets() {
		return sockets;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	/**
	 * Procédure statique exécutable
	 * 
	 * @param args
	 *            String[]
	 */
	public void run() {
		try {
			this.broadcast(new Information("go"));
			System.out.println("Démarrage du jeu");
			ipartie.dispose();
			/*
			 * DEMARRAGE DU JEU
			 */
		} finally {
			// Déconnexion du serveur
			System.out.println("Fermeture du serveur.");
		}
	}

	public void broadcast(Object o) {
		for (IOOStream s : sockets) {
			try {
				s.writeObject(o);
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
		}
	}
	
	public void tour(IOOStream s) {
		try {
			Information i = (Information) s.readObject();
			while(!i.toString().equals("fin")) {
				//Traitements
				
				
				i = (Information) s.readObject();
			}
			//Fin du tour
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
