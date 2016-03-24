package client;

import java.io.IOException;
import java.net.Socket;

import socketPerso.*;

public class Client implements Runnable {

	private IOOStreamReseau ioos;
	private static String ADR = "127.0.0.1";
	private static int PORT = 2000;

	public Client() {
		System.out.println("Tentative de connexion au serveur...");
		try {
			Socket s = new Socket(ADR, PORT);
			this.ioos = new IOOStreamReseau(s);
			System.out.println("Connexion établie.");
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
			ioos.writeObject(new Information("connecté."));
			
			//Attente d'un message du serveur
			InfoEntrante ie = (InfoEntrante) ioos.readObject();
			
			//Interactions avec le serveur
			while (!ie.toString().equals("end")) {
				System.out.println("[Serveur]: " + ie);
				if (ie.getEmplacement() >= 0)
					ioos.writeObject(new Information("Ta gueule tu parles trop !"));
				ie = (InfoEntrante) ioos.readObject();
			}
			
			//Déconnexion du client
			System.out.println("Déconnexion.");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		finally {
			ioos.close();
		}
	}
}
