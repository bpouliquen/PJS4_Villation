package launcher;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Permet de controler les entrées clavier
 * @author 
 *
 */
public class ControleSaisieNombre extends KeyAdapter{

	public ControleSaisieNombre(){
		super();
	}
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char ch = e.getKeyChar();
		if (!isNumber(ch)) {
			e.consume();
		}		
	}

	private boolean isNumber(char ch){
		return ch >= '0' && ch <= '9';
	}
}
