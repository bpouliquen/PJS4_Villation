package Reseau.socketPerso;

import java.util.LinkedList;

public class IOOStreamLocal implements IOOStream{

	private LinkedList<Information> listeEcriture;
	private LinkedList<Information> listeLecture;
	public IOOStreamLocal(){
		listeEcriture=new LinkedList<Information>();
		listeLecture=new LinkedList<Information>();
	}
	protected LinkedList<Information> getListeLec(){
		return this.listeLecture;
	}
	protected LinkedList<Information> getListeEc(){
		return this.listeEcriture;
	}
	public IOOStreamLocal(IOOStreamLocal associe){
		this.listeLecture=associe.getListeEc();
		this.listeEcriture=associe.getListeLec();
	}
	@Override
	public void writeObject(Information paquet) {
		synchronized(listeEcriture){
			listeEcriture.add(paquet);
			listeEcriture.notifyAll();
		}
	}

	@Override
	public Information readObject() {
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
				
	}

}
