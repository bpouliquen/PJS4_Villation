package launcher;

import ressources.IOOStream;

/**
 * Classe provisoire
 * @author Erwan
 *
 */
public class Joueur {
	private String nom;
	private boolean pret;
	private IOOStream socket;
	
	public Joueur(String nom, IOOStream socket, boolean pret){
		this.nom=nom;
		this.pret=pret;
		this.socket=socket;
	}

	public String getNom() {
		return nom;
	}

	public boolean isPret() {
		return pret;
	}

	public void setPret(boolean pret) {
		this.pret = pret;
	}
	
	public IOOStream getSocket() {
		return socket;
	}

	@Override
	public String toString() {
	      return nom;
	}
}
