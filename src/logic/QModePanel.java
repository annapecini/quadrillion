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

    public abstract void updateAdjacentColor( int buttonIndex);

    public abstract void updatePlayedColor( int buttonIndex);

    public void updateTreasureColor( int buttonIndex){}

    public void updatePlayerInformation(){
        playerInfo.update();
    }

    public boolean startQuadrillionGame( int i ){

        QGame game = mode.playGame( i);
        if( game != null) {
            QPlayer player = mode.getPlayer();
            QThemeManager man = new QThemeManager();

            gazillionPanel = new QGazillionPanel(this, frame, player, man.getThemes().get(0), game);
            frame.setActivePanel(gazillionPanel);
            return true;
        }

        return false;
    }

    ///////////////////////////// AFTER GAZILLION PANEL MESSAGES END OF GAME ///////////////////////////////////////////

    // To display messages related to award !!
    public abstract void update( Message msg);
}