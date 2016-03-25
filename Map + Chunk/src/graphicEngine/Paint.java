package graphicEngine;
import gameEngine.Chunk;
import gameEngine.Terrain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paint {

	private static int BORDERS=50;	//default number of pixels for the border.
	private static int sideLength=0;	// length of one side
	private static int triangleShortSide=0;	// short side of 30o triangle outside of each hex
	private static int radius=0;	// radius of inscribed circle (centre to middle of each side). radius = height/2
	private static int height=0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
	public static boolean XYVertex=true;

	private Terrain[][] map;

	public Paint(int[][] b, Terrain [][] m) {
		this.setMap(m);
	}

	public static void setBorders(int b){
		BORDERS=b;
	}

	public static void setXYasVertex(boolean b) {
		XYVertex=b;
	}

	public static void setSide(int side) {
		sideLength=side;
		triangleShortSide =  (int) (sideLength / 2);			//t = s sin(30) = (int) CalculateH(s);
		radius =  (int) (sideLength * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s); 
		height=2*radius;
	}
	public static void setHeight(int newHeight) {
		height = newHeight;			// h = basic dimension: height (distance between two adj centresr aka size)
		radius = height/2;			// r = radius of inscribed circle
		sideLength = (int) (height / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
		triangleShortSide = (int) (radius / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
	}

	public static polyImage poly (int x0, int y0) {

		int y = y0 + BORDERS;
		int x = x0 + BORDERS; // + (XYVertex ? t : 0); //Fix added for XYVertex = true. 
		// NO! Done below in cx= section
		if (sideLength == 0  || height == 0) {
			System.out.println("ERROR: size of hex has not been set");
			return new polyImage();
		}
		int[] cx,cy;
		//I think that this XYvertex stuff is taken care of in the int x line above. Why is it here twice?
		if (XYVertex) 
			cx = new int[] {x,x+sideLength,x+sideLength+triangleShortSide,x+sideLength,x,x-triangleShortSide};  //this is for the top left vertex being at x,y. Which means that some of the hex is cutoff.
		else
			cx = new int[] {x+triangleShortSide,x+sideLength+triangleShortSide,x+sideLength+triangleShortSide+triangleShortSide,x+sideLength+triangleShortSide,x+triangleShortSide,x};	//this is for the whole hexagon to be below and to the right of this point
		cy = new int[] {y,y,y+radius,y+radius+radius,y+radius+radius,y+radius};
		return new polyImage(cx,cy,6);
	}

	public void drawPoly(Graphics2D g2) throws IOException {
		//System.out.println("Je suis dans drawPoly");
		for (int i=0;i<Chunk.getLong();i++) {
			for (int j=0;j<Chunk.getLarg();j++) {
				int x = i * (sideLength+triangleShortSide);
				int y = j * height + (i%2) * height/2;
				polyImage poly = poly(x,y);
				defZone(i,j,poly);
				BufferedImage img = ImageIO.read(new File(choixImage(poly, x, y, g2)));
				g2.drawImage(img, x+16, y+14, null);
				afficheSpecial(poly, x, y, g2);
			}
		}
	}

	private void defZone(int x, int y, polyImage poly) {
		poly.setType(this.map[x][y]);
	}

	private static String choixImage(polyImage poly, int x, int y, Graphics2D g2) throws IOException {
		if (poly.getType().getType() == "Neige") {
			return "./images/terrain/SnowRedimension.png";
		} else if (poly.getType().getType() == "Desert") {
			return "./images/terrain/SandRedimension.png";
		} else if (poly.getType().getType() == "Mer") {
			return "./images/terrain/SeaRedimension.png";
		} else return "./images/terrain/GrassRedimension.png";
	}

	private static void afficheSpecial(polyImage poly, int x, int y, Graphics2D g2) throws IOException {
		//Attention, possible bug quand ajout zoom/dezoom et deplacement map
		if (poly.getType().getType() == "Neige" && poly.getType().getModif() == "Montagne") {
			BufferedImage neigeMontagne = ImageIO.read(new File("./images/terrain/MountainSnowRedimension.png"));
			g2.drawImage(neigeMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Plaine" && poly.getType().getModif() == "Montagne") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./images/terrain/MountainGreenRedimension.png"));
			g2.drawImage(plaineMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Desert" && poly.getType().getModif() == "Montagne") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./images/terrain/MountainSandRedimension.png"));
			g2.drawImage(plaineMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Neige" && poly.getType().getModif() == "Foret") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./images/terrain/ForestSnowRedimension.png"));
			g2.drawImage(plaineMontagne, x+16, y+12, null);
		} else if (poly.getType().getType() == "Plaine" && poly.getType().getModif() == "Foret") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./images/terrain/ForestGreenRedimension.png"));
			g2.drawImage(plaineMontagne, x+16, y+12, null);
		} else if (/*poly.getType().getType() == "Neige" && */poly.getType().getModif() == "Colline") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./images/terrain/CollinesRedimension.png"));
			g2.drawImage(plaineMontagne, x+17, y+7, null);
		}
	}

	public Image drawUn(int i, int j, Graphics2D g2) {
		int x = i * (sideLength+triangleShortSide);
		int y = j * height + (i%2) * height/2;
		Image img;
		try {
			img = ImageIO.read(new File("./images/unit/TestUnit.png"));
			g2.drawImage(img, x+24, y+9, null);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//This function changes pixel location from a mouse click to a hex grid location
	/*****************************************************************************
	 * Name: pxtoHex (pixel to hex)
	 * Parameters: mx, my. These are the co-ordinates of mouse click.
	 * Returns: point. A point containing the coordinates of the hex that is clicked in.
           If the point clicked is not a valid hex (ie. on the borders of the board, (-1,-1) is returned.
	 * Function: This only works for hexes in the FLAT orientation. The POINTY orientation would require
            a whole other function (different math).
            It takes into account the size of borders.
            It also works with XYVertex being True or False.
	 *****************************************************************************/
	public static Point pxtoHex(int mx, int my) {
		Point p = new Point(-1,-1);
		//correction for BORDERS and XYVertex
		mx -= BORDERS;
		my -= BORDERS;
		if (XYVertex) mx += triangleShortSide;
		int x = (int) (mx / (sideLength+triangleShortSide)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
		int y = (int) ((my - (x%2)*radius)/height); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

		/******FIX for clicking in the triangle spaces (on the left side only)*******/
		//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
		int dx = mx - x*(sideLength+triangleShortSide);
		int dy = my - y*height;
		if (my - (x%2)*radius < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

		//even columns
		if (x%2==0) {
			if (dy > radius) {	//bottom half of hexes
				if (dx * radius /triangleShortSide < dy - radius) {
					x--;
				}
			}
			if (dy < radius) {	//top half of hexes
				if ((triangleShortSide - dx)*radius/triangleShortSide > dy ) {
					x--;
					y--;
				}
			}
		} else {  // odd columns
			if (dy > height) {	//bottom half of hexes
				if (dx * radius/triangleShortSide < dy - height) {
					x--;
					y++;
				}
			}
			if (dy < height) {	//top half of hexes
				if ((triangleShortSide - dx)*radius/triangleShortSide > dy - radius) {
					x--;
				}
			}
		}
		p.x=x;
		p.y=y;
		return p;
	}

	public Terrain[][] getMap() {
		return map;
	}

	public void setMap(Terrain[][] m) {
		this.map = m;
	}

}
