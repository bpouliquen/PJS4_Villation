package graphicEngine;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoadingPanel extends JPanel {

	public LoadingPanel () {
		this.setBackground(Color.BLACK);
		JLabel image = new JLabel(new ImageIcon("Loading.png"));
		this.add(image);
	}
}
