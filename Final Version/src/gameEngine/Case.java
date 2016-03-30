package gameEngine;

import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
public class Case implements Serializable{
	private Terrain terr;
	private Ressource ress;
	
	public Case (Terrain terrain){
		this.terr=terrain;
		creationRessource(terr);
	}

	private void creationRessource(Terrain terrain) {
		// assigne une ressource selon le terrain si ce n'est ni de la mer ni une montagne
		if (terrain!=Terrain.MER && terrain !=Terrain.NEIGEMONTAGNE && terrain != Terrain.DESERTMONTAGNE && terrain !=Terrain.PLAINEMONTAGNE){
		Random r=new Random();
		int i=r.nextInt(100);
		if (i<=5){
			i=r.nextInt();
			if (i<10)
				this.ress=Ressource.FER;
			else if (i<20)
				this.ress=Ressource.CHEVAL;
			else {
				if (terrain==Terrain.DESERT || terrain ==Terrain.DESERTCOLLINE){
					if (i<60)
						this.ress=Ressource.OASIS;
					else if ((i<70))
						this.ress=Ressource.SEL;
					else if (i<80)
						this.ress=Ressource.OR;
					else if (i<90)
						this.ress=Ressource.CHARBON;
				}
				else if (terrain==Terrain.PLAINE || terrain==Terrain.NEIGE){
					if (i<35)
						this.ress=Ressource.BLE;
					else if (i<50)
						this.ress=Ressource.VACHE;
					else if (i<65)
						this.ress=Ressource.VIN;
					else if (i<70)
						this.ress=Ressource.CHARBON;
					else if (i<75)
						this.ress=Ressource.OR;
					else if (i<80)
						this.ress=Ressource.SOIE;
					else if (i<85)
						this.ress=Ressource.SEL;
					else if (i<90)
						this.ress=Ressource.FOURRURE;
					else if (i<95)
						this.ress=Ressource.BLE;
					else
						this.ress=Ressource.VACHE;
				}
				else if (terrain==Terrain.PLAINECOLLINE || terrain==Terrain.NEIGECOLLINE){
					if (i<35)
						this.ress=Ressource.OR;
					else if (i<50)
						this.ress=Ressource.CHARBON;
					else if (i<65)
						this.ress=Ressource.SEL;
					else if (i<70)
						this.ress=Ressource.BLE;
					else if (i<75)
						this.ress=Ressource.VACHE;
					else if (i<80)
						this.ress=Ressource.SOIE;
					else if (i<85)
						this.ress=Ressource.VIN;
					else if (i<90)
						this.ress=Ressource.FOURRURE;
					else if (i<95)
						this.ress=Ressource.BLE;
					else
						this.ress=Ressource.VACHE;
				}
				else if (terrain==Terrain.PLAINEFORET || terrain==Terrain.NEIGEFORET){
					if (i<45)
						this.ress=Ressource.SOIE;
					else if (i<70)
						this.ress=Ressource.FOURRURE;
					else if (i<80)
						this.ress=Ressource.CHARBON;
					else if (i<90)
						this.ress=Ressource.OR;
					else
						this.ress=Ressource.SEL;
				}
			}
		}
		}
	}

	public Terrain getSame() {
		// TODO Auto-generated method stub
		return terr;
	}
	public Ressource getRessource(){
		return this.ress;
	}

}
