package gameEngine;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
	private Coordonnées hautGauche;
	private Coordonnées basDroite;
	// a determiner
	private static final int longueurChunk=20;
	
	private List<TerritoireNat> territoire;
	
	public Chunk(){
		territoire=new ArrayList<TerritoireNat>();
	}
	public void addTerritoireNat(TerritoireNat t){
		territoire.add(t);
	}
	
	public Terrain[][] getTerrain(){
		Terrain[][] terr = new Terrain[longueurChunk][longueurChunk];
		for(int i=0;i<longueurChunk;i++){//parcours ligne
			for(int j=0;i<longueurChunk;){//parcours en colonne
				for (TerritoireNat t:territoire){
					Terrain temp=t.getTerr(i, j);
					if(temp!=null){
						terr[i][j]=temp;
					}
				}
			}
			
		}
		return terr;
	}
	
	
}
