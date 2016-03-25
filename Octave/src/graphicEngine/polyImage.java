package graphicEngine;
import gameEngine.Terrain;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class polyImage extends Polygon{

	private Terrain type;
	private Terrain spe;

	public polyImage(){}

	public polyImage(int[] cx, int[] cy, int i) {
		super(cx,cy,i);
	}

	public Terrain getType() {
		return type;
	}

	public void setType(Terrain type) {
		this.type = type;
	}

	public Terrain getSpe() {
		return spe;
	}

	public void setSpe(Terrain spe) {
		this.spe = spe;
	}
}
