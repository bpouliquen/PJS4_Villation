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
			
			//Interactions avec le serveur
			while (!ie.equals("exit")) {
				System.out.println("[Serveur]: " + ie);
				ie = (InfoEntrante) ioos.readObject();
			}
			
			//Déconnexion
			System.out.println("Déconnexion.");
		} finally {
			ioos.close();
		}
	}
}
