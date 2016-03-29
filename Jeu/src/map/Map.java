package map;

import building.Building;
import territoire.Amenagement;
import territoire.Territoire;
import unit.Fighter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import empire.Empire;
import ressources.IOOStream;
import unit.Ville;

public final class Map {
	private static List<Empire> listeEmpire = new ArrayList<Empire>();
	private static List<Territoire> listet = new ArrayList<Territoire>();
	private static int current;
	private static IOOStream transmission;

	public static void addEmpire(Empire e){
		listeEmpire.add(e);
	}

    public static void addTerritoire(Territoire t){
        listet.add(t);
    }
	
	public static Fighter getFighter(int x,int y){
		for(Empire e: listeEmpire) {
			if (e.getFighter(x, y) != null)
				return e.getFighter(x,y);
			}
		return null;
	}

	public static Ville getVille(int x,int y){
		for(Empire e: listeEmpire) {
			if (e.getVille(x, y) != null)
				return e.getVille(x, y);
		}
		return null;
	}
	
	public static List<Territoire> territoire1Case(int x,int y){
		List<Territoire> envoye = new ArrayList<Territoire>();
		for(Territoire t: listet){
			int tx=t.getX();int ty=t.getY();
			if((tx==x && ty==y-1) || (tx==x && ty==y+1) || (tx==x-1 && ty==y) || (tx==x+1 && ty==y)){
				envoye.add(t);
			}
		}
		return envoye;
	}

	public static void commande(String commande){
		String[] parties = commande.split(" ");
		Empire e = listeEmpire.get(Integer.parseInt(parties[0]));
		if(parties[1].equals("create")){
			if(parties[2].equals("fighter")){
				int id_ville = Integer.parseInt(parties[3]);
				int template = Integer.parseInt(parties[4]);
				int id_fighter = Integer.parseInt(parties[5]);
				int x = Integer.parseInt(parties[6]);
				int y = Integer.parseInt(parties[7]);

				e.addFighter(new Fighter(e,x,y,e.getTemplateFighter().get(template),id_fighter));
				e.getVille(id_ville).clean();

			}
			else if(parties[2].equals("ville")){
				int id_ville = Integer.parseInt(parties[3]);
				int x = Integer.parseInt(parties[4]);
				int y = Integer.parseInt(parties[5]);
				String name = parties[6];

				e.addVille(new Ville(name,e,x,y,2000,200,id_ville));
			}
			else if(parties[2].equals("building")){
				int id_ville = Integer.parseInt(parties[3]);
				int template = Integer.parseInt(parties[4]);

				e.getVille(id_ville).addBuilding(new Building(e.getTemplateBuilding().get(template).getName()));
				e.getVille(id_ville).clean();

			}
			else{
				int id_ville = Integer.parseInt(parties[3]);
				int x = Integer.parseInt(parties[4]);
				int y = Integer.parseInt(parties[5]);
				String name = parties[6];

				e.getTerritoire(x,y).setAmenagement(new Amenagement("name"));
				e.getVille(id_ville).clean();

			}
		}
		else if(parties[1].equals("refresh")){
			if(parties[2].equals("fighter")){
				if(parties[3].equals("position")){
					int id_fighter = Integer.parseInt(parties[4]);
					int x = Integer.parseInt(parties[5]);
					int y = Integer.parseInt(parties[6]);

					e.getFighter(id_fighter).setPos(x,y);
				}
				else{
					int id_fighter = Integer.parseInt(parties[4]);
					int pv = Integer.parseInt(parties[5]);
					int atk = Integer.parseInt(parties[6]);

					e.getFighter(id_fighter).setCar(pv,atk);
				}
			}
			else{
				if(parties[3].equals("production")) {
					int id_ville = Integer.parseInt(parties[4]);
					int aFaire = Integer.parseInt(parties[5]);
					int bool = Integer.parseInt(parties[6]);
					int template = Integer.parseInt(parties[7]);

					e.getVille(id_ville).setProduction(aFaire,(bool==1),template);
				}
				else {
					int id_ville = Integer.parseInt(parties[4]);
					int pv = Integer.parseInt(parties[5]);
					int atk = Integer.parseInt(parties[6]);

					e.getVille(id_ville).setCar(pv, atk);
				}
			}
		}
		else{
			if(parties[2].equals("unit")){
				int id = Integer.parseInt(parties[3]);

				e.destroy(id);
			}
			else{
				int x = Integer.parseInt(parties[3]);
				int y = Integer.parseInt(parties[4]);

				e.getTerritoire(x,y).setAmenagement(null);
			}

		}
	}

	public static String toStringStatic(){
		String s = "";
		for(Empire e: listeEmpire){
			s=s.concat(e.toString()+"\n");
		}
		return s;
	}

	public static boolean setupTransmission(IOOStream ioos){
		transmission = ioos;
		transmissionCommande();
		return true;
	}
	public static void transmissionCommande(){
		while(true){
			String commande = null;
			try {
				commande = transmission.readObject().toString();
			} catch (ClassNotFoundException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			if(commande!=null){
				if(commande.equals("tour")) monTour();
				if(commande.equals("exit")) break;
				Map.commande(commande);
			}
		}
	}
	
	public static void monTour(){
		
	}
}
