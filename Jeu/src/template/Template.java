package template;

/**
 * Created by user on 24/03/2016.
 */
public class Template {
    private String name;
    private int cost;

    public Template(String name,int cost){
        this.name=name;
        this.cost=cost;
    }

    public String getName(){ return name; }

    public int getCost(){ return cost; }
}
