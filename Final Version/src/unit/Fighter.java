package unit;

import empire.Empire;

@SuppressWarnings("unused")
public class Fighter extends Unit {
	private int distmove;
	public Fighter(int id,Empire e,int x,int y,int pv,int atk,int distatk,int distmove){
		super(id,e,x,y,pv,atk,distatk);
		this.distmove=distmove;
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
	
	public int getDistmove() {
		return distmove;
	}
}
