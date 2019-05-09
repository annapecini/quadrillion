package gazillion;
import quadrillion.*;
import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author
 * @version
 */

public class QMainMenu extends QPanel {

    private QMode mode;
    private QPlayer player;
    private QShopPanel shop;
    private QSettingsPanel settings;
    private QThemeManager manager;
    private QInstructionsPanel instructions;

    public QMainMenu( QPanel parent, QFrame frame, QPlayer player) {

        super( parent, frame);
        mode = null;
        manager = new QThemeManager();
        instructions = new QInstructionsPanel(this, frame);
        shop = new QShopPanel(this,frame,player,manager);
        settings = new QSettingsPanel(this,frame,player,manager);
        this.player = player;

        //JButton[] buttons = new JButton[6];
        //GridLayout layout = new GridLayout( 4, 1);
        //this.setLayout( layout);

        setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setPreferredSize(new Dimension(screenSize.width, screenSize.height));
	    
//	    System.out.println("width:" + screenSize.width);
//	    System.out.println("height:" + screenSize.height);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/red.png")));
		lblNewLabel_1.setBounds(87, 210, 92, 85);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/green.png")));
		lblNewLabel_2.setBounds(1139, 398, 92, 163);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/orange.png")));
		lblNewLabel_3.setBounds(87, 463, 153, 98);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/yellow.png")));
		lblNewLabel_4.setBounds(211, 359, 146, 129);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/aquamarine.png")));
		lblNewLabel_5.setBounds(69, 306, 146, 129);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/baby pink.png")));
		lblNewLabel_6.setBounds(1031, 288, 146, 129);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/light green.png")));
		lblNewLabel_7.setBounds(211, 225, 146, 98);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/pink.png")));
		lblNewLabel_9.setBounds(1091, 175, 153, 148);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/purple.png")));
		lblNewLabel_10.setBounds(925, 186, 146, 148);
		add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/violet.png")));
		lblNewLabel_11.setBounds(944, 446, 146, 98);
		add(lblNewLabel_11);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(QMainMenu.class.getResource("/img/Gazillion.png")));
		lblNewLabel_8.setBounds(456, 100, 403, 111);
		add(lblNewLabel_8);
		
		JButton btnNewButton_5 = new JButton("");
		final ImageIcon icon7 = new ImageIcon(MainMenu.class.getResource("/img/profile.png"));
	    btnNewButton_5.setIcon(icon7);
	    btnNewButton_5.setBounds(550, 231, 236, 59);
	    btnNewButton_5.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon7.setImage(ImageIO.read(getClass().getResource("/img/profile resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon7.setImage(ImageIO.read(getClass().getResource("/img/profile.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	
                frame.setActivePanel(parent);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });

	    btnNewButton_5.setOpaque(false);
	    btnNewButton_5.setContentAreaFilled(false);
	    btnNewButton_5.setBorderPainted(false);
	    add(btnNewButton_5);
		
		JButton btnNewButton = new JButton("");
		final ImageIcon icon1 = new ImageIcon(QMainMenu.class.getResource("/img/Treasure-Mode.png"));
		btnNewButton.setIcon(icon1);
		btnNewButton.setBounds(550, 278, 236, 59);
		btnNewButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon1.setImage(ImageIO.read(getClass().getResource("/img/Treasure-Mode resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon1.setImage(ImageIO.read(getClass().getResource("/img/Treasure-Mode.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	mode = new QTreasureMode(QMainMenu.this.player);
                QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                frame.setActivePanel(modePanel);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });

		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(550, 325, 236, 59);
		final ImageIcon icon2 = new ImageIcon(QMainMenu.class.getResource("/img/Ladder-Mode.png"));
		btnNewButton_1.setIcon(icon2);
		btnNewButton_1.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon2.setImage(ImageIO.read(getClass().getResource("/img/Ladder-Mode resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon2.setImage(ImageIO.read(getClass().getResource("/img/Ladder-Mode.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	mode = new QLadderMode( QMainMenu.this.player);
                QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                frame.setActivePanel(modePanel);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(550, 379, 236, 48);
		final ImageIcon icon3 = new ImageIcon(QMainMenu.class.getResource("/img/Levels-Mode.png"));
		btnNewButton_2.setIcon(icon3);
		btnNewButton_2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon3.setImage(ImageIO.read(getClass().getResource("/img/Levels-Mode resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon3.setImage(ImageIO.read(getClass().getResource("/img/Levels-Mode.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
                mode = new QLevelMode( QMainMenu.this.player);
                QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                frame.setActivePanel(modePanel);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(550, 424, 236, 48);
		final ImageIcon icon4 = new ImageIcon(QMainMenu.class.getResource("/img/Shop.png"));
		btnNewButton_3.setIcon(icon4);
		btnNewButton_3.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon4.setImage(ImageIO.read(getClass().getResource("/img/Shop resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon4.setImage(ImageIO.read(getClass().getResource("/img/Shop.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	frame.setActivePanel(shop);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(550, 464, 236, 48);
		final ImageIcon icon5 = new ImageIcon(QMainMenu.class.getResource("/img/Settings.png"));
		btnNewButton_4.setIcon(icon5);
		btnNewButton_4.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon5.setImage(ImageIO.read(getClass().getResource("/img/Settings resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon5.setImage(ImageIO.read(getClass().getResource("/img/Settings.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	frame.setActivePanel(settings);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
		btnNewButton_4.setOpaque(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		add(btnNewButton_4);
		
		
		JButton button = new JButton("");
	    button.setBounds(550, 502, 236, 48);
	    final ImageIcon icon6 = new ImageIcon(QMainMenu.class.getResource("/img/Credits.png"));
		button.setIcon(icon6);
		button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon6.setImage(ImageIO.read(getClass().getResource("/img/Credits resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon6.setImage(ImageIO.read(getClass().getResource("/img/Credits.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	    add(button);
		
	    JButton btnNewButton_6 = new JButton("");
	    ImageIcon icon8 = new ImageIcon(MainMenu.class.getResource("/img/Instructions.png"));
	    btnNewButton_6.setIcon(icon8);
	    btnNewButton_6.setBounds(550, 545, 236, 48);
	    btnNewButton_6.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon8.setImage(ImageIO.read(getClass().getResource("/img/Instructions resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon8.setImage(ImageIO.read(getClass().getResource("/img/Instructions.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 5, height,Image.SCALE_SMOOTH)));
            	frame.setActivePanel(instructions);
            }
            public void mouseReleased(MouseEvent evt)
            {
                //icon1.setImage((icon1.getImage().getScaledInstance(width + 10, height,Image.SCALE_SMOOTH)));
            }
        });
	    btnNewButton_6.setOpaque(false);
	    btnNewButton_6.setContentAreaFilled(false);
	    btnNewButton_6.setBorderPainted(false);
		add(btnNewButton_6);
    }
	

    public void setPlayer(QPlayer player) {
        this.player = player;
        if(player != null) {
            shop.setPlayer(player);
            settings.setPlayer(player);
        }
        if(mode!= null) {
            mode.setPlayer(player);
        }
    }

}
