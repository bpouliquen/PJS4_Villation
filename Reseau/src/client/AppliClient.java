package client;

public class AppliClient {

	private static Client c;

	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		c = new Client();
		new Thread(c).start();
	}

}
