package gazillion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
//	private static final int TIME_PRICE = 5;
//    private static final int HEALTH_PRICE = 5;
//    private static final int HINT_PRICE = 5;

    private JPanel timePanel;
    private JPanel healthPanel;
    private JPanel hintPanel;
    
    private JPanel profilePanel;
    
    private JLabel timeLabel;
    private JLabel healthLabel;
    private JLabel hintLabel;
    
    private JLabel timeIconLabel;
    private JLabel hintIconLabel;
    private JLabel healthIconLabel;
    
    private JLabel coinIconLabel1;    
    private JLabel coinIconLabel2;
    private JLabel coinIconLabel3;
    
    private JButton backButton;///////////////////////////////////////////////////
    
    private JButton buyTime;
    private JButton buyHealth;
    private JButton buyHint;
    
    private JButton buyTheme;
    
       	public ShopPanel() {
		//timeLabel = new JLabel("");
		//final ImageIcon icon = new ImageIcon(QMainMenu.class.getResource("/img/health1.png"));
		//timeLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/health1.jpg")));
       	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	setPreferredSize(new Dimension(screenSize.width, screenSize.height));
                
        buyTheme = new JButton("Buy Theme");
        
        timePanel = new JPanel();
        
        timePanel.setBackground(new Color(65,68,73));
        
        healthPanel = new JPanel();
        healthPanel.setBackground(new Color(65,68,73));
        
        hintPanel = new JPanel();
        hintPanel.setBackground(new Color(65,68,73));

//        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
//        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.Y_AXIS));
//        hintPanel.setLayout(new BoxLayout(hintPanel, BoxLayout.Y_AXIS));
        
        timePanel.setLayout(null);
        healthPanel.setLayout(null);
        hintPanel.setLayout(null);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1,3,30,30));
        upperPanel.setBackground(new Color(65,68,73));
        upperPanel.add(timePanel);
        
        timeLabel = new JLabel("");
        timeLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Time-Power-Up.png")));
        timeLabel.setBounds(118, 47, 240, 40);
        timePanel.add(timeLabel);
        
        timeIconLabel = new JLabel("");
        timeIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/time2.jpg")));
        timeIconLabel.setBounds(67, 47, 41, 40);
        timePanel.add(timeIconLabel);
        
        coinIconLabel1 = new JLabel("5");
        coinIconLabel1.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel1.setBounds(98, 118, 76, 50);
        timePanel.add(coinIconLabel1);
        
        buyTime = new JButton("Buy");
        buyTime.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        buyTime.setBounds(184, 132, 66, 23);
        timePanel.add(buyTime);
        upperPanel.add(healthPanel);
        
        healthLabel = new JLabel("");
        healthLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Health-Power-Up.png")));
        healthLabel.setBounds(107, 50, 202, 44);
        healthPanel.add(healthLabel);
        
        healthIconLabel = new JLabel("");
        healthIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/health2.jpg")));
        healthIconLabel.setBounds(52, 50, 47, 44);
        healthPanel.add(healthIconLabel);
        
        coinIconLabel2 = new JLabel("5");
        coinIconLabel2.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel2.setBounds(87, 126, 73, 44);
        healthPanel.add(coinIconLabel2);
        
        buyHealth = new JButton("Buy");
        buyHealth.setBounds(170, 137, 66, 23);
        healthPanel.add(buyHealth);
        upperPanel.add(hintPanel);
        
        hintLabel = new JLabel("");
        hintLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Hint-Power-Up.png")));
        hintLabel.setBounds(131, 52, 184, 45);
        hintPanel.add(hintLabel);
        
        hintIconLabel = new JLabel("");
        hintIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/hint2.jpg")));
        hintIconLabel.setBounds(74, 52, 47, 45);
        hintPanel.add(hintIconLabel);
        
        coinIconLabel3 = new JLabel("5");
        coinIconLabel3.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel3.setBounds(130, 122, 66, 57);
        hintPanel.add(coinIconLabel3);
        
        buyHint = new JButton("Buy");
        buyHint.setBounds(212, 139, 66, 23);
        hintPanel.add(buyHint);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(new Color(65,68,73));
        
        lowerPanel.setLayout(new GridLayout(1, 2, 100, 50));
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(65,68,73));
        
        JPanel buyThemePanel = new JPanel();
        buyThemePanel.setBackground(new Color(65,68,73));
        buyThemePanel.setLayout(null);
        
        buyTheme.setBounds(238,159,100,20);
        buyThemePanel.add(buyTheme);

        //lowerPanel.add(themeDisplay);
        lowerPanel.add(buyThemePanel);
        lowerPanel.add(profilePanel);

        setLayout(new GridLayout(3,1));
        add(upperPanel);
        add(lowerPanel);
        
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(65,68,73));
        backPanel.setLayout(null);
        
        
        
        //backButton.setBackground(new Color(65,68,73));
        //add(backButton);
        
        
        add(backPanel);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(42, 96, 89, 23);
        backPanel.add(backButton);
	}
}
