package gameEngine;

public class Coordonnées {
	private int x;//ligne
	private int y;//colonne
	private boolean pair;//si la colonne est paire, on est dans le creux de la vague

	public Coordonnées(int x, int y){
		this.x=x;
		this.y=y;
		this.pair=((y%2)==0);
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public boolean estSur(Coordonnées co){
		return(this.x==co.getX() && this.y==co.getY());
	}
	public boolean estAcote(Coordonnées co)throws Exception{
		boolean b=false;
		if(this.estSur(co)){
			throw new Exception("les deux cases sont sur le meme emplacement");
		}
		if (this.pair){
			if((this.x==co.getX()-1)&&(this.y==co.getY()))
				b=true;
			if((this.x==co.getX()-1)&&(this.y==co.getY()-1))
				b=true;
			if((this.x==co.getX()-1)&&(this.y==co.getY()+1))
				b=true;
			if((this.x==co.getX())&&(this.y==co.getY()-1))
				b=true;
			if((this.x==co.getX())&&(this.y==co.getY()+1))
				b=true;
			if((this.x==co.getX()+1)&&(this.y==co.getY()))
				b=true;

		}
		else{
			if((this.x==co.getX()-1)&&(this.y==co.getY()))
				b=true;
			if((this.x==co.getX())&&(this.y==co.getY()-1))
				b=true;
			if((this.x==co.getX())&&(this.y==co.getY()+1))
				b=true;
			if((this.x==co.getX()-1)&&(this.y==co.getY()-1))
				b=true;
			if((this.x==co.getX()-1)&&(this.y==co.getY()+1))
				b=true;
			if((this.x==co.getX()+1)&&(this.y==co.getY()))
				b=true;
		}

		return b;
	}
}
