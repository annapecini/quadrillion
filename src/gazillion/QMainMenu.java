package gazillion;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.QMode;
import logic.QTreasureMode;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.net.URL;

public class QMainMenu extends QPanel {

	/**
	 * Create the panel.
	 */
	private QMode mode;
    private QPlayer player;
    
	public QMainMenu(QPanel parent, QFrame frame) {
		super( parent, frame);
        mode = null;
        player = new QPlayer( "Player");
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		
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
		
		JButton btnNewButton = new JButton("");
		final ImageIcon icon1 = new ImageIcon(QMainMenu.class.getResource("/img/Play-Game.png"));
		btnNewButton.setIcon(icon1);
		btnNewButton.setBounds(550, 270, 236, 71);
		btnNewButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon1.setImage(ImageIO.read(getClass().getResource("/img/Play game resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon1.setImage(ImageIO.read(getClass().getResource("/img/Play-Game.png")));
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
		btnNewButton.addActionListener( new ActionListener() {
			//
          @Override
          public void actionPerformed(ActionEvent actionEvent) {

              mode = new QTreasureMode( player);
              QPanel modePanel= mode.createPanel( QMainMenu.this, frame);

              frame.setActivePanel(modePanel);
          }
		});
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(550, 322, 226, 65);
		final ImageIcon icon2 = new ImageIcon(QMainMenu.class.getResource("/img/Instructions.png"));
		btnNewButton_1.setIcon(icon2);
		btnNewButton_1.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon2.setImage(ImageIO.read(getClass().getResource("/img/Instructions resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon2.setImage(ImageIO.read(getClass().getResource("/img/Instructions.png")));
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
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(560, 377, 216, 48);
		final ImageIcon icon3 = new ImageIcon(QMainMenu.class.getResource("/img/Shop.png"));
		btnNewButton_2.setIcon(icon3);
		btnNewButton_2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon3.setImage(ImageIO.read(getClass().getResource("/img/Shop resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon3.setImage(ImageIO.read(getClass().getResource("/img/Shop.png")));
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
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(560, 422, 216, 59);
		final ImageIcon icon4 = new ImageIcon(QMainMenu.class.getResource("/img/Settings.png"));
		btnNewButton_3.setIcon(icon4);
		btnNewButton_3.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon4.setImage(ImageIO.read(getClass().getResource("/img/Settings resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon4.setImage(ImageIO.read(getClass().getResource("/img/Settings.png")));
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
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(568, 473, 208, 48);
		final ImageIcon icon5 = new ImageIcon(QMainMenu.class.getResource("/img/Credits.png"));
		btnNewButton_4.setIcon(icon5);
		btnNewButton_4.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	try {
            		icon5.setImage(ImageIO.read(getClass().getResource("/img/Credits resized.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //button.setIcon(icon1);
            }
            public void mouseExited(MouseEvent evt)
            {
                try {
                	icon5.setImage(ImageIO.read(getClass().getResource("/img/Credits.png")));
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
		btnNewButton_4.setOpaque(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		add(btnNewButton_4);
		

    }
	
}
