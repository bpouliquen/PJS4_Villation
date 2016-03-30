package gameEngine;

public class TerritoireNat extends Territoire{

	private Terrain type;

	public Terrain getTerr(int i, int j) {
		return(super.contientCase(new Coordonnees(i, j))?this.type:null);
	}
}
