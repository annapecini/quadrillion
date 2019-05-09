package gazillion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setPreferredSize(new Dimension(screenSize.width, screenSize.height));
	    
	    JButton btnNewButton = new JButton("");
	    btnNewButton.setBounds(550, 278, 236, 59);
	    final ImageIcon icon1 = new ImageIcon(MainMenu.class.getResource("/img/Treasure-Mode.png"));
		btnNewButton.setIcon(icon1);
	    add(btnNewButton);
	    
	    JButton btnNewButton_1 = new JButton("");
	    btnNewButton_1.setBounds(550, 325, 236, 59);
	    final ImageIcon icon2 = new ImageIcon(QMainMenu.class.getResource("/img/Ladder-Mode.png"));
		btnNewButton_1.setIcon(icon2);
	    add(btnNewButton_1);
	    
	    JButton btnNewButton_2 = new JButton("");
	    btnNewButton_2.setBounds(550, 379, 236, 48);
	    final ImageIcon icon3 = new ImageIcon(QMainMenu.class.getResource("/img/Levels-Mode.png"));
		btnNewButton_2.setIcon(icon3);
	    add(btnNewButton_2);
	    
	    JButton btnNewButton_3 = new JButton("");
	    btnNewButton_3.setBounds(550, 424, 236, 48);
	    final ImageIcon icon4 = new ImageIcon(QMainMenu.class.getResource("/img/Shop.png"));
		btnNewButton_3.setIcon(icon4);
	    add(btnNewButton_3);
	    
	    JButton btnNewButton_4 = new JButton("");
	    btnNewButton_4.setBounds(550, 464, 236, 48);
	    final ImageIcon icon5 = new ImageIcon(QMainMenu.class.getResource("/img/Settings.png"));
		btnNewButton_4.setIcon(icon5);
	    add(btnNewButton_4);
	    
	    JButton button = new JButton("");
	    button.setBounds(550, 502, 236, 48);
	    final ImageIcon icon6 = new ImageIcon(QMainMenu.class.getResource("/img/Credits.png"));
		button.setIcon(icon6);
	    add(button);
	    
	    JButton btnNewButton_5 = new JButton("");
	    btnNewButton_5.setIcon(new ImageIcon(MainMenu.class.getResource("/img/profile.png")));
	    btnNewButton_5.setBounds(550, 231, 236, 59);
	    add(btnNewButton_5);
	    
	    JButton btnNewButton_6 = new JButton("");
	    btnNewButton_6.setIcon(new ImageIcon(MainMenu.class.getResource("/img/Instructions.png")));
	    btnNewButton_6.setBounds(550, 545, 236, 48);
	    add(btnNewButton_6);
	    
	    
	}
}
