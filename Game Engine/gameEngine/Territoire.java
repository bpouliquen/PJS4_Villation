package gameEngine;

import java.util.Iterator;
import java.util.LinkedList;

public class Territoire {
	private static IMap map;
	
	private LinkedList<Coordonn�es> fronti�res;
	
	public Territoire(){
		fronti�res=new LinkedList<Coordonn�es>();
	}
	public boolean contientCase(Coordonn�es co){
		boolean b=false;
		//
		//important
		//
		return b;
	}
	public void etendreTerritoire(Coordonn�es co) throws Exception{
		//v�rifier que la case n'appartient � aucun territoire
		if (fronti�res==null)
			fronti�res.add(co);
		if(co.estAcote(fronti�res.getFirst())&&co.estAcote(fronti�res.getLast())){
			Iterator<Coordonn�es> i=fronti�res.iterator();
			int loop=0;
			while(i.hasNext()){
				if(!co.estAcote(i.next())){
					break;
				}
				loop++;
			}
			//parcours � partir de ce point pour voir combien de cases sont attenantes.
			int loop2=loop;
			//parcours par la fin serait plus rapide que parcourir tout le tableau
			while(i.hasNext()){
				if(co.estAcote(i.next()))
					break;
				loop2++;
			}
			this.insertBetweenLastFirst(loop, loop2, co);
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
				this.insertBetween(loop, loop2, co);
			}
			else{
				//throw une erreur car la case ne touche pas la fronti�re.
				throw new Exception("La case ne touche pas la fronti�re");
			}
		}
		
	}
	private void insertBetweenLastFirst(int ind1, int ind2, Coordonn�es co){
		int temp=((this.fronti�res.size())-ind2)+ind1;
		if(temp==1){
			this.fronti�res.add(ind2+1, co);
		}
		else if(temp<5){
			//si temp==2, on doit en supprimer 1, si il vaut 3, 2 ...
			temp--;
			//tout supprimer entre ind2 et ind1
			while(ind2+1<this.fronti�res.size()){
				this.fronti�res.removeLast();
				temp--;
			}
			while(temp>0){
				this.fronti�res.removeFirst();
				temp--;
			}
			this.fronti�res.add(co);
		}
		else{
			temp--;
			//tout supprimer entre ind2 et ind1
			while(ind2+1<this.fronti�res.size()){
				this.fronti�res.removeLast();
				temp--;
			}
			while(temp>0){
				this.fronti�res.removeFirst();
				temp--;
			}
		}
	}
	private void insertBetween(int ind1, int ind2, Coordonn�es co){
			int temp=ind2-ind1;
			if(temp==0){
				this.fronti�res.add(ind1+1, this.fronti�res.get(ind1));
				this.fronti�res.add(ind1+1, co);
			}
			else if(temp==1){
				this.fronti�res.add(ind1+1, co);
			}
			//pas des for bouffon mais des while
			else if(temp<5){
				temp--;
				while(temp>0){
					this.fronti�res.remove(ind1+1);
					temp--;
				}
				this.fronti�res.add(ind1+1, co);
			}
			else{
				temp--;
				while(temp>0){
					this.fronti�res.remove(ind1+1);
					temp--;
				}
			}
		}
	
}
