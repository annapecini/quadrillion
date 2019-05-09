package logic;
import gazillion.QFrame;
import gazillion.QGameFactory;
import gazillion.QPanel;
import gazillion.QPlayer;
import quadrillion.QGame;
import quadrillion.QTimer;
import utils.Message;
import utils.Observer;


public class QLadderMode extends QMode implements Observer {

    private QLadderModePanel modePanel;
    private QTimer timer;
    private int gamesWon;

    public QLadderMode( QPlayer player){
            super(player);
            modePanel = null;
            timer = new QTimer(20000);
            timer.addObserver(this);
            player.addObserver(this);
            gamesWon = 0;
    }

    @Override
    public QModePanel createPanel(QPanel panel, QFrame frame) {

        modePanel = new QLadderModePanel( this, panel, frame);
        return modePanel;
    }

    @Override
    public QAward evaluateAwardForCurrentGame(boolean won) {

        if( won){
            gamesWon++;
            if(gamesWon > player.getHighScore()) {
                player.setHighScore(gamesWon);
            }
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

    public QTimer getTimer() {
        return timer;
    }

    public int getGamesWon(){
        return gamesWon;
    }

    public void setModeGamesWon(int gw) {
        gamesWon = gw;
    }

    @Override
    public void updateStateOfMode(boolean won) {
        modePanel.displayWinPanel();
        modePanel.displayLosePanel();
    }

    @Override
    public QGame playGame(int i) {
        QGameFactory factory = new QGameFactory();
        QGame game = factory.getRandomQGame(timer.getTimeRemaining());
        timer.start();
        game.startTimer();
        return game;
    }

    @Override
    public void update(Message msg) {
        if(msg.isValid() && msg.getContents()[Message.GAME_OVER] || msg.getContents()[Message.GAME_UP]) {
            timer.stop();
            //timer.setTimeRemaining(0);
            modePanel.disableGameButton();
        }
    }

    public void reset() {

    }

}

