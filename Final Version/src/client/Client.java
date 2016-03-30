package client;

import gameEngine.Map;
import graphicEngine.Fenetre;
import graphicEngine.UnitPanel;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import launcher.*;
import ressources.*;

public class Client implements Runnable {

	private IOOStreamReseau ioos;
	private InterfaceRejoindrePartie ipartie;

	public Client(String adr, int port) {
		System.out.println("Tentative de connexion au serveur...");
		try {
			Socket s = new Socket(adr, port);
			this.ioos = new IOOStreamReseau(s);
			System.out.println("Connexion �tablie.");
			String nomPartie = ioos.readObject().toString();
			ioos.writeObject(new Information("Connect�."));
			@SuppressWarnings("unchecked")
			ArrayList<Joueur> joueurs = (ArrayList<Joueur>) ioos.readObject();
			ipartie = new InterfaceRejoindrePartie(nomPartie, null, null);
			ipartie.setLocationRelativeTo(null);
			ipartie.remplirListeJoueur(joueurs);
			new Thread(this).start();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Partie inexistante (Attention : l�IP affich�e chez l�h�te peut parfois �tre erron�e)", "Erreur", JOptionPane.ERROR_MESSAGE);
			new InterfaceRejoindreLAN().setLocationRelativeTo(null);
			//e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Stub de la m�thode g�n�r� automatiquement
		try {
			String msg = ((Information) ioos.readObject()).toString();
			while(!msg.equals("go")) {
				if(msg.equals("up")) {
					@SuppressWarnings("unchecked")
					ArrayList<Joueur> j = (ArrayList<Joueur>) ioos.readObject();
					System.out.println(j.size());
					ipartie.remplirListeJoueur(j);
				}
				msg = ((Information) ioos.readObject()).toString();
			}
			System.out.println("D�marrage du jeu");
			ipartie.dispose();
			
			Map client = (Map) ioos.readObject();
			UnitPanel up;
			
			Fenetre f = new Fenetre(client);
			
		} catch (Exception e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		} finally {
			// D�connexion du client
			System.out.println("D�connexion.");
			ioos.close();
		}
	}
	
	public void tour() {
		try {
			//Tour du joueur
			System.out.println("C'est mon tour !");
			Thread.sleep(5000);
			
			//Fin du tour
			ioos.writeObject(new Information("fin"));
		} catch (InterruptedException | IOException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
	}
}
