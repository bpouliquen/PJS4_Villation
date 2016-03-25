package ressources;

public class InfoEntrante extends Information {
	private static final long serialVersionUID = 1L;
	int id;

	// Attributs à ajouter
	public InfoEntrante(String mesg, int emplacement) {
		super(mesg);
		this.id = emplacement;
		// TODO Stub du constructeur généré automatiquement
	}

	public int getEmplacement() {
		return id;
	}

}
