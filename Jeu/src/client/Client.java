package client;

import java.io.IOException;
import java.net.Socket;

import map.Map;
import ressources.IOOStreamReseau;
import ressources.Information;

public class Client implements Runnable {

	private IOOStreamReseau ioos;

	public Client(String adr, int port) {
		System.out.println("Tentative de connexion au serveur...");
		try {
			Socket s = new Socket(adr, port);
			this.ioos = new IOOStreamReseau(s);
			System.out.println("Connexion établie.");
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			System.out.println(ioos.readObject());
			ioos.writeObject(new Information("Connecté."));

				// Attente d'un message du serveur
			Map.setupTransmission(ioos);
			
		} catch (ClassNotFoundException | IOException e) {
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
