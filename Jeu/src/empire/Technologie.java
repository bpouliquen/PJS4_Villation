package empire;

import template.TemplateBuilding;
import template.TemplateFighter;

import java.util.List;

/**
 * Created by benavide on 23/03/2016.
 */
public class Technologie {
    private String name;
    private int cost;
    private List<TemplateFighter> listefighter;
    private List<TemplateBuilding> listebuilding;
    private List<Bonus> listebonus;

    public Technologie(String name, int cost, List<TemplateFighter> listefighter, List<TemplateBuilding> listebuilding, List<Bonus> listebonus){
        this.name = name;
        this.cost = cost;
        this.listefighter = listefighter;
        this.listebuilding = listebuilding;
        this.listebonus = listebonus;
    }



}
