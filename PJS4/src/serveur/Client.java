package serveur;

import socketPerso.*;

/**
 * Classe initialisant et instanciant les paramètres pour le client local
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class Client implements Runnable{

	private IOOStreamLocal ioos;
	
	/**
	 * Constructeur
	 * @param temp IOOStreamLocal
	 */
	public Client(IOOStreamLocal temp){
		ioos=new IOOStreamLocal(temp);
	}
	
	@Override
	public void run() {
		System.out.println(ioos.readObject());
		ioos.writeObject(new Information("Je suis un client local"));
		try {
			Thread.sleep(5000);

			ioos.writeObject(new Information("mais tg"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
