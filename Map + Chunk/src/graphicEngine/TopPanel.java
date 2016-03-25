package graphicEngine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	private static final int hauteur=30;
	private static final int fontSize=15;
	private static final int fontPosition=23;

	private static final long serialVersionUID = 1L;
	public String Prod, Science, Food, Money, nbTour;
	private Image Imgprod, Imgscience, Imgfood, Imgmoney;
	private static Font font;

	public TopPanel(int width){
		this.setPreferredSize(new Dimension(width, hauteur));
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(215, 199, 110)));
		try
		{
			Imgprod = ImageIO.read(new File("./images/topPanel/Production.png"));
			Imgscience = ImageIO.read(new File("./images/topPanel/Sciences.png"));
			Imgfood = ImageIO.read(new File("./images/topPanel/Food.png"));
			Imgmoney = ImageIO.read(new File("./images/topPanel/Money.png"));
			font = Font.createFont(Font.TRUETYPE_FONT, new File("./images/TrajanusBricks.ttf"));
			font = font.deriveFont(Font.TRUETYPE_FONT,fontSize);
		}
		catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
		Prod="8";
		Science="0";
		Food="0";
		Money="0";
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(font);
		g.drawImage(Imgprod,0, 0, hauteur, hauteur, this);
		g.setColor(Color.BLUE);
		g.drawString(Prod,hauteur+2, fontPosition);
		g.drawImage(Imgscience,hauteur*4, 0, hauteur, hauteur, this);
		g.setColor(Color.GREEN);
		g.drawString(Science,hauteur*5+2, fontPosition);
		g.drawImage(Imgfood,hauteur*8, 0, hauteur, hauteur, this);
		g.setColor(Color.YELLOW);
		g.drawString(Food,hauteur*9+2, fontPosition);
		g.drawImage(Imgmoney,hauteur*12, 0, hauteur, hauteur, this);
		g.setColor(Color.YELLOW);
		g.drawString(Money,hauteur*13+2, fontPosition);
	}
}
