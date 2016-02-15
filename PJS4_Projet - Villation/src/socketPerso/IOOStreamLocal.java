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

	private List<Information> liste;

	/**
	 * Constructeur par défaut
	 */
	public IOOStreamLocal() {
		liste = new LinkedList<Information>();
	}

	/**
	 * Constructeur
	 * @param liste List Information
	 */
	public IOOStreamLocal(List<Information> liste) {
		this.liste = liste;
	}

	@Override
	public void writeObject(Information paquet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Information readObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
}
