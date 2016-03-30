package graphicEngine;
import gameEngine.Case;
import gameEngine.Coordonnees;
import gameEngine.Map;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings({ "serial", "unused" })
public class Fenetre extends JFrame {

	final static int EMPTY = 0;
	final static int BSIZE = 90; //board size.
	final static int HEXSIZE = 60;	//hex size in pixels
	final static int BORDERS = 15;  
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	public static boolean XYVertex=true;

	private Map carte;
	private UnitPanel panelUnit;

	public Fenetre (Map map) {
		this.carte = map;
		Paint p = new Paint(carte);
		initGame();
		createAndShowGUI(p);
	}

	private void createAndShowGUI(Paint p) {
		this.setTitle("Villation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width = (int)dimension.getWidth();
		ImageIcon icone = new ImageIcon("./ressources/images/VillationIconLarge.png");
		this.setIconImage(icone.getImage());
		
		//this.carte.setLongueur((int)width/51);
		//this.carte.setLargeur((int)height/65);

		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());

		TopPanel top=new TopPanel(width);
		content.add(top, BorderLayout.NORTH); 

		MapPanel panelDecor = new MapPanel(p);
		panelDecor.setLayout(new BorderLayout());

		SelecPanel panelSelec = new SelecPanel(p, height, width);
		panelDecor.add(panelSelec, BorderLayout.NORTH);
		
		panelUnit = new UnitPanel(p, panelSelec, height, width, this);
		panelSelec.add(panelUnit, BorderLayout.NORTH);
		content.add(panelDecor, BorderLayout.CENTER);

		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	public Map getCarte(){
		return this.carte;
	}
	
	void initGame(){

		Paint.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
		Paint.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		Paint.setBorders(BORDERS);

	}

	public static void setXYasVertex(boolean b) {
		XYVertex=b;
	}

	public void Visible() {
		this.setVisible(true);
	}
	
	public UnitPanel getPanelUnit(){ return panelUnit; }
	
	public Map getMap(){ return this.carte;}

}
