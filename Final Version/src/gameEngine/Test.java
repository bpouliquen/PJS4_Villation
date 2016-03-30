package gameEngine;
import Game.UnitDepla;

public class Test {
public static void main (String[] args){
	Map map=new Map(37,18);
	System.out.println("bonjour !");
	UnitDepla u=new UnitDepla(2, new Coordonnees(10,10));
	UnitDepla.setMap(map);
	u.getDeplacement();
}
}
