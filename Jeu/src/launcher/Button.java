package launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Gestion de tout les boutons du launcher
 */
public class Button extends JButton implements MouseListener{


	private static final long serialVersionUID = 1L;
	private String fondButton; //Fond de base
	private String survolButton; //Fond lors du survol
	private Image img, imgFond, imgSurvol;
	private String type;
	private Launcher fen;
	private boolean mute;
	File file;

	public Button(String fondButton, String survolButton, String type, Launcher fen){
		super();
		file = new File("./ressources/music/clickHover.wav");
		this.fen=fen;
		if(type.equals("Mute")) {
			this.mute=false;
		}
		setFond(fondButton, survolButton);
		this.type=type;
		this.setOpaque(false); //Permet de rendre le bouton transparent
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);

		this.addMouseListener(this);
	}


	public void setFond(String fondB, String survolB){
		String path="./ressources/images/launcher/";
		this.fondButton=path.concat(fondB);
		this.survolButton=path.concat(survolB);
		try {
			imgFond = ImageIO.read(new File(fondButton));
			imgSurvol = ImageIO.read(new File(survolButton));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.img=imgFond;

	}

	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (type)
		{
		case "Creation":
			new InterfaceCreerPartie().setLocationRelativeTo(null);
			fen.fermer();
			break;
		case "Rejoindre":
			/*String[] choix = {"En Ligne", "LAN", "Annuler"};
			new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, 
					"Quel type de partie voulez-vous rejoindre ?",
					"Rejoindre Partie", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[2]);
			if(option == JOptionPane.YES_OPTION) {
				//Appeler la fonction pour rejoindre un partie en ligne
				fen.fermer();
			}
			else if (option == JOptionPane.NO_OPTION){*/
				new InterfaceRejoindreLAN().setLocationRelativeTo(null);
				fen.fermer();
			//}
			break;
		case "Propos":
			JOptionPane.showMessageDialog(null, "Projet de PJS4", "A propos", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "Sound":
			Image temp=imgSurvol;
			imgSurvol=imgFond;
			imgFond=temp;
			if(mute){
				mute=false;
				fen.runMusic();
			}
			else {
				mute=true;
				fen.stopMusic();
			}
			break;
		case "Quitter":
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Change le fond du bouton lors du survol
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		img=imgSurvol;
		hoverSound();
	}

	/**
	 * Remet l'image de fond de base après un survol
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		img=imgFond;
	}

	/**
	 * Son lors du survol d'un bouton
	 */
	public void hoverSound() {		
		try {
			Clip music = AudioSystem.getClip();

			music.open(AudioSystem.getAudioInputStream(file));
			music.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

}
