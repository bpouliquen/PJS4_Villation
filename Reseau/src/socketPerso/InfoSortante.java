package socketPerso;

public class InfoSortante extends Information {
	private static final long serialVersionUID = 1L;
	int emplacement;
	//Attributs � ajouter
	public InfoSortante(String mesg, int emplacement) {
		super(mesg);
		this.emplacement = emplacement;
		// TODO Stub du constructeur g�n�r� automatiquement
	}
	
	public int getEmplacement() {
		return this.emplacement;
	}

}
