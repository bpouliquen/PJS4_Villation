package map;

import ressources.Commande;
import template.TemplateBuilding;
import template.TemplateFighter;
import unit.Fighter;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import empire.Empire;
import gameEngine.Case;
import gameEngine.Coordonnees;
import gameEngine.Map;
import graphicEngine.UnitPanel;
import graphicEngine.Zone;
import unit.Ville;

@SuppressWarnings("unused")
public final class game {
	private static int TAILLEMAX = 50;
	private static List<Empire> listeEmpire = new ArrayList<Empire>();
	private static Case[][] tableau = new Case[TAILLEMAX][TAILLEMAX]; 
	private static int id_empire;
	private static Map map;
	private static UnitPanel uP;
	private static boolean serveur;
	private static Image img;
	private static List<Commande> listecommande = new ArrayList<Commande>();
	
	private static List<TemplateFighter> listetf = new ArrayList<TemplateFighter>();
	private static List<TemplateBuilding> listetb = new ArrayList<TemplateBuilding>();

	public static void setUP(UnitPanel uP1){ uP=uP1; }
	
	public static void setMap(Map map1){ map=map1; }
	
	public static void addEmpire(Empire e){
		listeEmpire.add(e);
	}
	
	public static Case getCase(Coordonnees c){
		return getCase(c.getX(),c.getY());
	}
	
	public static Case getCase(int x,int y){
		return tableau[x][y];
	}
	
	public static Fighter getFighter(int x,int y){
		for(Empire e: listeEmpire) {
			if (e.getFighter(x, y) != null)
				return e.getFighter(x,y);
			}
		return null;
	}

	public static Fighter getFighter(int id){
		for(Empire e: listeEmpire) {
			if (e.getFighter(id) != null)
				return e.getFighter(id);
		}
		return null;
	}

	public static Ville getVille(int x,int y){
		for(Empire e: listeEmpire) {
			if (e.getVille(x, y) != null)
				return e.getVille(x, y);
		}
		return null;
	}

	public static Ville getVille(int id){
		for(Empire e: listeEmpire) {
			if (e.getVille(id) != null)
				return e.getVille(id);
		}
		return null;
	}

	public static List<Case> territoire1Case(int x,int y){
		List<Case> resultats = new ArrayList<Case>();
		resultats.add(getCase(x-1,y));
		resultats.add(getCase(x+1,y));
		resultats.add(getCase(x,y-1));
		resultats.add(getCase(x,y+1));
		resultats.add(getCase(x-1,y+1));
		return resultats;
	}

	public static String toStringStatic(){
		String s = "";
		for(Empire e: listeEmpire){
			s=s.concat(e.toString()+"\n");
		}
		return s;
	}
	
	public static List<Commande> serveur(Commande c){
		List<Commande> resultats = new ArrayList<Commande>();

		return resultats;
	}

	public static void client(List<Commande> listec){
		for(Commande c : listec){
			
		}

	}

	public static int getIdEmpire(Empire e){
		return listeEmpire.indexOf(e);
	}
	
	public static int getIdTemplateFighter(TemplateFighter tf){
		return listetf.indexOf(tf);
	}
	
	public static int getIdTemplateBuilding(TemplateBuilding tb){
		return listetb.indexOf(tb);
	}
	
	public static Commande createCity(Empire e,int x,int y, String name){
		int id = generateID();
		createCity(id,e,x,y,name);
		Commande c = new Commande(false);
		c.setAction("create");
		c.setCible("ville");
		c.setId(id);
		c.setX(x);c.setY(y);
		c.setId2(getIdEmpire(e));
		return c;
	}
	
	public static void createCity(int id,Empire e,int x,int y, String name){
		e.addVille(new Ville(id,e,name,x,y));
		uP.getListCity().add(new Zone(x,y,img));
	}
	
	public static void createFighter(int id,Empire e,int x,int y,int template) throws IOException{
		e.addFighter(listetf.get(template).getFighter(id, e, x, y));
	}
	
	public static void createFighter(Empire e,int x,int y,int template) throws IOException{
		int id = generateID();
		createFighter(id,e,x,y,template);
		Commande c = new Commande(false);
		c.setAction("create");c.setCible("fighter");c.setId(id);c.setX(x);c.setY(y);c.setId2(getIdEmpire(e));c.setTemplate(template);
		listecommande.add(c);
	}

	public static int generateID(){
		Random r = new Random();
		while(true) {
			int id = r.nextInt(1000000);
			if (getFighter(id) == null && getVille(id) == null) return id;
		}
	}
	
	public static void monTour(){
		
	}

	public static void attackFighter(int id,int id2){
		Fighter f = getFighter(id);
		Fighter f2 = getFighter(id2);
		f2.setCar(f2.getPv()-f.getAtk(),f2.getAtk());
		f.setCar(f.getPv()-f2.getAtk(),f.getAtk());
	}

	public static void attackVille(int id,int id2){
		Fighter f = getFighter(id);
		Ville f2 = getVille(id2);
		f2.setCar(f2.getPv()-f.getAtk(),f2.getAtk());
		f.setCar(f.getPv()-f2.getAtk(),f.getAtk());
	}

	public static void attack(int id,int x,int y){
		Fighter f = getFighter(id);
		Ville v2 = getVille(x,y);
		Fighter f2 = getFighter(x,y);
		if(f!=null){
			if(v2!=null){
				attackVille(id,v2.getId());
			}else if(f2!=null){
				attackFighter(id,f2.getId());
			}else{
				f.setPos(x,y);
			}

		}
	}

	public static void setServeur(boolean serveur1){
		serveur=serveur1;
	}
	
	public static List<Coordonnees> getZone(Coordonnees c,int taille){
		if(taille<=0) return null;
		List<Coordonnees> r = new ArrayList<Coordonnees>();
		List<Coordonnees> n = new ArrayList<Coordonnees>();
		List<Coordonnees> nn;
		n.add(c);
		for(int i=0;i<taille;i++){
			nn = new ArrayList<Coordonnees>();
			for(Coordonnees coor : n){
				if(coor.isPair()){
					Coordonnees test = new Coordonnees(coor.getX()-1, coor.getY());
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX()-1, coor.getY()+1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test=new Coordonnees(coor.getX()+1, coor.getY()-1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test=new Coordonnees(coor.getX()+1, coor.getY());
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test=new Coordonnees(coor.getX(), coor.getY()-1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test=new Coordonnees(coor.getX()-1, coor.getY()-1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);	
				}else{
					Coordonnees test = new Coordonnees(coor.getX()-1, coor.getY());
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX(), coor.getY()+1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX()+1, coor.getY()+1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX()+1, coor.getY());
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX()-1, coor.getY()+1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
					test = new Coordonnees(coor.getX(), coor.getY()-1);
					if(!(r.contains(test) || n.contains(test) || nn.contains(test))) nn.add(test);
				}
			}
			r.addAll(n);
			n = nn;
		}
		r.addAll(n);
		return r;
	}

}
