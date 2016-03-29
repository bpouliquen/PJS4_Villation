package launcher;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import serveur.Serveur;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.Color;


/**
 * Interface de creation de partie
 */
public class InterfaceCreerPartie extends MovableJFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JLabel lblNomPartie, lblMotDePasse, lblPort, lblNombreDeJoueurs;
	private JTextField textField_NomPartie, textField_Mdp, textField_Port, textField_nbJoueur;
	private JButton createButton, cancelButton;
	private JCheckBox chckbxPartieLocale;
	private Color labelColor = Color.BLACK;

	public InterfaceCreerPartie() {
		contentPanel = new JPanel();
		JPanel background=new BackgroundPanel("CréerPartieBackground.png");

		setTitle("Villation - Creer une partie");

		setBounds(100, 100, 410, 200);

		saisies();

		boutons();

		setContentPane(background);
		GroupLayout gl_contentPanel = miseEnPage();
		contentPanel.setLayout(gl_contentPanel);
		contentPanel.setOpaque(false);
		background.add(contentPanel);
		setVisible(true);
	}

	/**
	 * Gestion des saisies de la fenêtre
	 */
	public void saisies(){
		lblNomPartie = creerJLabel("Nom de la partie :");
		textField_NomPartie = new JTextField();
		textField_NomPartie.setColumns(10);

		lblMotDePasse = creerJLabel("Mot de passe (optionnel) : ");
		textField_Mdp = new JTextField();
		textField_Mdp.setColumns(10);

		lblPort = creerJLabel("Num\u00E9ro de port :");
		textField_Port = new JTextField();
		textField_Port.setText("2000");
		textField_Port.setColumns(8);
		textField_Port.addKeyListener(new ControleSaisieNombre());

		lblNombreDeJoueurs = creerJLabel("Nombre de joueurs max :");
		textField_nbJoueur = new JTextField();
		textField_nbJoueur.setColumns(10);
		textField_nbJoueur.addKeyListener(new ControleSaisieNombre());


		chckbxPartieLocale = new JCheckBox("Cr\u00E9er partie en local");
		chckbxPartieLocale.setForeground(labelColor);
		chckbxPartieLocale.setOpaque(false);
		chckbxPartieLocale.setFocusPainted(false);
	}

	public JLabel creerJLabel(String nom){
		JLabel jl = new JLabel(nom);
		jl.setForeground(labelColor);
		return jl;
	}

	/**
	 * Gestion des boutons de la fenêtre
	 */
	public void boutons(){
		createButton = new Button("ButtonCreer.png", "ButtonCreerSurvol.png", "Creer", null);
		createButton.setPreferredSize(new Dimension(85, 25));
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nomPartie=textField_NomPartie.getText();
				String port=textField_Port.getText();
				String mdp=textField_Mdp.getText();
				String nbJoueurs=textField_nbJoueur.getText();
				Boolean lan=chckbxPartieLocale.isSelected(); //Prevu pour être utilisé dans une classe
				if(nomPartie.equals("") || port.equals("")  || nbJoueurs.equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//Note : Appeler classe pour enregistrer les parametres de la partie, exemple : 
					//enregistrerPartie(nomPartie, port, mdp, nbJoueurs, lan);
					dispose();
					new Serveur(Integer.parseInt(port), Integer.parseInt(nbJoueurs), nomPartie);
				}
			}
		});

		cancelButton = new Button("ButtonAnnuler.png", "ButtonAnnulerSurvol.png", "Annuler", null); 
		cancelButton.setPreferredSize(new Dimension(85, 25));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher();
			}
		});
	}

	/**
	 * Fait la mise en page de la boite de dialogue
	 * @return
	 */
	public GroupLayout miseEnPage(){
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap(13, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPort)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_Port, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNombreDeJoueurs)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_nbJoueur, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(chckbxPartieLocale))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblNomPartie)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_NomPartie))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblMotDePasse)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_Mdp, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(createButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(42)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomPartie)
								.addComponent(textField_NomPartie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMotDePasse)
								.addComponent(textField_Mdp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPort)
								.addComponent(textField_Port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreDeJoueurs)
								.addComponent(textField_nbJoueur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxPartieLocale))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(createButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(100, Short.MAX_VALUE))
				);
		return gl_contentPanel;
	}
}
