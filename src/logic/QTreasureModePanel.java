package logic;
import gazillion.*;
import quadrillion.*;
import utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
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

    public QTreasureModePanel(QMode currMode, QPanel parent, QFrame frame) {

        super(currMode, parent, frame);
        treasureMode = (QTreasureMode) currMode;

        this.setLayout(new BorderLayout());

        ////////////////////////// PLAYER INFO AT THE TOP /////////////////////////////////////////////////////////////
        topPanel = new JPanel();

        // top panel has border layout
        topPanel.setLayout(new BorderLayout());

        // add player info panel as first component of top panel
        playerInfo = new QPlayerInfoPanel(this, frame, mode.getPlayer());
        topPanel.add(playerInfo, BorderLayout.NORTH);


        // add hint button as last thing of topPanel
        hintButton = new JButton("Hint");
        topPanel.add(hintButton, BorderLayout.EAST);

        hintButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                treasureMode.getNextTreasurePosition();
                updatePlayerInformation();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        //////////////////////// THE BUTTONS / LEVELS ///////////////////////////////////////////////////////////////
        buttonPanel = new JPanel(){

//            @Override
//            protected void paintComponent(Graphics g) {
//
//                super.paintComponent(g);
//                try {
//                    g.drawImage(ImageIO.read( new File("C:\\Users\\User\\IdeaProjects\\temp\\prova\\src\\logic\\image.jpg")),
//                                                0, 0, buttonPanel.getWidth(), buttonPanel.getHeight(), null);
//                } catch (IOException exp) {
//                    System.out.println( "Printim idiot");
//                }
//            }


        };
        buttons = new JButton[36];
        buttonPanel.setLayout(new GridLayout(6, 6));

        for (int i = 0; i < 36; i++) {
            buttons[i] = new JButton("" + (i + 1));

            ///// To put an icon to buttons //////////
//            try {
//                Image img = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\temp\\prova\\src\\logic\\button.jpg"));
//                buttons[i].setIcon(new ImageIcon(img));
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }

            JPanel temp = new JPanel();
            temp.setLayout(new FlowLayout());
            temp.setOpaque(false);
            temp.add(buttons[i]);

            buttonPanel.add(temp);

            buttons[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    int i = Integer.parseInt(((JButton) mouseEvent.getSource()).getText());
                    startQuadrillionGame(i - 1);
                }
            });
        }

        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.CENTER);

        /////////////////////////// TREASURE PANEL ///////////////////////////////////////////////////////////////S

        QThemeManager man = new QThemeManager();

        QGameBuilder g = new QGameBuilder();
        QGame tempgame = g.setGrid(QGridType.GRID_TYPE_1, 2)
                .setGrid(QGridType.GRID_TYPE_2, 1)
                .setGrid(QGridType.GRID_TYPE_3, 0)
                .setGrid(QGridType.GRID_TYPE_4,1)
                .setBoard(QBoardType.BOARD_TYPE_1)
                .build(600000);


        treasurePanel = new QPieceCollectionPanel(this, frame, tempgame.getPieces(), man.getThemes().get(0));

        for(int i = 0; i < 12; i++){
            Component c = treasurePanel.getDisplayOfHostedPiece( tempgame.getPieces().get(i));
            ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(false);
        }

        add(treasurePanel, BorderLayout.EAST);

        frame.setActivePanel(this);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read( new File("C:\\Users\\User\\IdeaProjects\\temp\\prova\\src\\logic\\image2.jpg")),
                                        0, 0,null);
        } catch (IOException exp) {
            System.out.println( "Printim idiot");
        }
    }

    ///////////////////// UPDATE BUTTON COLORS ///////////////////////////////////////////////////////

    public void updateAdjacentColor(int buttonIndex) {

        buttons[buttonIndex].setBackground(Color.GREEN);
    }

    public void updatePlayedColor(int buttonIndex) {

        buttons[buttonIndex].setBackground(Color.CYAN);
    }

    public void updateTreasureColor(int buttonIndex) {

        buttons[buttonIndex].setBackground(Color.RED);
    }

    public void updateTreasurePanel( QPiece newPiece) {

        Component c = treasurePanel.getDisplayOfHostedPiece( newPiece);
        ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(true);
    }


/////////////////////////////////////////// GAME OVER //////////////////////////////////////////////////////////

    public void displayGameOver() {
        JOptionPane.showMessageDialog( frame, "YOU DIED");
        treasureMode = null;
        frame.setActivePanel( new QMainMenu( null,frame));
    }

    @Override
    public void update( Message msg){

        if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_WON]){
            QAward award = treasureMode.evaluateAwardForCurrentGame( true);
            treasureMode.updateStateOfMode( true);
        } else if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_OVER]){           //////////////////// SHOULD BE CHANGED  ////////////////////
            QAward award = treasureMode.evaluateAwardForCurrentGame( true);
            treasureMode.updateStateOfMode( true);
        }

        treasureMode.updatePanel();
    }

}