package graphicEngine;

import gameEngine.Coordonnees;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelecPanel extends JPanel {
	
	private Paint p;
	private Graphics2D g2;
	private Zone select;
	private Image zoneDep;
	private ArrayList<Zone> listTerri = new ArrayList<Zone>();
	private ArrayList<Zone> listDeplacement = new ArrayList<Zone>();
	
	
	public SelecPanel(Paint p, int height, int width) {
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.p = p;
		
		try {
			this.zoneDep = ImageIO.read(new File("./ressources/images/unit/Chemin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);
		
		for (int i = 0; i<getListTerri().size();i++){
			int x = getListTerri().get(i).getX();
			int y = getListTerri().get(i).getY();
			p.drawTerritoire(x, y, g2, getListTerri().get(i));
		}
		
		for (int i = 0; i<getListDeplacement().size();i++){
			int x = getListDeplacement().get(i).getX();
			int y = getListDeplacement().get(i).getY();
			p.drawTerritoire(x, y, g2, getListDeplacement().get(i));
		}
		
		p.drawRessources(g2);
		
		if (this.getSelect() != null) {
			p.drawSelec(g2, this.getSelect());
		}
	}
	
	public void afficherDeplacement(Troupes z) {
		this.listDeplacement.clear();
			defDep(z);
	}
	
	private void defDep(Troupes p) {
		for (Coordonnees c : p.getDeplacement()) {
			this.listDeplacement.add(new Zone(c.getX(), c.getY(), this.zoneDep));
		}
	}
	
	public Zone getSelect() {
		return select;
	}
	public void setSelect(Zone select) {
		this.select = select;
	}
	public ArrayList<Zone> getListTerri() {
		return listTerri;
	}
	public void setListTerri(ArrayList<Zone> listTerri) {
		this.listTerri = listTerri;
	}
	
	public ArrayList<Zone> getListDeplacement() {
		return listDeplacement;
	}
	public void setListDeplacement(ArrayList<Zone> listDeplacement) {
		this.listDeplacement = listDeplacement;
	}
}
