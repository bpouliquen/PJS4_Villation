package client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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
			System.out.println("Connexion établie.");
			String nomPartie = ioos.readObject().toString();
			ioos.writeObject(new Information("Connecté."));
			@SuppressWarnings("unchecked")
			ArrayList<Joueur> joueurs = (ArrayList<Joueur>) ioos.readObject();
			ipartie = new InterfaceRejoindrePartie(nomPartie, null);
			ipartie.setLocationRelativeTo(null);
			ipartie.remplirListeJoueur(joueurs);
			new Thread(this).start();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			String msg = ((Information) ioos.readObject()).toString();
			while(!msg.equals("go")) {
				if(msg.equals("up")) {
					ArrayList<Joueur> j = (ArrayList<Joueur>) ioos.readObject();
					System.out.println(j.size());
					ipartie.remplirListeJoueur(j);
				}
				msg = ((Information) ioos.readObject()).toString();
			}
		} catch (Exception e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} finally {
			// Déconnexion du client
			System.out.println("Déconnexion.");
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
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
