package launcher;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.*;

/**
 * Launcher du Jeu
 *
 */
public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Clip music;

	public Launcher(){
		movableJFrame();
		//Définit un titre pour notre fenêtre
		this.setTitle("Villation - Launcher");
		//Définit sa taille : 400 pixels de large et 600 pixels de haut
		this.setSize(400, 600);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Retirer les contours et les boutons de contrôle
		this.setUndecorated(true);
		//Empêche le redimensionnement de la fenêtre
		this.setResizable(false);

		JPanel container = new BackgroundPanel ("Launcher.png"); 

		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		container.setLayout(null);

		JButton create = new Button("ButtonCreerP.png", "ButtonCreerPSurvol.png", "Creation", this);
		create.setBounds(50, 255, 300, 50);
		container.add(create);

		JButton join = new Button("ButtonJoin.png", "ButtonJoinSurvol.png", "Rejoindre", this);
		join.setBounds(50, 305, 300, 50);
		container.add(join);

		JButton about = new Button("ButtonPropos.png", "ButtonProposSurvol.png", "Propos", this);
		about.setBounds(50, 355, 300, 50);
		container.add(about);

		JButton quit = new Button("ButtonQuitter.png", "ButtonQuitterSurvol.png", "Quitter", this);
		quit.setBounds(50, 445, 300, 50);
		container.add(quit);

		JButton sound = new Button("Mute.png", "MuteOff.png", "Sound", this);
		sound.setBounds(340, 539, 30, 30);
		container.add(sound);

		this.setContentPane(container);
		//Et enfin, la rendre visible     
		this.setVisible(true);
		runMusic();
	}

	private void movableJFrame() {
		Point point = new Point();
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

	/**
	 * Ferme le launcher et arrete la musique
	 */
	public void fermer() {
		dispose();
		stopMusic();
	}

	public void runMusic() {		
		try {
			File file = new File("./ressources/music/LauncherWAV.wav");

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