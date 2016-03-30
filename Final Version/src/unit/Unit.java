package unit;

import empire.Empire;

public abstract class Unit{
	protected int id,x,y,pv,atk,distatk;
	protected Empire loyaute;
	
	public Unit(int id,Empire e,int x,int y,int pv,int atk,int distatk){
		this.loyaute=e;
		this.x=x;
		this.y=y;
		this.pv=pv;
		this.atk=atk;
		this.distatk=distatk;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public int getPv(){
		return pv;
	}
	
	public int getAtk() {
		return atk;
	}

	public int getDistatk(){
		return distatk;
	}
	
	public abstract String toString();
}
