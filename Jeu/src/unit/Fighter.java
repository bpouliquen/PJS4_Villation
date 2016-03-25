package unit;

import empire.Empire;
import template.TemplateFighter;

public class Fighter extends Unit {
	public static int Dist = 5;
	public Fighter(Empire e,int x,int y,int pv,int atk,int id){
		super(e,x,y,pv,atk,id);
	}

	public Fighter(Empire e, int x,int y, TemplateFighter t,int id){
		super(e,x,y,t.getPv(),t.getAtk(),id);
	}
	
	@Override
	public int getDistMax() {
		return Dist;
	}

	public String toString(){
		String s="";
		s = s.concat("Soldat("+x+","+y+") : PV="+pv+" ATK="+atk+"\n");
		return s;
	}

	public void setPos(int x,int y){
		this.x = x;
		this.y = y;
	}

	public void setCar(int pv,int atk){
		this.pv = pv;
		this.atk= atk;
	}
}
