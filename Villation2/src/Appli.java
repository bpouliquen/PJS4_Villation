import map.Map;
import template.TemplateFighter;
import empire.Empire;
import territoire.Territoire;


public class Appli {
	public static void main(String[] args){
		Empire e1 = new Empire("E1");
        Empire e2 = new Empire("E2");

        Map.addEmpire(e1);
        e1.addTemplateFighter(new TemplateFighter("Soldat",200,10,156));
        Map.addEmpire(e2);

        Map.commande("0 create ville 1 24 40 Paris");
        Map.commande("0 create ville 2 20 36 Lyon");
        Map.commande("0 create ville 5 0 0 Tombouctou");

        Map.commande("0 create fighter 2 0 3 20 36");
        Map.commande("0 create fighter 1 0 4 24 40");

        Map.commande("0 refresh fighter position 3 21 36");
        Map.commande("0 refresh fighter caracteristic 4 1 1");

        Map.commande("0 destroy unit 5");

        System.out.println(Map.toStringStatic());
	}
}
