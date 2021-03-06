package logic;
import quadrillion.*;
import gazillion.*;
import utils.Message;
import utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author
 * @version
 */

public abstract class QModePanel extends QPanel implements Observer {

    protected QPlayerInfoPanel playerInfo;
    protected QMode mode;
    protected QGazillionPanel gazillionPanel;                                 // for the Quadrillion Game

    public QModePanel( QMode currMode, QPanel parent, QFrame frame) {

        super( parent, frame);
        mode = currMode;
        gazillionPanel = null;
    }

    public boolean startQuadrillionGame( int i ){

        QGame game = mode.playGame( i);
        if( game != null) {
            QPlayer player = mode.getPlayer();
            QThemeManager man = new QThemeManager();

            gazillionPanel = new QGazillionPanel(this, frame, player, man.getTheme(player.getCurrentTheme()), game);
            frame.setActivePanel( gazillionPanel);
            return true;
        }

        return false;
    }

    ///////////////////////////// AFTER GAZILLION PANEL MESSAGES END OF GAME ///////////////////////////////////////////

    // To display messages related to award !!
    public abstract void update( Message msg);

}