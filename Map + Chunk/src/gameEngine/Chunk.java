package gameEngine;


public class Chunk {
	// a determiner
	private static final int longueurChunk=38;
	private static final int largeurChunk=17;

	private Terrain[][] territoire;

	public Chunk(){
		territoire=new Terrain[longueurChunk][largeurChunk];
		ChunkGeneration.generer(territoire);
	}

	public Terrain[][] getTerrain(){
		return territoire;
	}

	public static int getLong(){
		return longueurChunk;
	}
	public static int getLarg(){
		return largeurChunk;
	}


}
