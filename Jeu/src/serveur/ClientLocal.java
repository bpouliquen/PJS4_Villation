package serveur;

import java.io.IOException;

import ressources.IOOStreamLocal;
import ressources.InfoEntrante;
import ressources.Information;

/**
 * Classe initialisant et instanciant les paramètres pour le client local
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class ClientLocal implements Runnable {

	private IOOStreamLocal ioos;

	/**
	 * Constructeur
	 * 
	 * @param temp
	 *            IOOStreamLocal
	 */
	public ClientLocal(IOOStreamLocal temp) {
		ioos = new IOOStreamLocal(temp);
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			System.out.println(ioos.readObject());
			ioos.writeObject(new Information("Client local connecté."));

			// Attente d'un message du serveur
			InfoEntrante ie = (InfoEntrante) ioos.readObject();

			// Interactions avec le serveur
			while (!ie.toString().equals("end")) {
				System.out.println("[Serveur]: " + ie);
				if (ie.getEmplacement() >= 0)
					ioos.writeObject(new Information("Ta gueule tu parles trop !"));
				ie = (InfoEntrante) ioos.readObject();
			}

			// Déconnexion du client
			System.out.println("Déconnexion.");
		} finally {
			ioos.close();
		}
	}
}
