package client;

public class AppliClient {

	private static Client c;

	public static void main(String[] args) {
		// TODO Stub de la m�thode g�n�r� automatiquement
		c = new Client();
		new Thread(c).start();
	}

}
