import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton implements MouseListener{

	private String fondButton; //Fond de base
	private String survolButton; //Fond lors du survol
	private Image img;
	private String type;

	public Button(String fondButton, String survolButton, String type){
		super();
		fondButton="images/".concat(fondButton);
		this.fondButton=fondButton;
		this.survolButton="images/".concat(survolButton);
		this.type=type;
		try {
			img = ImageIO.read(new File(fondButton));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setMinimumSize(new Dimension(300,50)); //Taille du bouton
		this.setPreferredSize(new Dimension(300,50));
		this.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		this.setOpaque(false); //Permet de rendre le bouton transparent
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (type)
		{
		  case "Creation":
		    System.out.println("Créer partie");
		    break;
		  case "Rejoindre":
		    System.out.println("Rejoindre partie");
		    break;
		  case "Propos":
		    System.out.println("A propos");
		    break;
		  default:
			  System.exit(0);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/*
	 * Change le fond du bouton lors du survol
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		try {
			img = ImageIO.read(new File(survolButton));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Remet l'image de fond de base après un survol
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		try {
			img = ImageIO.read(new File(fondButton));
		} catch (IOException ep) {
			ep.printStackTrace();
		}

	}

}
