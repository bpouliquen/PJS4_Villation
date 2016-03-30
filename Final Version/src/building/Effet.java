package building;

/**
 * Created by user on 28/03/2016.
 */
public class Effet {
    private int or,science,production;
    private boolean or_simple,production_simple,science_simple;

    public Effet(int or,int production, int science, boolean or_simple,boolean production_simple,boolean science_simple) {
        this.or = or;
        this.production = production;
        this.science = science;
        this.or_simple = or_simple;
        this.production_simple = production_simple;
        this.science_simple = science_simple;
    }

	
    public int getOr() {
		return or;
	}

	public int getScience() {
		return science;
	}

	public int getProduction() {
		return production;
	}

	public boolean isOr_simple() {
		return or_simple;
	}

	public boolean isProduction_simple() {
		return production_simple;
	}

	public boolean isScience_simple() {
		return science_simple;
	}
}
