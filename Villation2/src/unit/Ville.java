package unit;

import java.util.ArrayList;
import java.util.List;

import building.Building;
import empire.Empire;
import template.Template;
import template.TemplateFighter;
import territoire.Territoire;

public class Ville extends Unit{
	private String name;
	private List<Territoire> listeterritoire;
    private List<Building> listebuilding;
	private Template produit = null;
	private int aFaire;
	
	public Ville(String name,Empire e,int x, int y,int pv,int atk,int id) {
		super(e,x, y,pv,atk,id);
		this.name = name;
		listeterritoire = new ArrayList<Territoire>();
		listebuilding = new ArrayList<Building>();
	}

	public int getDistMax() {
		return 0;
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

	public void addTerritoire(Territoire t){
		listeterritoire.add(t);
	}

	public void addBuilding(Building b){ listebuilding.add(b); }

	public List<Territoire> getTerritoires(){
		return this.listeterritoire;
	}

	public void Production(Template t){
		this.produit = t;
	}

	public void clean(){
		this.produit=null;
		this.aFaire=-1;
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

}
