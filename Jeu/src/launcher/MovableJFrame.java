package launcher;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * Permet de rendre les fenetres movable
 */
public class MovableJFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public MovableJFrame() {
		movable();
		//Termine le processus lorsqu'on clique sur la croix rouge
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Retirer les contours et les boutons de contrôle
		setUndecorated(true);
		//Empêche le redimensionnement de la fenêtre
		setResizable(false);
		ImageIcon icone = new ImageIcon("./ressources/images/graphic/VillationIconLarge.png");
		this.setIconImage(icone.getImage());
	}

	private void movable() {
		final Point point = new Point();
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				if(!e.isMetaDown()){
					point.x = e.getX();
					point.y = e.getY();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(!e.isMetaDown()){
					Point p = getLocation();
					setLocation(p.x + e.getX() - point.x,
							p.y + e.getY() - point.y);
				}
			}
		});
	}
}
