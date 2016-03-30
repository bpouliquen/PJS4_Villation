package serveur;

import gameEngine.Map;
import graphicEngine.Fenetre;
import graphicEngine.UnitPanel;
import ressources.*;

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
			String msg = ((Information) ioos.readObject()).toString();
			while(!msg.equals("go")) {
				if(msg.equals("up")) {
					ioos.readObject();
				}
				msg = ((Information) ioos.readObject()).toString();
			}
			
			Map client = (Map) ioos.readObject();
			UnitPanel up;
			
			Fenetre f = new Fenetre(client);
			
		} finally {
			// Déconnexion du client
			System.out.println("Déconnexion du client local.");
			ioos.close();
		}
	}
}
