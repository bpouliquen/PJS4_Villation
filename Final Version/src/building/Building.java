package building;

import java.util.List;

/**
 * Created by benavide on 24/03/2016.
 */
public class Building {
    private String name;
    private List<Effet> listeEffets;

    public Building(String name, List<Effet> listeEffets) {
        this.name = name;
        this.listeEffets = listeEffets;
    }

    public String getName() {
        return name;
    }

    public List<Effet> getListeEffets() {
        return listeEffets;
    }
}
