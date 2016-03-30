package gameEngine;

import java.io.Serializable;

import Game.UnitDepla;

public class Map implements IMap, Serializable{

	private Case[][] terrain;
	private int longueur;
	private int largeur;
	
	public Map(int longueur, int largeur){
		this.setLongueur(longueur);
		this.setLargeur(largeur);
		UnitDepla.setMap(this);
		terrain=new Case[longueur][largeur];
		ChunkGeneration.generer(terrain);
	}
	
	public Terrain[] getFieldAround(Coordonnees[] tab) {
		Terrain[] terr=new Terrain[6];
		for (int j=0; j<6;j++){
			terr[j]=terrain[tab[j].getX()][tab[j].getY()].getSame();
		}
		return terr;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public Case getTerrain(int x, int y) {
		return this.terrain[x][y];
	}
}
