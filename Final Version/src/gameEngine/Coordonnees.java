package gameEngine;

public class Coordonnees {
	private int x;//ligne
	private int y;//colonne
	private boolean pair;//si la colonne est paire, on est dans le creux de la vague
	
	public Coordonnees(int x, int y){
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
	public void setX(int x){
		this.x =x;
	}
	public void setY(int y){
		this.y=y;
	}
	public boolean isPair(){ return pair; }
	public boolean estSur(Coordonnees co){
		return(this.x==co.getX() && this.y==co.getY());
	}
	public Coordonnees[] getAutour(){
		Coordonnees[] tab=new Coordonnees[6];
		if (this.pair){
			tab[0]=new Coordonnees(this.x-1, this.y);
			tab[1]=new Coordonnees(this.x-1, this.y+1);
			tab[2]=new Coordonnees(this.x, this.y+1);
			tab[3]=new Coordonnees(this.x+1, this.y);
			tab[4]=new Coordonnees(this.x, this.y-1);
			tab[5]=new Coordonnees(this.x-1, this.y-1);
		}
		else{
			tab[0]=new Coordonnees(this.x-1, this.y);
			tab[1]=new Coordonnees(this.x, this.y+1);
			tab[2]=new Coordonnees(this.x+1, this.y+1);
			tab[3]=new Coordonnees(this.x+1, this.y);
			tab[4]=new Coordonnees(this.x+1, this.y-1);
			tab[5]=new Coordonnees(this.x, this.y-1);
		}
		return tab;
	}
	public boolean estAcote(Coordonnees co)throws Exception{
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
