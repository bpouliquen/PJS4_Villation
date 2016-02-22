package gameEngine;

import java.util.Iterator;
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
		if(co.estAcote(frontières.getFirst())&&co.estAcote(frontières.getLast())){
			
		}
		else{
			//vérifier que les cases sont attenantes et comment elles changent la frontière
			boolean b=false;
			Iterator<Coordonnées> i=frontières.iterator();
			int loop=0;
			while(i.hasNext()){
				if(co.estAcote(i.next())){
					b=true;
					break;
				}
				loop++;
			}
			//parcours à partir de ce point pour voir combien de cases sont attenantes.
			int loop2=loop;
			while(i.hasNext()){
				if(!co.estAcote(i.next()))
					break;
				loop2++;
			}
			//si j'ai trouvé un truc
			if(b){
				//insérer depuis loop et supprimer ceux du milieu
				this.insertBetween(loop, loop2,false, co);
			}
		}
		
	}
	private void insertBetween(int ind1, int ind2, boolean passeParOrigine, Coordonnées co){
		if(passeParOrigine){
			int temp=ind2-ind1;
			if(temp==0){
				this.frontières.add(ind1+1, this.frontières.get(ind1));
				this.frontières.add(ind1+1, co);
			}
			else if(temp==1){
				this.frontières.add(ind1+1, co);
			}
			else if(temp<5){
				for(int i=1;i<temp;i++){
					this.frontières.remove(ind1+i);
				}
				this.frontières.add(ind1+1, co);
			}
			else{
				for(int i=1;i<temp;i++){
					this.frontières.remove(ind1+i);
				}
			}
		}
	}
	
}
