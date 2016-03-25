package graphicEngine;
import gameEngine.Chunk;
import gameEngine.Coordonnées;
import gameEngine.Map;
import gameEngine.Terrain;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{

	final static int EMPTY = 0;
	final static int BSIZE = 90; //board size.
	final static int HEXSIZE = 60;	//hex size in pixels
	final static int BORDERS = 15;  
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	public static boolean XYVertex=true;

	static int[][] board = new int[BSIZE][BSIZE];
	private Terrain[][] map = new Terrain[BSIZE][BSIZE];
	private Map carte = new Map();

	public Fenetre () {
		initMap();
		Paint p = new Paint(board, map);
		initGame();
		createAndShowGUI(p);
	}

	private void createAndShowGUI(Paint p) {
		this.setTitle("Villation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width = (int)dimension.getWidth();
		ImageIcon icone = new ImageIcon("./images/VillationIconLarge.png");
		this.setIconImage(icone.getImage());
		//LoadingPanel lp = new LoadingPanel();

		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());

		TopPanel top=new TopPanel(width);
		content.add(top, BorderLayout.NORTH); 

		MapPanel panelDecor = new MapPanel(p);
		panelDecor.setLayout(new BorderLayout());

		UnitPanel panelUnit = new UnitPanel(p, panelDecor, height, width, this);
		panelDecor.add(panelUnit, BorderLayout.NORTH);
		content.add(panelDecor, BorderLayout.CENTER);

		//content.add(lp);
		this.setSize(width,height/*(SCRSIZE1.23),SCRSIZE*/);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	void initGame(){

		Paint.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
		Paint.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		Paint.setBorders(BORDERS);

		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				board[i][j]=EMPTY;
			}
		}
	}

	private void initMap() {
		Chunk[][] c = carte.getTerrain(new Coordonnées(0, 0));
		this.map = c[1][1].getTerrain();
	}

	public void majMap(int x, int y) {
		Chunk[][] c = carte.getTerrain(new Coordonnées(x, y));
		this.map = c[1][1].getTerrain();
		repaint();
	}
	
	public static void setXYasVertex(boolean b) {
		XYVertex=b;
	}

	public void Visible() {
		this.setVisible(true);
	}

	public static int[][] getBoard(){
		return board;
	}
}
