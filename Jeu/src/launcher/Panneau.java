package launcher;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel { 
	private static final long serialVersionUID = 1L;

	public Panneau(GridBagLayout gridBagLayout) {
		super(gridBagLayout);
		//this.setBackground(Color.LIGHT_GRAY);
	}

	public void paintComponent(Graphics g){
		//g.setColor(Color.BLACK);
		try {
			//Ajout du logo
			Image img = ImageIO.read(new File("./ressources/images/launcher/Launcher.png"));
			g.drawImage(img, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}         
	} 
	
	/*
	 * Permet de centrer le logo au centre
	 */
	/*public int getPositionWidthLogo(BufferedImage img){
		int widthImg = img.getWidth();
		int widthPan = this.getWidth();
		int pos=(widthPan-widthImg)/2;
		return pos;
	}*/
}
