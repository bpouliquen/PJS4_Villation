package graphicEngine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {

	final static Color COLOURBACK =  Color.BLACK;

	private Graphics2D g2;
	private Paint p;

	public MapPanel(Paint p) {
		setBackground(COLOURBACK);
		this.p = p;
	}

	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		super.paintComponent(g2);
		try {
			p.drawPoly(g2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}
}
