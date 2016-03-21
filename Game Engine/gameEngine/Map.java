package gameEngine;

import java.util.ArrayList;
import java.util.List;

public class Map implements IMap{

	private List<Chunk> terrain;
	
	public Map(){
		this.terrain=new ArrayList<Chunk>();
	}
	
}
