
package gazillion;
import quadrillion.*;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author
 * @version
 */

public class QMainMenu extends QPanel {

    private QMode mode;
    private QPlayer player;

    public QMainMenu( QPanel parent, QFrame frame) {

        super( parent, frame);
        mode = null;
        player = new QPlayer( "Player");

        JButton[] buttons = new JButton[3];
        GridLayout layout = new GridLayout( 1, 3);
        this.setLayout( layout);

        for( int i = 0; i < 3; i++){
            buttons[i] = new JButton( "" + (i+1));
            this.add( buttons[i]);

            buttons[i].addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    mode = new QTreasureMode( player);
                    QPanel modePanel= mode.createPanel( QMainMenu.this, frame);

                    frame.setActivePanel(modePanel);
                }
            });
        }
    }

}