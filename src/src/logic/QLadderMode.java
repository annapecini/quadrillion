package logic;
import gazillion.QFrame;
import gazillion.QGameFactory;
import gazillion.QPanel;
import gazillion.QPlayer;
import quadrillion.QGame;
import utils.Message;


public class QLadderMode extends QMode{

    QLadderModePanel modePanel;

    public QLadderMode( QPlayer player){
            super(player);
            modePanel = null;
    }

    @Override
    public QModePanel createPanel(QPanel panel, QFrame frame) {

        modePanel = new QLadderModePanel( this, panel, frame);
        return modePanel;
    }

    @Override
    public QAward evaluateAwardForCurrentGame(boolean won) {

        if( won){

            int health = (int) (Math.random() * 3);
            int hints = (int) (Math.random() * 3);
            int coins = 5 + (int) (Math.random() * 16);
            int time = (int) (Math.random() * 3);

            QAward result = new QAward( health, hints, coins, time, null);

            player.unpackAward( result);
            return result;
        }
        return null;
    }

    @Override
    public void updateStateOfMode(boolean won) {
        modePanel.displayWinPanel();
        modePanel.displayLosePanel();
    }

    @Override
    public QGame playGame(int i) {
        QGameFactory factory = new QGameFactory();
        QGame game = factory.getRandomQGame();
        game.startTimer();
        return game;
    }

}

