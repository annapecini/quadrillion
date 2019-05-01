package logic;
import quadrillion.*;
import gazillion.*;

/**
 * @author
 * @version
 */

public abstract class QMode {

    protected QPlayer player;
    protected QModePanel modePanel;

    public QMode( QPlayer player) {
        this.player = player;
    }

    public QModePanel createPanel( QPanel parent, QFrame frame) {
        return null;
    }                                                                       // not sure about this

    public QGame initQGame( boolean randomGame, int levelIndex) {

        QGameFactory factory = new QGameFactory();
        return factory.getRandomQGame();

    }

    public abstract QAward evaluateAwardForCurrentGame( boolean won);

    public abstract void updateStateOfMode( boolean won);

    public QPlayer getPlayer(){ return player;}

    public void setPlayer(QPlayer player) {this.player = player;}

    public abstract QGame playGame( int i);

}