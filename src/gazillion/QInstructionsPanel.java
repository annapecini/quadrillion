package gazillion;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class QInstructionsPanel extends QPanel {

	/**
	 * Create the panel.
	 */
	public QInstructionsPanel(QPanel parent, QFrame frame) {
		super(parent, frame);
		setLayout(null);
				
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setPreferredSize(new Dimension(screenSize.width, screenSize.height));
	    
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(QInstructionsPanel.class.getResource("/img/instructionsPhoto.png")));
		lblNewLabel.setBounds(40, 70, screenSize.width, screenSize.height);
		add(lblNewLabel);
		
		JButton backButton = getBackButton();
		backButton.setBounds(21, 22, 126, 43);
		add(backButton);
	}
}
