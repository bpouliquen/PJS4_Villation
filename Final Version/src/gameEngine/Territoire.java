package gameEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Territoire {
	private static IMap map;
	
	private LinkedList<Coordonnees> frontières;
	
	public Territoire(){
		frontières=new LinkedList<Coordonnees>();
	}
	public boolean contientCase(Coordonnees co){
		//
		//important
		//
		//ListIterator
		List<Coordonnees> l=new ArrayList<Coordonnees>();
		ListIterator<Coordonnees> iterat=frontières.listIterator();
		while (iterat.hasNext()){
			//
			//gérer les cas du premier et du dernier de la liste !!!!!
			//
			Coordonnees temp=iterat.next();
			//est-ce que l'élément est sur la colonne de co ?
			if (temp.getX()==co.getX()){
				l.add(temp);
			//est-ce que l'élément est une corne ? (on ne repasse pas par lui, on est d'un seul côté de l'axe deux côtés de l'axe)
				iterat.previous();
				Coordonnees prev=iterat.previous();
				iterat.next();
				Coordonnees nex=iterat.next();
				try {
					if (prev.estAcote(nex)){
						if ((nex.getX()==temp.getX())||(prev.getX()==temp.getX()))
							l.add(temp);
					}
				} catch (Exception e) {//mauvais usage très probable de l'exception
					// on arrive là si nex et prev sont une même case
					l.add(temp);
				}
			}
		}
		//regrouper par paires !!
		//on prend la paire qui nous intéresse, on regarde en ligne
		return false;
	}
	public void etendreTerritoire(Coordonnees co) throws Exception{
		//vérifier que la case n'appartient à aucun territoire
		if (frontières==null)
			frontières.add(co);
		if(co.estAcote(frontières.getFirst())&&co.estAcote(frontières.getLast())){
			Iterator<Coordonnees> i=frontières.iterator();
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
			Iterator<Coordonnees> i=frontières.iterator();
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
	private void insertBetweenLastFirst(int ind1, int ind2, Coordonnees co){
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
	private void insertBetween(int ind1, int ind2, Coordonnees co){
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
	public static IMap getMap() {
		return map;
	}
	public static void setMap(IMap map) {
		Territoire.map = map;
	}
	
}
