package ressources;

/**
 * Created by user on 28/03/2016.
 */
public class Commande {
    private String action;
    private String cible;
    private String precision;
    private String name;
    private int id,id2,x,y,template;
    private boolean fin;

    public Commande(boolean fin){
        this.fin = fin;
    }

    public String getAction() {
        return action;
    }

    public String getCible() {
        return cible;
    }

    public String getPrecision() {
        return precision;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getId2() {
        return id2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTemplate() {
        return template;
    }

    public boolean isFin() {
        return fin;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }
}
