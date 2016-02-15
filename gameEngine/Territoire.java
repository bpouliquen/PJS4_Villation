package gameEngine;

import java.util.LinkedList;

public class Territoire {
	private static IMap map;
	
	private LinkedList<Coordonn�es> fronti�res;
	
	public Territoire(){
		fronti�res=new LinkedList<Coordonn�es>();
	}
	public void etendreTerritoire(Coordonn�es co) throws Exception{
		//v�rifier que la case n'appartient � aucun territoire
		if (fronti�res==null)
			fronti�res.add(co);
		//v�rifier que les cases sont attenantes et comment elles changent la fronti�re
		for(Coordonn�es c: fronti�res){
			if(c.estAcote(co)){
				//faire traitement
				break;
			}
		}
	}
	
}
