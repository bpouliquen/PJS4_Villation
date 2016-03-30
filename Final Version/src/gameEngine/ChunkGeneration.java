package gameEngine;

import java.util.Random;

public class ChunkGeneration {

	public static void generer(Case[][] territoire) {
		for(int i = 0; i < territoire.length; i++){
			   for(int j = 0; j < territoire[i].length; j++){
			     if (i==0 ||j==0 || i==territoire.length-1 || j==territoire[0].length-1)
			    	 territoire[i][j]=new Case(ChunkGeneration.aleatoire());
			     else{
				     Random r=new Random();
				     int ran=r.nextInt(100); 
				     if (ran<15)
				    	 territoire[i][j]=new Case(territoire[i-1][j-1].getSame());
				     else if (ran<30)
				    	 territoire[i][j]=new Case(territoire[i-1][j].getSame());
				     else if (ran<45)
				    	 territoire[i][j]=new Case(territoire[i-1][j+1].getSame());
				     else if (ran<80)
				    	 territoire[i][j]=new Case(territoire[i][j-1].getSame());
				     else
				    	 territoire[i][j]=new Case(ChunkGeneration.aleatoire());
				    	 
			     }
			     //jet de dé pour savoir si il y a une ressource
			     
			   }
			}
		for(int i = 0; i < territoire.length; i++){
			territoire[i][0]=new Case(Terrain.PLAINEMONTAGNE);
			territoire[i][territoire[i].length-1]=new Case(Terrain.PLAINEMONTAGNE);
		}
		for(int i = 0; i < territoire[i].length; i++){
			territoire[0][i]=new Case(Terrain.PLAINEMONTAGNE);
			territoire[territoire.length-1][i]=new Case(Terrain.PLAINEMONTAGNE);
		}
	}
	
	
	private static Terrain aleatoire(){
		Random r=new Random();
	    int ran=r.nextInt(100);
	    if (ran<30)
	    	return Terrain.PLAINE;
	    else if (ran<40)
		    return Terrain.PLAINECOLLINE;
		else if (ran<48)
		    return Terrain.PLAINEMONTAGNE;
		else if (ran<60)
	    	return Terrain.PLAINEFORET;
	    
	    else if (ran<72)
	    	return Terrain.DESERT;
	    else if (ran<77)
	    	return Terrain.DESERTCOLLINE;
	    else if (ran<80)
	    	return Terrain.DESERTMONTAGNE;
	    
	    else if (ran<85)
	    	return Terrain.NEIGE;
	    else if (ran<89)
	    	return Terrain.NEIGEFORET;
	    else if (ran<91)
	    	return Terrain.NEIGEMONTAGNE;
	    else if (ran<95)
	    	return Terrain.NEIGECOLLINE;
	    
	    else
	    	return Terrain.MER;
	    
	}

}
