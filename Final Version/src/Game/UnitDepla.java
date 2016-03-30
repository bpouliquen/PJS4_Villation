package Game;
import java.util.ArrayList;
import java.util.List;

import gameEngine.Coordonnees;
import gameEngine.IMap;
import gameEngine.Terrain;


public class UnitDepla {
	private static IMap map;
	private Coordonnees co;

	private int porteeDeplacement;
	
	public UnitDepla(int porteeDeplacement, Coordonnees co){
		this.porteeDeplacement=porteeDeplacement;
		this.co=co;
	}
	
	public static void setMap(IMap map){
		UnitDepla.map=map;
	}
	
	public List<Coordonnees> getDeplacement(){
		List<List<Coordonnees>> temp=new ArrayList<List<Coordonnees>>();
		for(int i=0; i<=this.porteeDeplacement; i++){
			temp.add(new ArrayList<Coordonnees>());
		}
		temp.get(0).add(this.co);
		boolean b=true;
		int i=0;
		Coordonnees[] coordTemp =null;
		Terrain[] caseTemp=null;
		System.out.println("recherche pour"+this.co.getX()+"/"+this.co.getY());
		while(b && i<this.porteeDeplacement){
			b=false;
			for(Coordonnees c:temp.get(i)){
				System.out.println("\t recherche par"+c.getX()+"/"+c.getY());
				coordTemp =c.getAutour();
				caseTemp=map.getFieldAround(coordTemp);
				for(int j=0; j<6;j++){
					System.out.println("\t\trecherche pour :"+coordTemp[j].getX()+"/"+coordTemp[j].getY());
					if(caseTemp[j].getModif().equals("Montagne")||caseTemp[j].getType().equals("Mer")){
						
					}
					else if (caseTemp[j].getModif().equals("nu")){
						if (i<this.porteeDeplacement){
							boolean ecrire=true;
							for (Coordonnees coor:temp.get(i+1)){
								if (coor.estSur(coordTemp[j])){
								ecrire=false;}
							}
							if(ecrire){
								temp.get(i+1).add(coordTemp[j]);
								System.out.println("\t\t écrit dans "+(i+1));
								b=true;
							}
						}
					}
					else{
						if (i+1<this.porteeDeplacement){
							boolean ecrire=true;
							for (Coordonnees coor:temp.get(i+2)){
								if (coor.estSur(coordTemp[j])){
									ecrire=false;}
							}
							if(ecrire){
								temp.get(i+2).add(coordTemp[j]);
								System.out.println("\t\t écrit dans "+(i+2));
								b=true;
							}
						}
					}
				}
			}
			i++;
		}
		List<Coordonnees> retour=new ArrayList<Coordonnees>();
		for (List<Coordonnees> a: temp){
			retour.addAll(a);
		}
		return retour;
	}
	
	public Coordonnees getCo() {
		return co;
	}

	public void setCo(Coordonnees co) {
		this.co = co;
	}
		
}
