package empire;

import java.util.ArrayList;
import java.util.List;

import template.TemplateBuilding;
import map.Map;
import territoire.Territoire;
import unit.Fighter;
import template.TemplateFighter;
import unit.Unit;
import unit.Ville;

public class Empire {
	private static int PV = 20;
	private static int ATK = 5;

	private String name;
	private Unit current=null;
	private List<Fighter> listefighter;
    private List<TemplateFighter> listetemplatefighter;
    private List<TemplateBuilding> listetemplatebuilding;
	private List<Ville> listeville;

	
	public Empire(String name){
		this.name = name;
		listefighter = new ArrayList<Fighter>();
		listeville= new ArrayList<Ville>();
		listetemplatefighter = new ArrayList<TemplateFighter>();
        listetemplatebuilding = new ArrayList<TemplateBuilding>();
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
	
	public void focus(Unit u){
		this.current=u;
	}
	
    public void addTemplateFighter(TemplateFighter tu){
        listetemplatefighter.add(tu);
    }

    public void addTemplateBuilding(TemplateBuilding tb){
        listetemplatebuilding.add(tb);
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

	public Territoire getTerritoire(int x,int y){
		for(Ville v: listeville){
			for(Territoire t: v.getTerritoires()){
				if(t.getX()==x && t.getY()==y){
					return t;
				}
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
