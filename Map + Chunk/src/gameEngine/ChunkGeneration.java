package gameEngine;

import java.util.Random;

public class ChunkGeneration {

	public static void generer(Terrain[][] territoire) {
		for(int i = 0; i < territoire.length; i++){
			for(int j = 0; j < territoire[i].length; j++){
				if (i==0 ||j==0 || i==territoire.length-1 || j==territoire[0].length-1)
					territoire[i][j]=ChunkGeneration.aleatoire();
				else{
					Random r=new Random();
					int ran=r.nextInt(100); 
					if (ran<20)
						territoire[i][j]=territoire[i-1][j-1].getSame();
					else if (ran<40)
						territoire[i][j]=territoire[i-1][j].getSame();
					else if (ran<60)
						territoire[i][j]=territoire[i-1][j+1].getSame();
					else if (ran<80)
						territoire[i][j]=territoire[i][j-1].getSame();
					else
						territoire[i][j]=ChunkGeneration.aleatoire();

				}
			}
		}

	}


	private static Terrain aleatoire(){
		Random r=new Random();
		int ran=r.nextInt(100);
		if (ran<10)
			return Terrain.PLAINE;
		else if (ran<20)
			return Terrain.PLAINEFORET;
		else if (ran<30)
			return Terrain.MER;
		else if (ran<38)
			return Terrain.DESERT;
		else if (ran<42)
			return Terrain.DESERTCOLLINE;
		else if (ran<46)
			return Terrain.DESERTMONTAGNE;
		else if (ran<50)
			return Terrain.NEIGEFORET;
		else if (ran<56)
			return Terrain.NEIGE;
		else if (ran<62)
			return Terrain.NEIGEMONTAGNE;
		else if (ran<68)
			return Terrain.NEIGECOLLINE;
		else if (ran<74)
			return Terrain.PLAINECOLLINE;
		else if (ran<78)
			return Terrain.PLAINEMONTAGNE;
		else if (ran<88)
			return Terrain.PLAINE;
		else if (ran<93)
			return Terrain.PLAINEFORET;
		else
			return Terrain.MER;
	}

}
