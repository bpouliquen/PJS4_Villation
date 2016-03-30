package graphicEngine;
import gameEngine.Map;
import gameEngine.Ressource;
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
	
	Image img;
	private Map carte;

	public Paint( Map map) {
		this.carte = map;
		try {
            img= ImageIO.read(new File("./ressources/images/unit/UnitBleue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void setBorders(int b){
		BORDERS=b;
	}

	public static void setXYasVertex(boolean b) {
		XYVertex=b;
	}

	public static void setSide(int side) {
		setSideLength(side);
		setTriangleShortSide((int) (getSideLength() / 2));			//t = s sin(30) = (int) CalculateH(s);
		radius =  (int) (getSideLength() * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s); 
		setHeight(2*radius);
	}
	public static void setHeight(int newHeight) {
		height = newHeight;			// h = basic dimension: height (distance between two adj centresr aka size)
		radius = height/2;			// r = radius of inscribed circle
		setSideLength((int) (height / 1.73205));	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
		setTriangleShortSide((int) (radius / 1.73205));	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
	}

	public static PolyImage poly (int x0, int y0) {

		int y = y0 + BORDERS;
		int x = x0 + BORDERS; // + (XYVertex ? t : 0); //Fix added for XYVertex = true. 
		// NO! Done below in cx= section
		if (getSideLength() == 0  || getHeight() == 0) {
			System.out.println("ERROR: size of hex has not been set");
			return new PolyImage();
		}
		int[] cx,cy;
		//I think that this XYvertex stuff is taken care of in the int x line above. Why is it here twice?
		if (XYVertex) 
			cx = new int[] {x,x+getSideLength(),x+getSideLength()+getTriangleShortSide(),x+getSideLength(),x,x-getTriangleShortSide()};  //this is for the top left vertex being at x,y. Which means that some of the hex is cutoff.
		else
			cx = new int[] {x+getTriangleShortSide(),x+getSideLength()+getTriangleShortSide(),x+getSideLength()+getTriangleShortSide()+getTriangleShortSide(),x+getSideLength()+getTriangleShortSide(),x+getTriangleShortSide(),x};	//this is for the whole hexagon to be below and to the right of this point
		cy = new int[] {y,y,y+radius,y+radius+radius,y+radius+radius,y+radius};
		return new PolyImage(cx,cy,6);
	}

	public void drawPoly(Graphics2D g2) throws IOException {
		for (int i=0;i<carte.getLongueur();i++) {
			for (int j=0;j<carte.getLargeur();j++) {
				int x = i * (getSideLength()+getTriangleShortSide());
				int y = j * getHeight() + (i%2) * getHeight()/2;
				PolyImage poly = poly(x,y);
				defZone(i,j,poly);
				BufferedImage img = ImageIO.read(new File(choixImage(poly, x, y, g2)));
				g2.drawImage(img, x+16, y+14, null);
				afficheSpecial(poly, x, y, g2);
			}
		}
	}

	private void defZone(int x, int y, PolyImage poly) {
		poly.setType(this.carte.getTerrain(x, y));
	}

	private static String choixImage(PolyImage poly, int x, int y, Graphics2D g2) throws IOException {
		if (poly.getType().getType() == "Neige") {
			return "./ressources/images/terrain/SnowRedimension.png";
		} else if (poly.getType().getType() == "Desert") {
			return "./ressources/images/terrain/SandRedimension.png";
		} else if (poly.getType().getType() == "Mer") {
			return "./ressources/images/terrain/SeaRedimension.png";
		} else return "./ressources/images/terrain/GrassRedimension.png";
	}

	private static void afficheSpecial(PolyImage poly, int x, int y, Graphics2D g2) throws IOException {
		if (poly.getType().getType() == "Neige" && poly.getType().getModif() == "Montagne") {
			BufferedImage neigeMontagne = ImageIO.read(new File("./ressources/images/terrain/MountainSnowRedimension.png"));
			g2.drawImage(neigeMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Plaine" && poly.getType().getModif() == "Montagne") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./ressources/images/terrain/MountainGreenRedimension.png"));
			g2.drawImage(plaineMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Desert" && poly.getType().getModif() == "Montagne") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./ressources/images/terrain/MountainSandRedimension.png"));
			g2.drawImage(plaineMontagne, x+19, y+5, null);
		} else if (poly.getType().getType() == "Neige" && poly.getType().getModif() == "Foret") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./ressources/images/terrain/ForestSnowRedimension.png"));
			g2.drawImage(plaineMontagne, x+16, y+12, null);
		} else if (poly.getType().getType() == "Plaine" && poly.getType().getModif() == "Foret") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./ressources/images/terrain/ForestGreenRedimension.png"));
			g2.drawImage(plaineMontagne, x+16, y+12, null);
		} else if (poly.getType().getModif() == "Colline") {
			BufferedImage plaineMontagne = ImageIO.read(new File("./ressources/images/terrain/CollinesRedimension.png"));
			g2.drawImage(plaineMontagne, x+17, y+7, null);
		}
	}

	private void afficheRessources(PolyImage poly, int x, int y, Graphics2D g2) throws IOException {
		if (poly.getRessource() == Ressource.CHARBON) {
			BufferedImage charbon = ImageIO.read(new File("./ressources/images/ressources/Coal.png"));
			g2.drawImage(charbon, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.OR) {
			BufferedImage or = ImageIO.read(new File("./ressources/images/ressources/Gold.png"));
			g2.drawImage(or, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.BLE) {
			BufferedImage ble = ImageIO.read(new File("./ressources/images/ressources/Wheat.png"));
			g2.drawImage(ble, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.VACHE) {
			BufferedImage vache = ImageIO.read(new File("./ressources/images/ressources/Cow.png"));
			g2.drawImage(vache, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.SOIE) {
			BufferedImage soie = ImageIO.read(new File("./ressources/images/ressources/Silk.png"));
			g2.drawImage(soie, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.VIN) {
			BufferedImage vin = ImageIO.read(new File("./ressources/images/ressources/Wine.png"));
			g2.drawImage(vin, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.SEL) {
			BufferedImage sel = ImageIO.read(new File("./ressources/images/ressources/Salt.png"));
			g2.drawImage(sel, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.FOURRURE) {
			BufferedImage fourrure = ImageIO.read(new File("./ressources/images/ressources/Pelt.png"));
			g2.drawImage(fourrure, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.OASIS) {
			BufferedImage oasis = ImageIO.read(new File(".ressources//images/ressources/Oasis.png"));
			g2.drawImage(oasis, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.FER) {
			BufferedImage fer = ImageIO.read(new File("./ressources/images/ressources/Iron.png"));
			g2.drawImage(fer, x+25, y+40, null);
		} else if (poly.getRessource() == Ressource.CHEVAL) {
			BufferedImage cheval = ImageIO.read(new File("./ressources/images/ressources/Horse.png"));
			g2.drawImage(cheval, x+25, y+40, null);
		}
	}
	
	public Image drawUn(int i, int j, Graphics2D g2) {
		int x = i * (getSideLength()+getTriangleShortSide());
		int y = j * getHeight() + (i%2) * getHeight()/2;
		g2.drawImage(img, x-70, y-30, null);
        return img;
	}
	
	public void drawCity(int i, int j, Graphics2D g2, Zone city) {
		int x = i * (getSideLength()+getTriangleShortSide());
		int y = j * getHeight() + (i%2) * getHeight()/2;
		g2.drawImage(city.getImg(), x, y-16, null);
	}
	
	public void drawSelec(Graphics2D g2, Zone selec) {
		int x = selec.getX() * (getSideLength()+getTriangleShortSide());
		int y = selec.getY() * getHeight() + (selec.getX()%2) * getHeight()/2;
		g2.drawImage(selec.getImg(),x+11,y+5,null);
	}
	
	public void drawTerritoire(int i, int j, Graphics2D g2, Zone terri) {
		int x = i * (getSideLength()+getTriangleShortSide());
		int y = j * getHeight() + (i%2) * getHeight()/2;
		g2.drawImage(terri.getImg(),x+17,y+15,null);
	}

	public void drawRessources(Graphics2D g2) {
		for (int i=0;i<carte.getLongueur();i++) {
			for (int j=0;j<carte.getLargeur();j++) {
				int x = i * (getSideLength()+getTriangleShortSide());
				int y = j * getHeight() + (i%2) * getHeight()/2;
				PolyImage poly = poly(x,y);
				defRessources(i,j,poly);
				try {
					afficheRessources(poly, x, y, g2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void defRessources(int x, int y, PolyImage p) {
		p.setRessource(this.carte.getTerrain(x, y));
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
		if (XYVertex) mx += getTriangleShortSide();
		int x = (int) (mx / (getSideLength()+getTriangleShortSide())); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
		int y = (int) ((my - (x%2)*radius)/getHeight()); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

		/******FIX for clicking in the triangle spaces (on the left side only)*******/
		//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
		int dx = mx - x*(getSideLength()+getTriangleShortSide());
		int dy = my - y*getHeight();
		if (my - (x%2)*radius < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

		//even columns
		if (x%2==0) {
			if (dy > radius) {	//bottom half of hexes
				if (dx * radius /getTriangleShortSide() < dy - radius) {
					x--;
				}
			}
			if (dy < radius) {	//top half of hexes
				if ((getTriangleShortSide() - dx)*radius/getTriangleShortSide() > dy ) {
					x--;
					y--;
				}
			}
		} else {  // odd columns
			if (dy > getHeight()) {	//bottom half of hexes
				if (dx * radius/getTriangleShortSide() < dy - getHeight()) {
					x--;
					y++;
				}
			}
			if (dy < getHeight()) {	//top half of hexes
				if ((getTriangleShortSide() - dx)*radius/getTriangleShortSide() > dy - radius) {
					x--;
				}
			}
		}
		p.x=x;
		p.y=y;
		return p;
	}

	public static int getSideLength() {
		return sideLength;
	}

	public static void setSideLength(int sideLength) {
		Paint.sideLength = sideLength;
	}

	public static int getTriangleShortSide() {
		return triangleShortSide;
	}

	public static void setTriangleShortSide(int triangleShortSide) {
		Paint.triangleShortSide = triangleShortSide;
	}

	public static int getHeight() {
		return height;
	}

}
