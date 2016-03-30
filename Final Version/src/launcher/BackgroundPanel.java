package launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Permet de mettre une image de fond aux fenetres
 */
public class BackgroundPanel extends JPanel { 

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	
	public BackgroundPanel(String path) {
		super();
		try {
			backgroundImage = ImageIO.read(new File("./ressources/images/launcher/".concat(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void paintComponent(Graphics g){
			//Ajout du logo
			g.drawImage(backgroundImage, 0, 0, this);        
	}
}
