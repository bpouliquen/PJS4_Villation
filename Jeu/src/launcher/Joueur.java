package launcher;
/**
 * Classe provisoire
 * @author Erwan
 *
 */
public class Joueur {
	private String nom;
	private boolean pret;
	
	public Joueur(String nom, boolean pret){
		this.nom=nom;
		this.pret=pret;
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
	
	@Override
	public String toString() {
	      return nom;
	}
}
