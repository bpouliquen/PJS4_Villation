package empire;

import java.util.ArrayList;
import java.util.List;
import template.TemplateBuilding;
import unit.Fighter;
import template.TemplateFighter;
import unit.Unit;
import unit.Ville;

@SuppressWarnings("unused")
public class Empire {
	private static int PV = 20;
	private static int ATK = 5;
	private String name;
	private List<Fighter> listefighter;
	private List<Ville> listeville;
	private List<TemplateFighter> listetemplatefighter;
    private List<TemplateBuilding> listetemplatebuilding;
	private List<Technologie> listetechnologie;
	private Technologie rechercheActuelle;
	private int rechercheRestante;
	private int or;

	
	public Empire(String name){
		this.name = name;
		this.listefighter = new ArrayList<Fighter>();
		this.listeville= new ArrayList<Ville>();
		this.listetemplatefighter = new ArrayList<TemplateFighter>();
		this.listetemplatebuilding = new ArrayList<TemplateBuilding>();
		this.rechercheActuelle = null;
		this.rechercheRestante = 0;
	}
	
	public void addOr(int or){
		this.or += or;
	}
	
	public void addScience(int science){
		this.rechercheRestante -= science;
	}
	
	public List<TemplateFighter> getTemplateFighter(){
		return listetemplatefighter;
	}

    public List<TemplateBuilding> getTemplateBuilding(){
        return listetemplatebuilding;
    }

	public List<Ville> getVilles() {
		return listeville;
	}

	public void addTechnologie(Technologie t){
		for(TemplateFighter tf : t.getListefighter()){
			listetemplatefighter.add(tf);
		}

		for(TemplateBuilding tb : t.getListebuilding()){
			listetemplatebuilding.add(tb);
		}

		listetechnologie.add(t);
	}

	public void addFighter(Fighter u){
		listefighter.add(u);
	}
	
	public void addVille(Ville v){
		this.listeville.add(v);
	}
	
	public String toString(){
		String s="";
		s = s.concat("Liste des Unites de "+name+":\n");
        s = s.concat("Liste des Combattants:\n");
		for(Unit u : listefighter){
			s=s.concat(u.toString());
		}
        s = s.concat("Liste des Villes:\n");
        for(Unit u : this.listeville){
            s=s.concat(u.toString());
        }


		return s;
	}

	public Fighter getFighter(int x,int y){
		for(Fighter f: listefighter){
			if(f.getX()==x && f.getY()==y){
				return f;
			}
		}
		return null;
	}

	public Ville getVille(int x,int y){
		for(Ville v: listeville){
			if(v.getX()==x && v.getY()==y){
				return v;
			}
		}
		return null;
	}

	public Fighter getFighter(int id){
		for(Fighter f: listefighter){
			if(f.getId()==id){
				return f;
			}
		}
		return null;
	}

	public Ville getVille(int id){
		for(Ville v: listeville){
			if(v.getId()==id){
				return v;
			}
		}
		return null;
	}

	public void destroy(int id){
		Fighter f = this.getFighter(id);
		if(f!=null){
			this.listefighter.remove(f);
		}else{
			this.listeville.remove(this.getVille(id));
		}
	}
}
