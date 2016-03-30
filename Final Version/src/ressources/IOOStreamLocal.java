package ressources;

import java.util.LinkedList;

/**
 * Classe instanciant une m�moire tampon pour le joueur local
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class IOOStreamLocal implements IOOStream {

	private LinkedList<Object> listeEcriture;
	private LinkedList<Object> listeLecture;

	/**
	 * Constructeur par d�faut
	 */
	public IOOStreamLocal() {
		listeEcriture=new LinkedList<Object>();
		listeLecture=new LinkedList<Object>();
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
	protected LinkedList<Object> getListeLec(){
		return this.listeLecture;
	}
	
	/**
	 * Accesseur de l'attribut listeEcriture
	 * @return
	 */
	protected LinkedList<Object> getListeEc(){
		return this.listeEcriture;
	}
	
	@Override
	public void writeObject(Object paquet) {
		// TODO Auto-generated method stub
		synchronized(listeEcriture){
			listeEcriture.add(paquet);
			listeEcriture.notifyAll();
		}
	}

	@Override
	public Object readObject() {
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
