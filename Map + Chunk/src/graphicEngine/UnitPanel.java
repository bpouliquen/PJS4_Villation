package graphicEngine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;






import javax.swing.JPanel;


@SuppressWarnings("serial")
public class UnitPanel extends JPanel implements MouseListener, MouseMotionListener {

	final static Color COLOURBACK =  Color.darkGray;
	final static int BORDMOVEMAP = 65;
	final static int VITESSEMOVEMAP =3;

	private Graphics2D g2;
	private Paint p;
	private ArrayList<Zone> listUnit = new ArrayList<Zone>();
	private Fenetre f;

	public UnitPanel(Paint p, MapPanel panelPere, int height, int width, Fenetre f) {
		setBackground(COLOURBACK);
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.p = p;
		this.f = f;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);

		for (int i=0;i<listUnit.size();i++) {
			int x=listUnit.get(i).getX();
			int y=listUnit.get(i).getY();
			p.drawUn(x,y,g2);
		}
	}

	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}

	public void addUnit(int x, int y) {
		listUnit.add(new Zone (x,y,null));
		repaint();  
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = new Point(Paint.pxtoHex(e.getX(),e.getY()));
		if(verifPresence(p.x, p.y)){
			System.out.println("Déjà quelqu'un");
		}
		else {
			addUnit(p.x, p.y);
		}
	}

	private boolean verifPresence(int x, int y) {
		for (int i=0;i<listUnit.size();i++) {
			if(listUnit.get(i).getX()==x && listUnit.get(i).getY()==y){
				return true;
			}
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < BORDMOVEMAP || e.getY() < BORDMOVEMAP || e.getX() >= f.getWidth()-BORDMOVEMAP || e.getY() >= f.getHeight()-BORDMOVEMAP) {
			Point p = new Point(Paint.pxtoHex(e.getX(), e.getY()));
			this.f.majMap(p.x,p.y);
			System.out.println("X : " + e.getX() + " Y : " + e.getY());
			System.out.println("Tu est sorti de la fenetre");
		} else System.out.println("X : " + e.getX() + " Y : " + e.getY());System.out.println("Tu est dans la fenetre");
	}
}
