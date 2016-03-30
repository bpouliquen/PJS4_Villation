package empire;
import template.TemplateBuilding;
import template.TemplateFighter;

import java.util.List;

/**
 * Created by benavide on 23/03/2016.
 */
@SuppressWarnings("unused")
public class Technologie {
    private String name;
    private int cost;
    private List<TemplateFighter> listefighter;
    private List<TemplateBuilding> listebuilding;
	private List<Bonus> listebonus;

    public Technologie(String name, int cost, List<TemplateFighter> listefighter, List<TemplateBuilding> listebuilding){
        this.name = name;
        this.cost = cost;
        this.listefighter = listefighter;
        this.listebuilding = listebuilding;
        this.listebonus = null;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public List<TemplateFighter> getListefighter() {
        return listefighter;
    }

    public List<TemplateBuilding> getListebuilding() {
        return listebuilding;
    }
}
