package graphicEngine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class TopPanel extends JPanel {
	private static final int hauteur=30;
	private static final int fontSize=15;
	private static final int fontPosition=23;
	private	JPopupMenu popupMenu;
	private Clip music;
	private static String textAide="Insérer une aide ICI";

	private int width;
	private boolean VilleEdition=false;
	private boolean Recoltable=false;
	private boolean Elevable=false;

	private static final long serialVersionUID = 1L;
	public String Prod, Science, /*Food,*/ Money, Horse, Iron;
	private int nbTour;
	private Image Imgprod, Imgscience, /*Imgfood,*/ Imgmoney, ImgChevaux, ImgFer;
	private static Font font;
	private Font btnFont;

	public TopPanel(int width){
		this.width=width;
		this.setLayout(new BorderLayout());

		Box b = Box.createHorizontalBox();
		b.add(styleBouton("Tour suivant"));
		b.add(styleBouton("AIDE"));
		b.add(styleBouton("MENU"));
		this.add(b, BorderLayout.EAST);

		this.setPreferredSize(new Dimension(width, hauteur));
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(128, 128, 104)));
		try
		{
			Imgprod = ImageIO.read(new File("./ressources/images/topPanel/Production.png"));
			Imgscience = ImageIO.read(new File("./ressources/images/topPanel/Sciences.png"));
			//Imgfood = ImageIO.read(new File("./ressources/images/topPanel/Food.png"));
			Imgmoney = ImageIO.read(new File("./ressources/images/topPanel/Money.png"));
			ImgChevaux = ImageIO.read(new File("./ressources/images/topPanel/Horse.png"));
			ImgFer = ImageIO.read(new File("./ressources/images/topPanel/Iron.png"));
			font = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/images/TrajanusBricks.ttf"));
			font = font.deriveFont(Font.TRUETYPE_FONT,fontSize);
		}
		catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
		Science="0";
		Money="0";
		Prod="0";
		Horse="0";
		Iron="0";
		//Food="0";
		nbTour=1;

		menuPopup();
		runMusic();
	}

	private void menuPopup() {
		JMenuItem menuFileConfig = new JMenuItem("Reglage");
		menuFileConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reglage");
			}
		});

		JMenuItem menuFileSave = new JMenuItem("Sauvegarder");
		menuFileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sauvegarder");
			}
		});
		
		final JMenuItem menuMusic = new JMenuItem("Musique : ON");
		menuMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuMusic.getText().equals("Musique : ON")){
					stopMusic();
					menuMusic.setText("Musique : OFF");
				}
				else  {
					runMusic();
					menuMusic.setText("Musique : ON");
				}	
			}
		});

		JMenuItem menuFileQuit = new JMenuItem("Quitter");
		menuFileQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
						"Etes-vous sûr de vouloir quitter le jeu ?", "Quitter Villation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		// Create a popup menu
		popupMenu = new JPopupMenu("Menu");
		popupMenu.add(menuFileConfig);
		popupMenu.add(menuFileSave );
		popupMenu.add(menuMusic);
		popupMenu.add(menuFileQuit);
	}

	public JButton styleBouton(final String nom){
		JButton v=new JButton(nom);
		v.setOpaque(false); //Permet de rendre le bouton transparent
		v.setContentAreaFilled(false);
		v.setBorderPainted(false);
		v.setFocusPainted(false);
		v.setForeground(new Color(128, 128, 104));
		v.setFont(font);
		this.btnFont = v.getFont();
		v.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(nom.equals("AIDE")){
					JOptionPane.showMessageDialog(null, textAide, "Aide", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(nom.equals("Tour suivant")){
					majTour(nbTour);
				}
				else {
					popupMenu.show( evt.getComponent(), evt.getX(), evt.getY() );
				}
			}
		});
		return v;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setFont(btnFont);
		g.setColor(new Color(128, 128, 104));
		FontMetrics fontMetrics = g.getFontMetrics();
		String tour = "Tour : "+nbTour;
		int tourPos=(width - 270) - fontMetrics.stringWidth(tour);
		g.drawString(tour, tourPos, fontPosition-4);

		g.setFont(font);
		g.drawImage(Imgmoney,0, 0, hauteur, hauteur, this);
		g.setColor(new Color(218, 140, 33));
		g.drawString(Money,hauteur+2, fontPosition);
		g.drawImage(Imgscience,hauteur*4, 0, hauteur, hauteur, this);
		g.setColor(new Color(16, 162, 61));
		g.drawString(Science,hauteur*5+2, fontPosition);
		int last=hauteur*8;
		if(VilleEdition){
			g.drawImage(Imgprod,last, 0, hauteur, hauteur, this);
			g.setColor(new Color(45, 182, 182));
			g.drawString(Prod,last+hauteur+2, fontPosition);
			last=last+hauteur*4;
			/*g.drawImage(Imgfood,last, 0, hauteur, hauteur, this);
			g.setColor(new Color(141, 135, 23));
			g.drawString(Food,last+hauteur+2, fontPosition);*/
		}
		if(Elevable){
			g.drawImage(ImgChevaux,last, 0, hauteur, hauteur, this);
			g.setColor(new Color(154, 83, 44));
			g.drawString(Horse,last+hauteur+2, fontPosition);
			last=last+hauteur*4;
		}
		if(Recoltable){
			g.drawImage(ImgFer,last, 0, hauteur, hauteur, this);
			g.setColor(new Color(131, 109, 82));
			g.drawString(Iron,last+hauteur+2, fontPosition);
			last=last+hauteur*4;
		}
	}

	public void villeEdition(){
		if(VilleEdition){
			VilleEdition=false;
		}
		else {
			VilleEdition=true;
		}
		repaint();
	}

	public void elevageMode(){
		if(Elevable){
			Elevable=false;
		}
		else {
			Elevable=true;
		}
		repaint();
	}
	
	public void recoltableMode(){
		if(Recoltable){
			Recoltable=false;
		}
		else {
			Recoltable=true;
		}
		repaint();
	}
	
	public String setValue(int val) {
		if(val>0){
			return "+"+val;
		} 
		else {
			return ""+val;
		}
	}

	public void majMoney(int val) {
		this.Money=setValue(val);
		repaint();
	}

	public void majProd(int val) {
		this.Prod=setValue(val);
		repaint();
	}

	public void majScience(int val) {
		this.Science=setValue(val);
		repaint();
	}

	public void majTour(int val) {
		this.nbTour+=1;
		repaint();
	}
	
	public void runMusic() {		
		try {
			File file = new File("./ressources/music/GameMusicSound.wav");

			music = AudioSystem.getClip();

			music.open(AudioSystem.getAudioInputStream(file));
			music.loop(Clip.LOOP_CONTINUOUSLY);
			music.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public void stopMusic() {
		music.stop();
	}
}
