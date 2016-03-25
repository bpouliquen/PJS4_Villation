package launcher;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Main {
	public static void main(String[] args) {
		new Fenetre();   
		runMusic();
	}

	public static void runMusic() {						
		try (FileInputStream audioIn = new FileInputStream("./ressources/music/launcher.mp3")) {
			Player clip = new Player(audioIn);
			clip.play();
		} catch (IOException | JavaLayerException e1) {
			e1.printStackTrace();
		}
	}
}       

/*fen.addWindowListener(new WindowAdapter() {
@Override
public void windowOpened(WindowEvent e) {
	// on doit utiliser un thread pour éviter de bloquer l'IHM
	new Thread() {
		public void run() {						
			try (FileInputStream audioIn = new FileInputStream("launcher.mp3")) {
				Player clip = new Player(audioIn);
				clip.play();
			} catch (IOException | JavaLayerException e1) {
				e1.printStackTrace();
			}
		};
	}.start();
}
});*/

