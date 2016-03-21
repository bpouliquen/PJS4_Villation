package gameEngine;

import java.util.Iterator;
import java.util.LinkedList;

public class Territoire {
	private static IMap map;
	
	private LinkedList<Coordonnées> frontières;
	
	public Territoire(){
		frontières=new LinkedList<Coordonnées>();
	}
	public boolean contientCase(Coordonnées co){
		boolean b=false;
		//
		//important
		//
		return b;
	}
	public void etendreTerritoire(Coordonnées co) throws Exception{
		//vérifier que la case n'appartient à aucun territoire
		if (frontières==null)
			frontières.add(co);
		if(co.estAcote(frontières.getFirst())&&co.estAcote(frontières.getLast())){
			Iterator<Coordonnées> i=frontières.iterator();
			int loop=0;
			while(i.hasNext()){
				if(!co.estAcote(i.next())){
					break;
				}
				loop++;
			}
			//parcours à partir de ce point pour voir combien de cases sont attenantes.
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
				this.insertBetween(loop, loop2, co);
			}
			else{
				//throw une erreur car la case ne touche pas la frontière.
				throw new Exception("La case ne touche pas la frontière");
			}
		}
		
	}
	private void insertBetweenLastFirst(int ind1, int ind2, Coordonnées co){
		int temp=((this.frontières.size())-ind2)+ind1;
		if(temp==1){
			this.frontières.add(ind2+1, co);
		}
		else if(temp<5){
			//si temp==2, on doit en supprimer 1, si il vaut 3, 2 ...
			temp--;
			//tout supprimer entre ind2 et ind1
			while(ind2+1<this.frontières.size()){
				this.frontières.removeLast();
				temp--;
			}
			while(temp>0){
				this.frontières.removeFirst();
				temp--;
			}
			this.frontières.add(co);
		}
		else{
			temp--;
			//tout supprimer entre ind2 et ind1
			while(ind2+1<this.frontières.size()){
				this.frontières.removeLast();
				temp--;
			}
			while(temp>0){
				this.frontières.removeFirst();
				temp--;
			}
		}
	}
	private void insertBetween(int ind1, int ind2, Coordonnées co){
			int temp=ind2-ind1;
			if(temp==0){
				this.frontières.add(ind1+1, this.frontières.get(ind1));
				this.frontières.add(ind1+1, co);
			}
			else if(temp==1){
				this.frontières.add(ind1+1, co);
			}
			//pas des for bouffon mais des while
			else if(temp<5){
				temp--;
				while(temp>0){
					this.frontières.remove(ind1+1);
					temp--;
				}
				this.frontières.add(ind1+1, co);
			}
			else{
				temp--;
				while(temp>0){
					this.frontières.remove(ind1+1);
					temp--;
				}
			}
		}
	
}
