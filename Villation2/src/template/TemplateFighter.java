package template;

public class TemplateFighter extends Template {
	private int pv,atk;
	
	public TemplateFighter(String name,int pv,int atk,int cost){
		super(name,cost);
		this.pv=pv;
		this.atk=atk;

	}
	
	public String toString(){
		return this.getName()+" : PV="+pv+" ATK="+atk+" Cost="+this.getCost();
	}

	public int getPv() {
		return pv;
	}

	public int getAtk() {
		return atk;
	}
}
