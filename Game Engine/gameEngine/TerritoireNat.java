package gameEngine;

public class TerritoireNat extends Territoire{

	private Terrain type;

	public Terrain getTerr(int i, int j) {
		return(super.contientCase(new Coordonnées(i, j))?this.type:null);
	}
}
