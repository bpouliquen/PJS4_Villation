package launcher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import serveur.Serveur;

import java.awt.Font;
import java.util.List;

/**
 * Interface qui s'ouvre quand on rejoint une partie où qu'on en heberge une
 * (salon d'attente)
 */
public class InterfaceRejoindrePartie extends MovableJFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel, background;
	private boolean host = false;
	private JLabel partieNameLabel;
	private JScrollPane scroll;
	private JButton readyButton, cancelButton;
	private String port;
	@SuppressWarnings("rawtypes")
	private JList liste;
	Color labelColor = Color.BLACK;
	private Serveur serveur;

	public InterfaceRejoindrePartie(String nom, String port, Serveur se) {
		serveur = se;
		contentPanel = new JPanel();
		background = new BackgroundPanel("RejoindrePartieBackground.png");
		setTitle("Villation - Salon partie");

		if (port != null) {
			this.port = port;
			this.host = true;
		}

		setBounds(100, 100, 380, 170);
		background.add(contentPanel);

		info(nom);

		boutons();

		GroupLayout gl_contentPanel;
		if (!host) {
			gl_contentPanel = joinMode();
		} else {
			gl_contentPanel = hostMode();
		}

		readyButton.setPreferredSize(new Dimension(85, 25));

		JLabel lblListeJoueurs = new JLabel("Liste Joueurs");
		lblListeJoueurs.setHorizontalAlignment(SwingConstants.CENTER);
		scroll.setColumnHeaderView(lblListeJoueurs);
		contentPanel.setLayout(gl_contentPanel);
		contentPanel.setOpaque(false);
		setContentPane(background);
		setVisible(true);

	}

	private void info(String nom) {
		partieNameLabel = new JLabel(nom);
		partieNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		partieNameLabel.setForeground(labelColor);
		partieNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * Gestion des boutons (readyButton différent suivant le mode)
	 */
	public void boutons() {
		cancelButton = new Button("ButtonAnnuler.png", "ButtonAnnulerSurvol.png", "Annuler", null);
		cancelButton.setPreferredSize(new Dimension(85, 25));
	}

	@SuppressWarnings("rawtypes")
	private void initJlist() {
		liste = new JList();
		scroll = new JScrollPane(liste);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	/**
	 * A appeler à chaque changement dans la liste des joueurs et active le
	 * bouton lancer ou non
	 */
	@SuppressWarnings("unchecked")
	public void remplirListeJoueur(List<Joueur> listeJoueur) { // On recup la
																// liste des
																// joueurs
																// depuis le
																// serveur ?

		liste.setListData(listeJoueur.toArray());

		liste.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			/**
			 * Permet de changer la couleur du joueur pour savoir si il est prêt
			 * ou non
			 */
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// Assumes the stuff in the list has a pretty toString
				setText(value.toString());
				// On change la couleur du texte en vert si le joueur est prêt,
				// sino rouge
				if (listeJoueur.get(index).isPret()) {
					setForeground(new Color(51, 153, 102));
				} else {
					setForeground(Color.RED);
				}
				return this;
			}
		});
		/*
		 * On verifie si tout le monde est prêt pour activer le bouton lancer
		 */
		if (host) {
			readyButton.setEnabled(true);
			for (Joueur j : listeJoueur) {
				if (!j.isPret() || listeJoueur.size() != serveur.getNbJoueurs()) {
					readyButton.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Mise en page de la fenetre quand on est en mode Herbergement de partie
	 * 
	 * @return le layout
	 */
	private GroupLayout hostMode() {
		// readyButton = new JButton("Lancer");
		readyButton = new Button("ButtonLancer.png", "ButtonLancerSurvol.png", "Lancer", null);
		readyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(serveur).start();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher();
			}
		});

		initJlist();

		JLabel IPLabel = new JLabel("Votre IP :");
		IPLabel.setForeground(labelColor);
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		JLabel nIPLabel = new JLabel(ip);
		nIPLabel.setForeground(labelColor);
		JLabel PortLabel = new JLabel("Votre port :");
		PortLabel.setForeground(labelColor);
		JLabel nPortLabel = new JLabel(port);
		nPortLabel.setForeground(labelColor);
		GroupLayout host_contentPanel = new GroupLayout(contentPanel);
		host_contentPanel.setHorizontalGroup(host_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(host_contentPanel.createSequentialGroup().addContainerGap().addGroup(host_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(partieNameLabel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
						.addGroup(host_contentPanel.createSequentialGroup()
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(host_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(host_contentPanel.createSequentialGroup().addComponent(IPLabel)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(nIPLabel))
										.addGroup(host_contentPanel.createSequentialGroup().addComponent(PortLabel)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(nPortLabel))
										.addGroup(host_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(readyButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(cancelButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
						.addContainerGap()));
		host_contentPanel
				.setVerticalGroup(host_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(host_contentPanel
						.createSequentialGroup().addGap(4).addComponent(partieNameLabel).addPreferredGap(
								ComponentPlacement.UNRELATED)
						.addGroup(host_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(host_contentPanel.createSequentialGroup()
										.addGroup(host_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(IPLabel).addComponent(nIPLabel))
										.addGap(7)
										.addGroup(host_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(PortLabel).addComponent(nPortLabel))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(readyButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(cancelButton,
												GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, Short.MAX_VALUE)));
		return host_contentPanel;
	}

	/**
	 * Mise en page de la fenetre quand on est en mode Rejoindre une partie
	 * 
	 * @return le layout
	 */
	private GroupLayout joinMode() {
		readyButton = new Button("ButtonPasPret.png", "ButtonPasPretSurvol.png", "Prêt", null);
		readyButton.setText("Pas Prêt");
		readyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (readyButton.getText().equals("Prêt")) {
					// readyButton = new Button("ButtonPasPret.png",
					// "ButtonPasPret.png", "Pas prêt", null);
					((Button) readyButton).setFond("ButtonPasPret.png", "ButtonPasPretSurvol.png");
					readyButton.setText("Pas prêt");
					// appeler fonction pour dire au serveur qu'on est pas prêt
				} else {
					((Button) readyButton).setFond("ButtonPret.png", "ButtonPretSurvol.png");
					readyButton.setText("Prêt");
					// appeler fonction pour dire au serveur qu'on est prêt
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher();
			}
		});

		initJlist();

		GroupLayout join_contentPanel = new GroupLayout(contentPanel);
		join_contentPanel.setHorizontalGroup(join_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(join_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(join_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(partieNameLabel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addGroup(join_contentPanel.createSequentialGroup()
										.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(join_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(readyButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(cancelButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)))
						.addContainerGap()));
		join_contentPanel.setVerticalGroup(join_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(join_contentPanel.createSequentialGroup().addGap(4).addComponent(partieNameLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(join_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										join_contentPanel.createSequentialGroup()
												.addComponent(readyButton, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(cancelButton,
														GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, Short.MAX_VALUE)));
		return join_contentPanel;
	}
}
