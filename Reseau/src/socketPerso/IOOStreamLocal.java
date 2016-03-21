package socketPerso;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe instanciant une mémoire tampon pour le joueur local
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class IOOStreamLocal implements IOOStream {

	private LinkedList<Information> listeEcriture;
	private LinkedList<Information> listeLecture;

	/**
	 * Constructeur par défaut
	 */
	public IOOStreamLocal() {
		listeEcriture=new LinkedList<Information>();
		listeLecture=new LinkedList<Information>();
	}

	/**
	 * Constructeur
	 * @param associe IOStreamLocal
	 */
	public IOOStreamLocal(IOOStreamLocal associe){
		this.listeLecture=associe.getListeEc();
		this.listeEcriture=associe.getListeLec();
	}

	/**
	 * Accesseur de l'attribut listeLecture
	 * @return
	 */
	protected LinkedList<Information> getListeLec(){
		return this.listeLecture;
	}
	
	/**
	 * Accesseur de l'attribut listeEcriture
	 * @return
	 */
	protected LinkedList<Information> getListeEc(){
		return this.listeEcriture;
	}
	
	@Override
	public void writeObject(Information paquet) {
		// TODO Auto-generated method stub
		synchronized(listeEcriture){
			listeEcriture.add(paquet);
			listeEcriture.notifyAll();
		}
	}

	@Override
	public Information readObject() {
		// TODO Auto-generated method stub
		synchronized (listeLecture){
			while(listeLecture.isEmpty()){
				try {
					listeLecture.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return listeLecture.removeFirst();
			}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
