package gameEngine;

import java.io.Serializable;

public enum Ressource implements Serializable{

	CHARBON(0,2,1,"./ressources/images/ressources/Coal.png",""),
	OR(3,0,0,"./ressources/images/ressources/Gold.png",""),
	BLE(1,1,1,"./ressources/images/ressources/Wheat.png",""),
	VACHE(1,2,0,"./ressources/images/ressources/Cow.png",""),
	SOIE(2,1,0,"./ressources/images/ressources/Silk.png",""),
	VIN(2,-1,2,"./ressources/images/ressources/Wine.png",""),
	SEL(-1,2,2,"./ressources/images/ressources/Salt.png",""),
	FOURRURE(2,2,-1,"./ressources/images/ressources/Pelt.png",""),
	OASIS(2,1,2, "./ressources/images/ressources/Oasis.png", ""),
	
	FER(0,2,0,"./ressources/images/ressources/Iron.png",""),
	CHEVAL(0,2,0,"./ressources/images/ressources/Horse.png","");
	
	private int or;
	private int prod;
	private int science;
	private String urlSymb;
	private String urlMap;
	private Ressource(int or, int prod, int science, String urlSymb, String urlMap){
		this.setOr(or);
		this.setProd(prod);
		this.setScience(science);
		this.setUrlSymb(urlSymb);
		this.setUrlMap(urlMap);
	}
	public int getOr() {
		return or;
	}
	public void setOr(int or) {
		this.or = or;
	}
	public int getProd() {
		return prod;
	}
	public void setProd(int prod) {
		this.prod = prod;
	}
	public int getScience() {
		return science;
	}
	public void setScience(int science) {
		this.science = science;
	}
	public String getUrlSymb() {
		return urlSymb;
	}
	public void setUrlSymb(String urlSymb) {
		this.urlSymb = urlSymb;
	}
	public String getUrlMap() {
		return urlMap;
	}
	public void setUrlMap(String urlMap) {
		this.urlMap = urlMap;
	}
}
