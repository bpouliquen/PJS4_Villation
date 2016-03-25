package gameEngine;


public class Map implements IMap{

	private Chunk[][] terrain;

	public Map(){
		terrain=new Chunk[3][3];
		for(int i = 0; i < terrain.length; i++){
			for(int j = 0; j < terrain[i].length; j++){
				terrain[i][j]=new Chunk();
			}
		}
	}

	public Chunk[][] getTerrain(Coordonnées centreVue){
		int x=centreVue.getX()/Chunk.getLong();
		int y=centreVue.getY()/Chunk.getLarg();
		Chunk[][] temp=new Chunk[3][3];
		for(int i=-1; i<2; i++ ){
			for (int j=-1; j<2; j++){
				temp[i+1][j+1]=this.getChunk(x+i, y+j);
			}
		}	
		return temp;
	}

	private Chunk getChunk(int i, int j) {
		if (i>=0 && j>=0 && i<terrain.length && j<terrain[0].length){
			return terrain[i][j];
		}
		else
			return null;
	}

}
