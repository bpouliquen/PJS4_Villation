package serveur;

import socketPerso.*;

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
	}

	@Override
	public void run() {
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

			// Déconnexion
			System.out.println("Client local déconnecté.");
		} finally {
			ioos.close();
		}
	}
}
