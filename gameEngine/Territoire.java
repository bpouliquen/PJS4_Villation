package gameEngine;

import java.util.LinkedList;

public class Territoire {
	private static IMap map;
	
	private LinkedList<Coordonnées> frontières;
	
	public Territoire(){
		frontières=new LinkedList<Coordonnées>();
	}
	public void etendreTerritoire(Coordonnées co) throws Exception{
		//vérifier que la case n'appartient à aucun territoire
		if (frontières==null)
			frontières.add(co);
		//vérifier que les cases sont attenantes et comment elles changent la frontière
		for(Coordonnées c: frontières){
			if(c.estAcote(co)){
				//faire traitement
				break;
			}
		}
	}
	
}
