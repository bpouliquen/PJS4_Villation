package serveur;

import socketPerso.*;

/**
 * Classe initialisant et instanciant les param�tres pour le client local
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class ClientLocal implements Runnable{

	private IOOStreamLocal ioos;
	
	/**
	 * Constructeur
	 * @param temp IOOStreamLocal
	 */
	public ClientLocal(IOOStreamLocal temp){
		ioos=new IOOStreamLocal(temp);
	}
	
	@Override
	public void run() {
		System.out.println(ioos.readObject());
		ioos.writeObject(new Information("Client local connect�."));
		try {
			//Thread.sleep(5000);
			System.out.println("[Local]" + ioos.readObject());
			ioos.writeObject(new Information("D�connexion du client local."));
		}/* catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		finally {
			ioos.close();
		}
	}

}
