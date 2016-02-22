package gameEngine;

import java.util.Iterator;
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
		if(co.estAcote(fronti�res.getFirst())&&co.estAcote(fronti�res.getLast())){
			
		}
		else{
			//v�rifier que les cases sont attenantes et comment elles changent la fronti�re
			boolean b=false;
			Iterator<Coordonn�es> i=fronti�res.iterator();
			int loop=0;
			while(i.hasNext()){
				if(co.estAcote(i.next())){
					b=true;
					break;
				}
				loop++;
			}
			//parcours � partir de ce point pour voir combien de cases sont attenantes.
			int loop2=loop;
			while(i.hasNext()){
				if(!co.estAcote(i.next()))
					break;
				loop2++;
			}
			//si j'ai trouv� un truc
			if(b){
				//ins�rer depuis loop et supprimer ceux du milieu
				this.insertBetween(loop, loop2,false, co);
			}
		}
		
	}
	private void insertBetween(int ind1, int ind2, boolean passeParOrigine, Coordonn�es co){
		if(passeParOrigine){
			int temp=ind2-ind1;
			if(temp==0){
				this.fronti�res.add(ind1+1, this.fronti�res.get(ind1));
				this.fronti�res.add(ind1+1, co);
			}
			else if(temp==1){
				this.fronti�res.add(ind1+1, co);
			}
			else if(temp<5){
				for(int i=1;i<temp;i++){
					this.fronti�res.remove(ind1+i);
				}
				this.fronti�res.add(ind1+1, co);
			}
			else{
				for(int i=1;i<temp;i++){
					this.fronti�res.remove(ind1+i);
				}
			}
		}
	}
	
}
