package logic;
import quadrillion.*;
import gazillion.*;

/**
 * @author
 * @version
 */

public abstract class QMode {

    protected QPlayer player;

    public QMode( QPlayer player) {
        this.player = player;
    }

    public QModePanel createPanel( QPanel parent, QFrame frame) {
        return null;
    }

    public abstract QAward evaluateAwardForCurrentGame( boolean won);

    public abstract void updateStateOfMode( boolean won);

    public QPlayer getPlayer(){ return player;}

    public void setPlayer(QPlayer player) {this.player = player;}

    public abstract QGame playGame( int i);

}