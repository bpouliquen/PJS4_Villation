package client;

import java.io.IOException;
import java.net.Socket;

import ressources.IOOStreamReseau;
import ressources.InfoEntrante;
import ressources.Information;

public class Client implements Runnable {

	private IOOStreamReseau ioos;

	public Client(String adr, int port) {
		System.out.println("Tentative de connexion au serveur...");
		try {
			Socket s = new Socket(adr, port);
			this.ioos = new IOOStreamReseau(s);
			System.out.println("Connexion �tablie.");
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Stub de la m�thode g�n�r� automatiquement
		try {
			System.out.println(ioos.readObject());
			ioos.writeObject(new Information("Connect�."));

			// Attente d'un message du serveur
			InfoEntrante ie = (InfoEntrante) ioos.readObject();

			// Interactions avec le serveur
			while (!ie.toString().equals("end")) {
				System.out.println("[Serveur]: " + ie);
				if (ie.getEmplacement() >= 0)
					ioos.writeObject(new Information("Ta gueule tu parles trop !"));
				ie = (InfoEntrante) ioos.readObject();
			}

			// D�connexion du client
			System.out.println("D�connexion.");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		} finally {
			ioos.close();
		}
	}
}
