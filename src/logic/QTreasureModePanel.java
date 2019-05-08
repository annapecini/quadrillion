package logic;
import gazillion.*;
import quadrillion.*;
import utils.Message;
import utils.QProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utils.Message.GAME_OVER;

/**
 * @author
 * @version
 */

public class QTreasureModePanel extends QModePanel {

    private JButton[] buttons;
    private JPanel buttonPanel;
    private QPieceCollectionPanel treasurePanel;
    private QTreasureMode treasureMode;
    private JPanel topPanel;
    private JButton hintButton;
    private JButton resetButton;

    private static final int locked = 0;
    private static final int unlocked = 1;
    private static final int played = 2;
    private static final int revealed = 3;

    public QTreasureModePanel(QMode currMode, QPanel parent, QFrame frame) {

        super(currMode, parent, frame);
        treasureMode = (QTreasureMode) currMode;

        this.setLayout(new BorderLayout());

        ////////////////////////// PLAYER INFO AT THE TOP ///////////////////////////
        topPanel = new JPanel();

        // top panel has border layout
        topPanel.setLayout(new BorderLayout());

        // add player info panel as first component of top panel
        playerInfo = new QPlayerInfoPanel(this, frame, mode.getPlayer());
        topPanel.add(playerInfo, BorderLayout.NORTH);


        // add hint button as last thing of topPanel
        hintButton = new JButton("Hint");
        topPanel.add(hintButton, BorderLayout.EAST);

        // add a back button maybe?
        topPanel.add(getBackButton(), BorderLayout.WEST);

        hintButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                treasureMode.getNextTreasurePosition();
            }
        });

        resetButton = new JButton("Reset");
        topPanel.add(resetButton, BorderLayout.SOUTH);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int choice = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to reset?", "Reset", JOptionPane.YES_NO_OPTION);
                if(choice == 0) {
                    mode.player.setTreasureGrid(null);
                    mode.player.setNoPieces(0);
                    mode.player.setLastDisplayedHint(-1);
                    mode.player.setTreasureModeGrid(null);
                    frame.setActivePanel(parent);
                }
            }
        });

        add(topPanel, BorderLayout.NORTH);

        //////////////////////// THE BUTTONS / LEVELS ///////////////////////////////
        buttonPanel = new JPanel();

        int gridSize = treasureMode.getGridSize();
        buttons = new JButton[gridSize*gridSize];
        buttonPanel.setLayout(new GridLayout(6, 6, 0, 0));

        for (int i = 0; i < gridSize * gridSize; i++) {
            buttons[i] = new JButton();
            buttons[i].setActionCommand("" + i);

            JPanel temp = new JPanel();
            temp.setLayout(new FlowLayout());
            temp.setOpaque(false);
            temp.add(buttons[i]);

            buttonPanel.add(temp);

            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if( ((JButton)mouseEvent.getSource()).isEnabled()) {
                        int i = Integer.parseInt(((JButton) mouseEvent.getSource()).getActionCommand());
                        startQuadrillionGame(i);
                    }
                }
            });
        }

        // set button attributes, loaded from Treasure Mode when saved
        initiateButtons();

        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.CENTER);

        /////////////////////////// TREASURE PANEL //////////////////////////////////////

        QThemeManager man = new QThemeManager();

        QGameBuilder g = new QGameBuilder();
        QGame tempgame = g.setGrid(QGridType.GRID_TYPE_1, 2)
                .setGrid(QGridType.GRID_TYPE_2, 1)
                .setGrid(QGridType.GRID_TYPE_3, 0)
                .setGrid(QGridType.GRID_TYPE_4,1)
                .setBoard(QBoardType.BOARD_TYPE_1)
                .build(600000);


        treasurePanel = new QPieceCollectionPanel(this, frame, tempgame.getPieces(), man.getTheme(mode.player.getCurrentTheme()));

        for(int i = 0; i < 12; i++){
            Component c = treasurePanel.getDisplayOfHostedPiece( tempgame.getPieces().get(i));
            ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(false);
        }
        for (int i = 0; i < mode.player.getNoPieces(); i++) {
            Component c = treasurePanel.getDisplayOfHostedPiece( tempgame.getPieces().get(i));
            ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(true);
        }

        add(treasurePanel, BorderLayout.EAST);
        frame.setActivePanel(this);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("image2.jpg"));
            g.drawImage(img,0, 0,null);
        } catch (IOException exp) {
            //exp.printStackTrace();
            //System.out.println( "Printim idiot");
        }
    }

    ///////////////////// UPDATE BUTTON COLORS //////////////////////////

    public void updateAdjacentColor(int buttonIndex) {

        //buttons[buttonIndex].setBackground(Color.GREEN);
        buttons[buttonIndex].setVisible( true);
        buttons[buttonIndex].setEnabled( true);
    }


    public void updateTreasureColor(int buttonIndex) {

        //buttons[buttonIndex].setBackground(Color.RED);
        buttons[buttonIndex].setVisible( true);
    }

    public void updateTreasurePanel( QPiece newPiece) {

        Component c = treasurePanel.getDisplayOfHostedPiece( newPiece);
        ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(true);
    }

    public void initiateButtons(){
        int[][] gameGrid = treasureMode.getGameGrid();
        int gridSize = treasureMode.getGridSize();
        int[][] treasureGrid = treasureMode.getTreasureGrid();

        // load icons to the buttons according to treasure/ not treasure
        for( int i = 0; i < gridSize; i++){
            for( int j = 0; j < gridSize; j++){

                buttons[i * gridSize + j].setContentAreaFilled(false);
                if( treasureGrid[i][j] == 1){
                    try{
                        buttons[i * gridSize + j].setIcon( new ImageIcon( ImageIO.read(getClass().getResource(  "treasure.PNG"))));
                    }
                    catch( Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try{
                        buttons[i * gridSize + j].setIcon( new ImageIcon( ImageIO.read(getClass().getResource(  "nottreasure.PNG"))));
                    }
                    catch( Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // adjust buttons visibility and enable/disable
        for( int i = 0; i < gridSize; i++){
            for( int j = 0; j < gridSize; j++){
                if( gameGrid[i][j] == locked){
                    buttons[ i * gridSize + j].setEnabled( false);
                    buttons[ i * gridSize + j].setVisible( false);
                }
                else if ( gameGrid[i][j] == revealed){
                    buttons[ i * gridSize + j].setEnabled( false);
                    buttons[ i * gridSize + j].setVisible( true);
                }
                else{
                    buttons[ i * gridSize + j].setEnabled( true);
                    buttons[ i * gridSize + j].setVisible( true);
                }
            }
        }

        // manually set corner buttons
        buttons[0].setVisible( true);
        buttons[0].setEnabled(true);

        buttons[gridSize - 1].setVisible( true);
        buttons[gridSize - 1].setEnabled(true);

        buttons[gridSize *( gridSize - 1)].setVisible( true);
        buttons[gridSize *( gridSize - 1)].setEnabled(true);

        buttons[gridSize * gridSize - 1].setVisible( true);
        buttons[gridSize * gridSize - 1].setEnabled(true);
    }


/////////////////////////////////////////// GAME OVER //////////////////////////////////////////////////////////

    public void displayGameOver() {
        JOptionPane.showMessageDialog( frame, "You died looking for treasure...");
        treasureMode = null;
        frame.setActivePanel( parent);
    }

    @Override
    public void update( Message msg){

        if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_WON]){
            QAward award = treasureMode.evaluateAwardForCurrentGame( true);
            treasureMode.updateStateOfMode( true);
            if(award.getPieceAward() == null)
                JOptionPane.showMessageDialog(frame, "You loot the island. Your spoils are: " +
                                           award.getCoinsAwardNo() + " coins.\n" +
                                           award.getTimeAwardNo() + " time powerup(s)\n" +
                                           award.getHealthAwardNo() + " health powerup(s)\n" +
                                           award.getHintsAwardNo() + " hint powerup(s).");
            else {
                JOptionPane.showMessageDialog(frame, "You loot the island. Your spoils are: " +
                        award.getCoinsAwardNo() + " coins.\n" +
                        award.getTimeAwardNo() + " time powerup(s)\n" +
                        award.getHealthAwardNo() + " health powerup(s)\n" +
                        award.getHintsAwardNo() + " hint powerup(s)\n" +
                        "You also found a piece!");
            }
        } else if( msg.getContents()[Message.VALID] && (msg.getContents()[Message.GAME_OVER] || msg.getContents()[Message.GAME_UP])) {
            QAward award = treasureMode.evaluateAwardForCurrentGame( false);
            treasureMode.updateStateOfMode( false);
        }

        treasureMode.saveMode();
    }

}