package graphicEngine;
import gameEngine.Case;
import gameEngine.Ressource;
import gameEngine.Terrain;

import java.awt.Polygon;

@SuppressWarnings("serial")
public class PolyImage extends Polygon{

	private Case type;
	private Case spe;
	private Case ressource;
	
	public PolyImage(){}

	public PolyImage(int[] cx, int[] cy, int i) {
		super(cx,cy,i);
	}

	public Terrain getType() {
		return type.getSame();
	}

	public void setType(Case map) {
		this.type = map;
	}

	public Terrain getSpe() {
		return spe.getSame();
	}

	public void setSpe(Case spe) {
		this.spe = spe;
	}

	public Ressource getRessource() {
		return ressource.getRessource();
	}

	public void setRessource(Case ressource) {
		this.ressource = ressource;
	}
}
