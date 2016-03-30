package graphicEngine;
import gameEngine.Coordonnees;
import gameEngine.Terrain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

import map.game;


@SuppressWarnings("serial")
public class UnitPanel extends JPanel implements MouseListener, MouseMotionListener {

	final static Color COLOURBACK =  Color.darkGray;
	final static int BORDMOVEMAP = 65;
	final static int VITESSEMOVEMAP =3;
	final static int tailleTerritoire = 2;

	private Graphics2D g2;
	private Paint p;
	private ArrayList<Troupes> listUnit = new ArrayList<Troupes>();
	private ArrayList<Zone> listCity = new ArrayList<Zone>();
	
	private Image imgP1;
	private Image imgP2;
	private Image terriP1;
	private Image terriP2;
	private SelecPanel panelPere;
	private Troupes SelectionTemp;
	private Fenetre f;

	public UnitPanel(Paint p, SelecPanel panelPere, int height, int width, Fenetre f) {
		setBackground(COLOURBACK);
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.p = p;
		this.f = f;
		this.panelPere = panelPere;
		
		try {
			terriP1 = ImageIO.read(new File("./ressources/images/unit/BlueTerritoire.png"));
			terriP2 = ImageIO.read(new File("./ressources/images/unit/RedTerritoire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Image img = ImageIO.read(new File("./ressources/images/unit/CityBlue.png"));
			imgP1 = img;
			img = ImageIO.read(new File("./ressources/images/unit/CityRed.png"));
			imgP2 = img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Zone p1 = new Zone(6,4,imgP1);
		defTerri(p1,tailleTerritoire,terriP1);
		
		Zone p2 = new Zone(17,8,imgP2);
		defTerri(p2,tailleTerritoire,terriP2);
		
		listCity.add(p1);
		listCity.add(p2);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void repeindre(){
		panelPere.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);
		
		for (int i=0;i<listCity.size();i++){
			int x = listCity.get(i).getX();
			int y = listCity.get(i).getY();
			p.drawCity(x, y, g2, listCity.get(i));
		}
		
		for (int i=0;i<getListUnit().size();i++) {
			int x=getListUnit().get(i).getCo().getX();
			int y=getListUnit().get(i).getCo().getY();
			p.drawUn(x,y,g2);
		}
	}

	public void defTerri(Zone p, int i, Image img) {
		List<Coordonnees> temp = new ArrayList<Coordonnees>();
		temp = game.getZone(new Coordonnees(p.getX(),p.getY()), i);
		
		for (Coordonnees c : temp) {
			panelPere.getListTerri().add(new Zone(c.getX(),c.getY(),img));
		}
	}
	
	public void afficherOptionVille() {
		InterfaceVille dialog = new InterfaceVille();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		dialog.setLocation(0, (screenSize.height-dialog.getHeight()));
		dialog.setVisible(true);
	}
	
	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}

	public void addUnit(int x, int y) {
		getListUnit().add(new Troupes (x,y,null));
		repaint(); 
	}

	public ArrayList<Troupes> getListUnit() {
		return listUnit;
	}
	
	public ArrayList<Zone> getListCity(){
		return listCity;
	}

	public void setListUnit(ArrayList<Troupes> listUnit) {
		this.listUnit = listUnit;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	private int verifPresence(int x, int y) {
		for (int i=0;i<getListUnit().size();i++) {
			if(getListUnit().get(i).getCo().getX()==x && getListUnit().get(i).getCo().getY()==y){
				return 1;
			}
		}
		
		for (int j=0;j<getListCity().size();j++) {
			if (getListCity().get(j).getX() == x && getListCity().get(j).getY() == y) {
				return 2;
			}
		}
		return 0;
	}
	
	private boolean verifCarte(int x, int y) {
		if (f.getCarte().getTerrain(x,y).getSame().getType() != "Mer" &&
			f.getCarte().getTerrain(x,y).getSame().getModif() != "Montagne") {
			return true;
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = new Point(Paint.pxtoHex(e.getX(),e.getY()));
		if(verifPresence(p.x, p.y) == 1){
			panelPere.getListDeplacement().clear();
			panelPere.repaint();
				for (Troupes z : getListUnit()){
					if (z.getCo().getX() == p.x && z.getCo().getY() == p.y) {
							this.SelectionTemp = z;
							panelPere.afficherDeplacement(z);
							panelPere.repaint();
					}
			}
		} else if (verifPresence(p.x, p.y) == 2) {
			afficherOptionVille();
		} else if ((verifPresence(p.x, p.y) == 0) && SelectionTemp != null) {
			for (Zone z : panelPere.getListDeplacement()) {
				if (z.getX() == p.x && z.getY() == p.y) {
					for (Troupes t : getListUnit()){
						if (t.getCo().getX() == SelectionTemp.getCo().getX() && t.getCo().getY() == SelectionTemp.getCo().getY()) {
							t.getCo().setX(p.x);
							t.getCo().setY(p.y);
							repaint();
						}
					}
				}
			}
			panelPere.getListDeplacement().clear();
			panelPere.repaint();
			SelectionTemp = null;
		}
		else if ((verifPresence(p.x, p.y) == 0) && SelectionTemp == null){
			panelPere.getListDeplacement().clear();
			panelPere.repaint();
			if (verifCarte(p.x,p.y)) {
				addUnit(p.x, p.y);
			}
		}
		
		if (!(e.getX() < 30 || e.getY() < 30 || e.getX() >= f.getWidth() || e.getY() >= f.getHeight())) {
			Image img;
			try {
				img = ImageIO.read(new File("./ressources/images/unit/SelectorYellow.png"));
				Zone z = new Zone(p.x,p.y,img);
				panelPere.setSelect(z);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			panelPere.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
