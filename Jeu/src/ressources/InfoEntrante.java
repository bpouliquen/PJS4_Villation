package ressources;

public class InfoEntrante extends Information {
	private static final long serialVersionUID = 1L;
	int id;

	// Attributs � ajouter
	public InfoEntrante(String mesg, int emplacement) {
		super(mesg);
		this.id = emplacement;
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	public int getEmplacement() {
		return id;
	}

}
