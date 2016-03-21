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
			ioos.writeObject(new InfoSortante("connecté.",-1));
			Thread.sleep(5000);
			System.out.println("Message du serveur: "+ ioos.readObject());
			System.out.println("Déconnexion.");
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
