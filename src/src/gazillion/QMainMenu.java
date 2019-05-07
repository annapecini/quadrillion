
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

    public QMainMenu( QPanel parent, QFrame frame, QPlayer player) {

        super( parent, frame);
        mode = null;
        this.player = player;

        JButton[] buttons = new JButton[4];
        GridLayout layout = new GridLayout( 4, 1);
        this.setLayout( layout);

        for( int i = 0; i < 3; i++){
            buttons[i] = new JButton( "" + (i+1));
            this.add( buttons[i]);

            buttons[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    int i = Integer.parseInt(((JButton) mouseEvent.getSource()).getText());

                    if( i == 1) {
                        mode = new QTreasureMode(QMainMenu.this.player);
                    }
                    else if( i == 2){
                        mode = new QLevelMode( QMainMenu.this.player);
                    }
                    else{
                        mode = new QLadderMode( QMainMenu.this.player);
                    }

                    QPanel modePanel = mode.createPanel(QMainMenu.this, frame);
                    frame.setActivePanel(modePanel);
                }
            });
        }
        buttons[3] = new JButton("Profile Selection");
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setActivePanel(parent);
            }
        });
        add(buttons[3]);
    }

    public void setPlayer(QPlayer player) {
        this.player = player;
        if(mode!= null) {
            mode.setPlayer(player);
        }
    }

}