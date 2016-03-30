package graphicEngine;

import gameEngine.Coordonnees;

import java.awt.Image;

import Game.UnitDepla;

public class Troupes extends UnitDepla {

	private final static int nbDeplacement = 2;
	Image img;

	public Troupes(int i, int j ,Image img) {
		super(nbDeplacement, new Coordonnees(i,j));
		this.img = img;
	}

	public int getNbDeplacement() {
		return nbDeplacement;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
