package gazillion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * QPanel
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QPanel extends JPanel {
    protected QPanel parent;
    protected QFrame frame;

    public QPanel(QPanel parent, QFrame frame) {
        super();
        this.parent = parent;
        this.frame = frame;
    }

    public QPanel getParentPanel() {
        return parent;
    }

    public JButton getBackButton() {
        if(parent != null) {
            JButton button = new JButton("");
            ImageIcon icon = new ImageIcon(QPanel.class.getResource("/img/Back.png"));
            button.setIcon(icon);
            button.setOpaque(false);
    		button.setContentAreaFilled(false);
    		button.setBorderPainted(false);
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setActivePanel(parent);
                }
            });
            button.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent evt)
                {
                	try {
                		icon.setImage(ImageIO.read(getClass().getResource("/img/Back resized.png")));
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                    //button.setIcon(icon1);
                }
                public void mouseExited(MouseEvent evt)
                {
                    try {
                    	icon.setImage(ImageIO.read(getClass().getResource("/img/Back.png")));
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
            });                
            return button;
        }
        return null;
    }
}
