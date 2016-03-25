package territoire;

public class Territoire {
	private int x,y;
	private Amenagement a = null;
	
	public Territoire(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){return x;}
	
	public int getY(){return y;}

	public void setAmenagement(Amenagement a){
		this.a=a;
	}

	public Amenagement getAmenagement() { return a; }
}
