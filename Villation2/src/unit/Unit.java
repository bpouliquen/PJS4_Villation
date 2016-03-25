package unit;

import empire.Empire;

public abstract class Unit{
	protected int id,x,y,pv,atk;
	protected Empire loyaute;
	
	public Unit(Empire e,int x,int y,int pv,int atk,int id){
		this.loyaute=e;
		this.x=x;
		this.y=y;
		this.pv=pv;
		this.atk=atk;
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
	
	public abstract String toString();
	
	public abstract int getDistMax();

}
