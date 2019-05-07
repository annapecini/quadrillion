package logic;
import quadrillion.*;
import gazillion.*;
import utils.Message;

import java.awt.*;
import java.util.*;

/**
 *
 * @author
 * @version 20190328
 */

public class QTreasureMode extends QMode{

    private int gridSize = 6;
    private int lastDisplayedHint;

    private static final int locked = 0;
    private static final int unlocked = 1;
    private static final int played = 2;
    private static final int revealed = 3;

    private int [][] gameGrid;

    private int [][] treasureGrid;              // to keep track of the treasure positions
    private int collectedPieces;                // used to decide which piece type to generate next
    private int currentX;                       // x-index of current position
    private int currentY;                       // y-index of current position

    private QTreasureModePanel modePanel;

    public QTreasureMode( QPlayer player) {
        super( player);

        gameGrid = player.getTreasureModeGrid();
        if( gameGrid == null) {
            gameGrid = new int[gridSize][gridSize];

            // initialize all levels as locked
            for (int i = 0; i < gridSize; i++)
                for (int j = 0; j < gridSize; j++)
                    gameGrid[i][j] = locked;

            // enable the corner levels
            gameGrid[0][0] = unlocked;
            gameGrid[0][gridSize - 1] = unlocked;
            gameGrid[gridSize - 1][0] = unlocked;
            gameGrid[gridSize - 1][gridSize - 1] = unlocked;
        }

        treasureGrid = player.getTreasureGrid();

        if( treasureGrid == null) {
            // randomly generate treasure positions
            treasureGrid = new int[gridSize][gridSize];

            for (int i = 0; i < gridSize; i++)
                for (int j = 0; j < gridSize; j++)
                    treasureGrid[i][j] = 0;

            for (int i = 0; i < 12; i++) {
                int rand = (int) (Math.random() * (gridSize * gridSize));
                if (treasureGrid[rand / gridSize][rand % gridSize] == 1)
                    i--;
                else {
                    treasureGrid[rand / gridSize][rand % gridSize] = 1;
                }
            }
        }

        lastDisplayedHint = player.getLastDisplayedHint();
        collectedPieces = player.getNoPieces();
        currentX = -1;
        currentY = -1;

        modePanel = null;
    }

    ////////////////////////////////////// FOR GUI COMPONENTS //////////////////////////////////////////////////////

    @Override
    public QModePanel createPanel( QPanel panel, QFrame frame) {

        modePanel = new QTreasureModePanel( this, panel, frame);
        return modePanel;
    }

    /**
     * Number of buttons to be constructed
     */
    public int getGridSize(){
        return gridSize;
    };

    public int[][] getGameGrid() {
        return gameGrid;
    }

    public int[][] getTreasureGrid() {
        return treasureGrid;
    }

    /**
     * To get index of the button to highlight / change color ?????
     */
    public void getNextTreasurePosition(){
        int nh = player.getNoHints();
        if( nh > 0) {

            // decrease player hints
            player.setNoHints(  nh - 1 );
            //player.notifyObservers();

            // find next hint to display
            int i;
            boolean found = false;

            for (i = lastDisplayedHint + 1; i < gridSize * gridSize; i++) {
                if (treasureGrid[i / gridSize][i % gridSize] == 1) {
                    found = true;
                    break;
                }
            }

            if(found) {
                gameGrid[i / gridSize][i % gridSize] = revealed;
                lastDisplayedHint = i;
                modePanel.updateTreasureColor(i);
            }
        }
    }

    public void updatePanelColors(){
        for( int i = 0; i < gridSize; i++)
            for( int j = 0; j < gridSize; j++) {
                if( gameGrid[i][j] == unlocked){
                    modePanel.updateAdjacentColor( i * gridSize + j);
                }else if( gameGrid[i][j] == played) {
                    modePanel.updatePlayedColor( i * gridSize + j);
                }
            }
    }

    public void saveMode(){

        player.setNoPieces( collectedPieces);
        player.setTreasureGrid( treasureGrid);
        player.setTreasureModeGrid( gameGrid);
        player.setLastDisplayedHint( lastDisplayedHint);
    }

    //////////////////////////////// TO BE CALLED AFTER GAME HAS BEEN PLAYED ///////////////////////////////////

    /**
     * Evaluates AND ASSIGNS the award to the player. to be called after current game has finished.
     * Returns null if no award has been assigned.
     */
    @Override
    public QAward evaluateAwardForCurrentGame( boolean won){

        if(  gameGrid[currentX][currentY] != played){

            QAward result = null;

            if(won){

                int health = (int) (Math.random() * 4);
                int hints = (int) (Math.random() * 4);
                int coins = 5 + (int) (Math.random() * 6);
                int time = (int) (Math.random() * 4);

                if( treasureGrid[currentX][currentY] == 1) {

                    //update treasure panel
                    QPieceFactory pieceFactory = new QPieceFactory();
                    modePanel.updateTreasurePanel (pieceFactory.getPieceOfType( QPieceType.valueOf( "PIECE_TYPE_" + (collectedPieces+1))));

                    // add new piece to the collection
                    collectedPieces++;

                    // because treasureGrid reused for hints message
                    treasureGrid[currentX][currentY] = 0;

                    result = new QAward( health, hints, coins, time, QPieceType.valueOf( "PIECE_TYPE_" + (collectedPieces+1)));
                }
                else
                    result = new QAward( health, hints, coins, time, null);
            }
            else {
                player.setHealth( player.getNoHealth() - 1);

                ///////////// CHECK IF PLAYER'S HEALTH IS 0 ///////////////
                if( player.getNoHealth() == 0){
                    modePanel.displayGameOver();
                }
            }

            player.unpackAward( result);
            return result;
        }
        return null;
    }

    /**
     * Updating mode information after current game has finished. To be called after game is finished.
     */
    public void updateStateOfMode( boolean won){

        if( won){
            // mark current position as played
            gameGrid[currentX][currentY] = played;

            // unlock adjacent levels
            if( currentX - 1 > -1 && gameGrid[currentX - 1][currentY] != 2) {
                gameGrid[currentX - 1][currentY] = 1;
            }

            if( currentX + 1 < gridSize && gameGrid[currentX + 1][currentY] != 2) {
                gameGrid[currentX + 1][currentY] = 1;
            }

            if( currentY + 1 < gridSize && gameGrid[currentX][currentY + 1] != 2) {
                gameGrid[currentX][currentY + 1] = 1;
            }

            if( currentY - 1 > -1 && gameGrid[currentX][currentY - 1] != 2) {
                gameGrid[currentX][currentY - 1] = 1;
            }

            updatePanelColors();
        }
    }

    /////////////////////////////////////// TO INITIATE A NEW GAME ////////////////////////////////////////////
    /**
     * Updates the coordinates of the current game/level according to input from TreasurePanel
     */
    private void setCurrentLocation( int level){
        currentY = level % gridSize;
        currentX = level / gridSize;
    }

    /**
     * Initiates a random game after checking if level requested is valid.
     * Returns reference to the game if constructed and NULL if not valid
     */
    @Override
    public QGame playGame( int i){
        setCurrentLocation( i);

        if( gameGrid[ currentX][ currentY] != 0){
            QGameFactory factory = new QGameFactory();
            QGame game = factory.getRandomQGame();
            game.startTimer();
            return game;
        }
        return null;
    }

}