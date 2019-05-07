
package gazillion;
import quadrillion.*;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public QMainMenu( QPanel parent, QFrame frame, QPlayer player) {

        super( parent, frame);
        mode = null;
        manager = new QThemeManager();
        shop = new QShopPanel(this,frame,player,manager);
        settings = new QSettingsPanel(this,frame,player,manager);
        this.player = player;

        JButton[] buttons = new JButton[6];
        GridLayout layout = new GridLayout( 4, 1);
        this.setLayout( layout);

        for( int i = 0; i < 4; i++){
            buttons[i] = new JButton( );
            buttons[i].setText("" + i);
            buttons[i].setActionCommand("" + i);
            this.add( buttons[i]);

            buttons[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    int i = Integer.parseInt(((JButton) mouseEvent.getSource()).getActionCommand());

                    if( i == 0) {
                        mode = new QTreasureMode(QMainMenu.this.player);
                        QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                        frame.setActivePanel(modePanel);
                    }
                    else if( i == 1){
                        mode = new QLevelMode( QMainMenu.this.player);
                        QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                        frame.setActivePanel(modePanel);
                    } else if (i == 2) {
                        frame.setActivePanel(shop);
                    } else if (i == 3) {
                        mode = new QLadderMode( QMainMenu.this.player);
                        QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                        frame.setActivePanel(modePanel);
                    }
                }
            });
        }
        buttons[4] = new JButton("Profile Selection");
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setActivePanel(parent);
            }
        });
        add(buttons[4]);

        buttons[5] = new JButton("Settings");
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setActivePanel(settings);
            }
        });
        add(buttons[5]);
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