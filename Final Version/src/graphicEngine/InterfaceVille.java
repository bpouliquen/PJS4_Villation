package graphicEngine;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

import launcher.Button;

public class InterfaceVille extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblActualProd, labelOr, labelScience, labelProd, labelNbTour;
	@SuppressWarnings("rawtypes")
	private JList liste;
	private int hauteur = 30;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public InterfaceVille() {
		setUndecorated(true);
		setBounds(100, 100, 210, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(128, 128, 104)));
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 210, 30);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblActualProd = new JLabel("Production en cours");
		lblActualProd.setForeground(Color.WHITE);
		lblActualProd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActualProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualProd.setBounds(10, 214, 190, 30);
		contentPanel.add(lblActualProd);

		liste = new JList();
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste.setFont(new Font("Arial", Font.BOLD, 17));
		liste.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = liste.locationToIndex(evt.getPoint()); //Récupère l'index
					setActualProd(index, liste.getSelectedValue().toString());
				}
			}
		});

		JScrollPane scroll = new JScrollPane(liste);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 41, 130, 167);
		liste.setOpaque(false);
		scroll.setOpaque(false);
		//contentPanel.setOpaque(false);
		contentPanel.add(scroll);

		ImageIcon i = new ImageIcon("./ressources/images/ville/Money30x30.png");
		JLabel iconOr = new JLabel(i);
		iconOr.setBounds(0, 0, hauteur, hauteur);
		panel.add(iconOr);

		labelOr = new JLabel("0");
		labelOr.setForeground(Color.WHITE);
		labelOr.setBounds(32, 0, hauteur, hauteur);
		panel.add(labelOr);

		i = new ImageIcon("./ressources/images/ville/Sciences30x30.png");
		JLabel iconScience = new JLabel(i);
		iconScience.setBounds(60, 0, hauteur, hauteur);
		panel.add(iconScience);

		labelScience = new JLabel("0");
		labelScience.setForeground(Color.WHITE);
		labelScience.setBounds(92, 0, hauteur, hauteur);
		panel.add(labelScience);

		i = new ImageIcon("./ressources/images/ville/Production30x30.png");
		JLabel iconProd = new JLabel(i);
		iconProd.setBounds(120, 0, hauteur, hauteur);
		panel.add(iconProd);

		labelProd = new JLabel("0");
		labelProd.setForeground(Color.WHITE);
		labelProd.setBounds(152, 0, 30, 30);
		panel.add(labelProd);



		JLabel lblTour1 = new JLabel("Nb tour(s)");
		lblTour1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTour1.setForeground(Color.WHITE);
		lblTour1.setBounds(141, 98, 69, 14);
		contentPanel.add(lblTour1);

		JLabel lblTour2 = new JLabel("restant(s) :");
		lblTour2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTour2.setForeground(Color.WHITE);
		lblTour2.setBounds(141, 111, 69, 14);
		contentPanel.add(lblTour2);

		labelNbTour = new JLabel("0");
		labelNbTour.setHorizontalAlignment(SwingConstants.CENTER);
		labelNbTour.setForeground(Color.WHITE);
		labelNbTour.setBounds(141, 132, 69, 14);
		contentPanel.add(labelNbTour);

		JButton btnClose = new Button("ButtonFermer.png", "ButtonFermerSurvol.png", "Creation", null);
		btnClose.setBounds(180, 0, 30, 30);
		panel.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});


		//<provisoire>
		String[] data = {"Chevalier 5", "Guerrier 10", "Prod 3", "Prod 4"};
		remplirListe(data);
		//</provisoire>
	}

	public void setActualProd(int i, String nom) {
		lblActualProd.setText(nom);
		//Appeler la fonction necessaire 
	}

	public void setNbTourRestant(int i) {
		labelProd.setText(Integer.toString(i));
	}

	public void setOr(int i) {
		String val=setValue(i);
		labelOr.setText(val);
	}

	public void setScience(int i) {
		String val=setValue(i);
		labelScience.setText(val);
	}

	public void setIron(int i) {
		String val=setValue(i);
		labelProd.setText(val);
	}

	public String setValue(int val) {
		if(val>0){
			return "+"+val;
		} 
		else {
			return ""+val;
		}
	}

	@SuppressWarnings("unchecked")
	public void remplirListe(String[] listeProd){
		liste.setListData(listeProd);
	}
}
