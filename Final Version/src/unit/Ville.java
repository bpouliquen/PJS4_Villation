package unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import map.game;
import building.Building;
import building.Effet;
import empire.Empire;
import gameEngine.Case;
import gameEngine.Coordonnees;
import ressources.Commande;
import template.Template;
import template.TemplateBuilding;
import template.TemplateFighter;

public class Ville extends Unit{
	private static int DISTATK = 3;
	private static int ATK = 200;
	private static int PV = 2000;

	private String name;
	private List<Coordonnees> listecoor;
    private List<Building> listebuilding;
	private List<Effet> listeEffet;
	private Template produit = null;
	private int aFaire;

	private int or,production,science;
	
	public Ville(int id,Empire e,String name,int x, int y) {
		super(id,e,x, y,PV,ATK,DISTATK);
		this.name = name;
		listecoor = new ArrayList<Coordonnees>();
		listebuilding = new ArrayList<Building>();
		listeEffet = new ArrayList<Effet>();
	}

	public String toString(){
		String s="";
		s = s.concat("Ville("+x+","+y+") : PV="+pv+" ATK="+atk);
        if(this.produit!=null){
            s= s.concat(" En production: "+produit.getName()+" Reste a produire: "+this.aFaire);
        }
        s = s.concat("\n");
		return s;
	}

	public String getName(){ return this.name; }
	
	public void addCase(Coordonnees c){
		listecoor.add(c);
	}

	public void addBuilding(TemplateBuilding tb){
		Building temp = tb.getBuilding();
		for(Effet e: temp.getListeEffets()){
			listeEffet.add(e);
		}
		listebuilding.add(temp);
	}

	public List<Coordonnees> getCases(){
		return this.listecoor;
	}

	public void Production(Template t){
		this.produit = t;
	}

	public Commande clean(){
		this.produit=null;
		this.aFaire=-1;
		Commande c = new Commande(false);
		c.setAction("refresh");c.setCible("ville");c.setPrecision("vide");c.setId(this.getId());
		return c;
	}

	public void setCar(int pv,int atk){
		this.pv = pv;
		this.atk=atk;
	}

	public void setProduction(int aFaire,boolean fighter,int template){
		this.aFaire = aFaire;
		if(fighter) this.produit = this.loyaute.getTemplateFighter().get(template);
		else this.produit = this.loyaute.getTemplateBuilding().get(template);

	}
	
	public List<Commande> turn() throws IOException{
		List<Commande> listec = new ArrayList<Commande>();
		this.aFaire -= this.production;
		if(this.production<= 0){
			listec.addAll(create());
		}
		
		this.loyaute.addOr(this.or);
		this.loyaute.addScience(this.science);
		
		return listec;
	}

	
	public List<Commande> create() throws IOException{
		List<Commande> lc = new ArrayList<Commande>();
		if(this.loyaute.getTemplateFighter().contains((TemplateFighter) this.produit)) lc.add(this.createFighter());
		else lc.add(this.createBuilding());
		lc.add(this.clean());
		return lc;
	}
	
	public Commande createBuilding(){
		this.addBuilding((TemplateBuilding) this.produit);
		Commande c = new Commande(false);
		c.setAction("create");c.setCible("building");c.setId(this.getId());c.setTemplate(game.getIdTemplateBuilding((TemplateBuilding) this.produit));
		return c;
	}
	
	public Commande createFighter() throws IOException{
		int id = game.generateID();
		
		int template = game.getIdTemplateFighter((TemplateFighter) this.produit);
		game.createFighter(id,this.loyaute,this.getX(),this.getY(),template);
		Commande c = new Commande(false);
		c.setAction("create");c.setCible("fighter");c.setId(id);c.setX(x);c.setY(y);
		c.setTemplate(template);c.setId2(game.getIdEmpire(this.loyaute));
			
		
		return c;
	}
	
	public int getOr(){ return this.or; }

	public int getScience(){ return this.science; }

	public void getRessources(){
		this.or = 0;this.production = 0;this.science = 0;
		for(Coordonnees coor : listecoor){
			Case c = game.getCase(coor);
			this.or += c.getRessource().getOr();
			this.production += c.getRessource().getProd();
			this.science += c.getRessource().getScience();
		}
		
		int or_pourcentage = 0;
		int prod_pourcentage = 0;
		int science_pourcentage = 0;
		for(Effet e : listeEffet){
			if(e.isOr_simple()) this.or += e.getOr();
			else or_pourcentage += e.getOr();
			
			if(e.isProduction_simple()) this.production += e.getProduction();
			else prod_pourcentage += e.getProduction();
			
			if(e.isScience_simple()) this.science += e.getScience();
			else science_pourcentage += e.getScience();
		}
		
		this.or *= ((or_pourcentage+100)/100);
		this.production *= ((prod_pourcentage+100)/100);
		this.science *= ((science_pourcentage+100)/100);
	}
}
