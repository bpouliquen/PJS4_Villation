package template;

import building.Building;
import building.Effet;

import java.util.List;

/**
 * Created by benavide on 24/03/2016.
 */
@SuppressWarnings("unused")
public class TemplateBuilding extends Template {
    private String name;
    private List<Effet> listeEffets;
    private int cost;

    public TemplateBuilding(String name,int cost,List<Effet> listeEffets){
        super(name,cost);
        this.listeEffets= listeEffets;
    }

    public String toString(){
        return name;
    }

    public Building getBuilding(){
        return new Building(name,listeEffets);
    }
}
