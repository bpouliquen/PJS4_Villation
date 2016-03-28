package launcher;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import client.Client;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Interface pour rejoindre une LAN
 */
public class InterfaceRejoindreLAN extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel, background;
	private JFormattedTextField IPtextField;
	private JTextField PorttextField;
	private Color labelColor = Color.BLACK;

	public InterfaceRejoindreLAN() {
		contentPanel  = new JPanel();
		background=new BackgroundPanel("ConnexionBackground.png");
		setTitle("Villation - Rejoindre LAN");
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setContentPane(background);
		setTitle("Rejoindre LAN");
		setBounds(100, 100, 315, 80);
		background.setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.add(contentPanel, BorderLayout.CENTER);

		saisies();

		boutons();

		contentPanel.setOpaque(false);
		setVisible(true);
	}

	/**
	 * Gestion des boutons
	 */
	private void boutons() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		background.add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setOpaque(false);

		JButton okButton = new Button("ButtonRejoindre.png", "ButtonRejoindreSurvol.png", "OK", null);
		okButton.setPreferredSize(new Dimension(85, 25));
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ip=IPtextField.getText();
				String port=PorttextField.getText();
				if(ip.equals("") || port.equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ip=ip.trim();
					//<Provisoire>
					boolean checkPartie=true; //Note : Appeler fonction pour vérifier si la partie existe
					//boolean checkPartie=partieExiste(ip, port);
					//</Provisoire>

					if(checkPartie){
						//<Provsoire>
						String nomPartie="Hello team"; //Remplacer par un getter pour avoir le nom de la partie
						//</Provsoire>
						
						//new Client("127.0.0.1", 2000);
						
						new InterfaceRejoindrePartie(nomPartie, null).setLocationRelativeTo(null);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Partie inexistante", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new Button("ButtonAnnuler.png", "ButtonAnnulerSurvol.png", "Annuler", null); 
		cancelButton.setPreferredSize(new Dimension(85, 25));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher();
			}
		});
		buttonPane.add(cancelButton);
	}

	/**
	 * Gestion des saisies
	 */
	public void saisies(){
		JLabel IPLabel = new JLabel("IP :");
		IPLabel.setForeground(labelColor);
		contentPanel.add(IPLabel);

		MaskFormatter formatter;
		try {
			formatter = new MaskFormatter("###.###.###.###");
			formatter.setPlaceholderCharacter('0'); 
			IPtextField = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPanel.add(IPtextField);
		IPtextField.setColumns(10);
		//IPtextField.addKeyListener(new KeyAdapterNombre());

		JLabel PortLabel = new JLabel("Port :");
		PortLabel.setForeground(labelColor);
		contentPanel.add(PortLabel);

		PorttextField = new JTextField();
		contentPanel.add(PorttextField);
		PorttextField.setColumns(5);
		PorttextField.addKeyListener(new ControleSaisieNombre());
	}
}
