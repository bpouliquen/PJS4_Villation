package serveur;

import java.io.IOException;

import ressources.IOOStreamLocal;
import ressources.Information;

/**
 * Classe initialisant et instanciant les param�tres pour le client local
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
		// TODO Stub de la m�thode g�n�r� automatiquement
		try {
			System.out.println(ioos.readObject());
			ioos.writeObject(new Information("Client local connect�."));

			while(true) {
				// Attente d'un message du serveur
				Information i = ioos.readObject();

				// Interactions avec le serveur
				if(i.toString().equals("tour"))
					this.tour();
				else {
					//Paquet d'informations du jeu � traiter
				}
			}			
		} finally {
			// D�connexion du client
			System.out.println("D�connexion du client local.");
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
		} catch (InterruptedException e) {
			// TODO Bloc catch g�n�r� automatiquement
			e.printStackTrace();
		}
	}
}
