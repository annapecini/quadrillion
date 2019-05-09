package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import gazillion.QMainMenu;


public class TreasureModePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JButton[] buttons;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JButton hintButton;
    private JPanel treasurePanel;
    private JPanel playerInfo;

    
	public TreasureModePanel() {
		this.setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		playerInfo = new JPanel();

        // top panel has border layout
        topPanel.setLayout(new BorderLayout());
        topPanel.add(playerInfo, BorderLayout.NORTH);
        
        hintButton = new JButton("Hint");
        topPanel.add(hintButton, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);

        //////////////////////// THE BUTTONS / LEVELS ///////////////////////////////////////////////////////////////
        buttonPanel = new JPanel();
        buttons = new JButton[36];
        buttonPanel.setLayout(new GridLayout(6, 6));

        for (int i = 0; i < 36; i++) {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setBackground(new Color(152, 216, 216));
            //Remove border
            buttons[i].setBorderPainted(false);
            if ( i % 4 == 0)
            	buttons[i].setIcon(new ImageIcon(TreasureModePanel.class.getResource("/img/island1.png")));
            if ( i % 4 == 1)
            	buttons[i].setIcon(new ImageIcon(TreasureModePanel.class.getResource("/img/island2.png")));
            if ( i % 4 == 2)
            	buttons[i].setIcon(new ImageIcon(TreasureModePanel.class.getResource("/img/island3.png")));
            if ( i % 4 == 3)
            	buttons[i].setIcon(new ImageIcon(TreasureModePanel.class.getResource("/img/island4.png")));
            JPanel temp = new JPanel();
            temp.setLayout(new FlowLayout());
            temp.setOpaque(false);
            temp.add(buttons[i]);
            //buttonPanel.add(buttons[i]);
            buttonPanel.add(temp);
            
        }
        buttonPanel.setBackground(new Color(152, 216, 216));
        //buttonPanel.setOpaque(false);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        
        treasurePanel = new JPanel();
        
        add(treasurePanel, BorderLayout.EAST);
	}

}
