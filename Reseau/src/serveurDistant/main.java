package serveurDistant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import socketPerso.IOOStreamReseau;
import socketPerso.Information;

public class main {
	private static int PORT = 2000;
	private static ServerSocket serveur;
	private static List<Socket> rooms;

	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		try {
			serveur = new ServerSocket(PORT);
			while(true) {
				Socket temp = serveur.accept();
				IOOStreamReseau ioos = new IOOStreamReseau(temp);
				Information inf = (Information) ioos.readObject();
				if(inf.toString().equals("client")) {
					ioos.writeObject(rooms);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

}
