package graphicEngine;
import java.awt.Image;


public class Zone {

	private int x;
	private int y;
	private Image img;
	public Zone(int i, int j, Image img) {
		this.setX(i);
		this.setY(j);
		this.setImg(img);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
}
