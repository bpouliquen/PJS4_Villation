package template;

import empire.Empire;
import unit.Fighter;

public class TemplateFighter extends Template {
	private int pv;
	private int atk;
	private int distatk;
	private int distmove;

	public TemplateFighter(String name, int cost, int pv, int atk, int distatk,int distmove) {
		super(name,cost);
		this.pv = pv;
		this.atk = atk;
		this.distatk = distatk;
		this.distmove = distmove;
	}

	public String toString(){
		return this.getName()+" : PV="+pv+" ATK="+atk+" DATK="+atk+" DMOVE="+distmove+" Cost="+this.getCost();
	}

	public Fighter getFighter(int id,Empire e,int x,int y){
		return new Fighter(id,e,x,y,pv,atk,distatk,distmove);
	}
}
