package logic;
import quadrillion.*;
import gazillion.*;

/**
 *
 * @author
 * @version 20190328
 */

public class QLevelMode extends QMode {

    private int gridSize = 5;
    private int highestPlayed;
    private QLevelModePanel modePanel;

    public QLevelMode( QPlayer player) {
        super( player);
        highestPlayed = player.getLatestLevel();
        modePanel = null;
    }

    @Override
    public QModePanel createPanel( QPanel panel, QFrame frame) {

        modePanel = new QLevelModePanel( this, panel, frame);
        return modePanel;
    }

    public void saveMode(){
        player.setLatestLevel( highestPlayed);
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getHighestPlayed() {
        return highestPlayed;
    }

    /**
     * Evaluates AND ASSIGNS the award to the player. to be called after current game has finished.
     * Returns null if no award has been assigned.
     */
    @Override
    public QAward evaluateAwardForCurrentGame( boolean won){

        if( won && modePanel.firstPlayed()){

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

    /**
     * Updating mode information after current game has finished. To be called after game is finished.
     */
    public void updateStateOfMode( boolean won){

        if( won && modePanel.firstPlayed()){
            // update highest played
            highestPlayed++;

            // unlock next level
            if( highestPlayed < gridSize*gridSize){
                modePanel.enableNextLevel( highestPlayed);
            }
        }
    }

    /////////////////////////////////////// TO INITIATE A NEW GAME ////////////////////////////////////////////

    @Override
    public QGame playGame( int i){
        return LevelModeDefinitions.getLevel(i);
    }
}
