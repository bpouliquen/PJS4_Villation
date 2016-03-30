package gameEngine;

import java.io.Serializable;
import java.util.Random;

public enum Terrain implements Serializable{

	NEIGE("Neige", "nu"),
	NEIGEMONTAGNE("Neige", "Montagne"),
	NEIGECOLLINE("Neige", "Colline"),
	NEIGEFORET("Neige", "Foret"),
	PLAINE("Plaine", "nu"),
	PLAINEMONTAGNE("Plaine", "Montagne"),
	PLAINECOLLINE("Plaine", "Colline"),
	PLAINEFORET("Plaine", "Foret"),
	DESERT("Desert", "nu"),
	DESERTMONTAGNE("Desert", "Montagne"),
	DESERTCOLLINE("Desert", "Colline"),
	MER("Mer", "nu");
	
	private String type;
	private String modif;
	private Terrain(String type, String modific){
		this.type=type;
		this.modif=modific;
	}
	
	public String getType(){
		return this.type;
	}
	public String getModif(){
		return this.modif;
	}

	

	public Terrain getSame() {
		Random r=new Random();
		int i=r.nextInt(100);
		for (Terrain t: Terrain.values()){
			if( (t.getType().equals(this.getType()) && (this.getModif().equals(t.getModif()))) ){
				if (i<50)
					return t;
			}
			else if(t.getType().equals(this.getType()) && (t.getModif().equals("nu") )){
				if (i>=50 && i<70)
					return t;
			}
			else if(t.getType().equals(this.getType()) && (t.getModif().equals("Colline") )){
				if (i>=70 && i<80)
					return t;
			}
			else if(t.getType().equals(this.getType()) && (t.getModif().equals("Foret") )){
				if (i>=80 && i<90)
					return t;
			}
			else if(t.getType().equals(this.getType()) && (t.getModif().equals("Montagne") )){
				if (i>=90)
					return t;
			}
		}
		for (Terrain t: Terrain.values()){
			if( (t.getType().equals(this.getType()) && (t.getModif().equals(this.getModif())))){
				return t;
			}
		}
		return Terrain.MER;
	}
}
