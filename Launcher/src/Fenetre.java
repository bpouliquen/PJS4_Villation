package Launcher.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	public Fenetre(){
		//D�finit un titre pour notre fen�tre
		this.setTitle("Villation Launcher");
		//D�finit sa taille : 400 pixels de large et 600 pixels de haut
		this.setSize(400, 600);
		//Nous demandons maintenant � notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Retirer les contours et les boutons de contr�le
		this.setUndecorated(true);
		//Emp�che le redimensionnement de la fen�tre
		this.setResizable(false);

		JButton create=new Button("ButtonCreer.png", "ButtonCreerSurvol.png", "Creation");
		JButton join = new Button("ButtonJoin.png", "ButtonJoinSurvol.png", "Rejoindre");
		JButton about=new Button("ButtonPropos.png", "ButtonProposSurvol.png", "Propos");
		JButton quit=new Button("ButtonQuitter.png", "ButtonQuitterSurvol.png", "Quitter");
	

		Box boutons = Box.createVerticalBox();
		boutons.add(Box.createVerticalStrut(150));
		boutons.add(create);
		boutons.add(join);
		boutons.add(about);
		//Permet d'imposer une distance entre deux sous-composants.
		boutons.add(Box.createVerticalStrut(40));
		boutons.add(quit);

		JPanel container = new Panneau (new GridBagLayout ()); 
		//Couleur de fond de la fen�tre
		container.add (boutons, new GridBagConstraints (0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets (0,0,0,0), 0, 0));

		this.setContentPane(container);
		//Et enfin, la rendre visible     
		this.setVisible(true);
	}
}