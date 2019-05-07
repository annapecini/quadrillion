package logic;
import gazillion.*;
import quadrillion.QGame;
import utils.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QLevelModePanel extends QModePanel {

    private JButton[] buttons;
    private JPanel buttonPanel;
    private QLevelMode levelMode;
    private JPanel topPanel;
    private JButton lastPlayed;

    public QLevelModePanel(QMode currMode, QPanel parent, QFrame frame) {

        super(currMode, parent, frame);
        levelMode = (QLevelMode) currMode;

        this.setLayout(new BorderLayout());

        ////////////////////////// PLAYER INFO AT THE TOP /////////////////////////////////////////////////////////////
        topPanel = new JPanel();

        // top panel has border layout
        topPanel.setLayout(new BorderLayout());

        // add player info panel as first component of top panel
        playerInfo = new QPlayerInfoPanel(this, frame, mode.getPlayer());
        topPanel.add(playerInfo, BorderLayout.NORTH);

        // add a back button maybe?
        topPanel.add(getBackButton(), BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        //////////////////////// THE BUTTONS / LEVELS ///////////////////////////////////////////////////////////////
        buttonPanel = new JPanel(){};

        int gridSize = levelMode.getGridSize() * levelMode.getGridSize();

        buttons = new JButton[gridSize];
        buttonPanel.setLayout(new GridLayout(6, 6));

        for (int i = 0; i < gridSize; i++) {

            try{
                buttons[i] = new JButton( new ImageIcon( ImageIO.read(getClass().getResource(  "1.PNG"))));
            }
            catch( Exception e){
                buttons[i] = new JButton();
            }

            JPanel temp = new JPanel();
            temp.setLayout(new FlowLayout());
            temp.setOpaque(false);
            temp.add(buttons[i]);

            buttonPanel.add(temp);

            buttons[i].setActionCommand("" + (i));

            buttons[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if( ((JButton)mouseEvent.getSource()).isEnabled()){
                        int j = Integer.parseInt(((JButton) mouseEvent.getSource()).getActionCommand());
                        startQuadrillionGame(j);
                        lastPlayed = (JButton) mouseEvent.getSource();
                    }
                }
            });

            buttons[i].setPreferredSize( new Dimension(100,100));
        }

        initiateButtons();
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.CENTER);

        frame.setActivePanel(this);
        lastPlayed = null;
    }

    public void initiateButtons(){

        int gridSize = levelMode.getGridSize();
        int highest = levelMode.getHighestPlayed();
        System.out.println( highest);

        for( int i = 0; i < gridSize*gridSize; i++){
            if( i < highest){
                buttons[i].setEnabled(true);
            }
            else{
                buttons[i].setEnabled(false);
            }
        }
        buttons[0].setEnabled(true);
    }

    ///////////////////// UPDATE BUTTON COLORS ///////////////////////////////////////////////////////

    public boolean firstPlayed(){

        int index = Integer.parseInt(lastPlayed.getActionCommand());
        if( index == levelMode.getHighestPlayed())
            return true;

        return false;
    }

    public void enableNextLevel(int buttonIndex) {
        buttons[buttonIndex].setEnabled( true);
    }

/////////////////////////////////////////// GAME OVER //////////////////////////////////////////////////////////

    @Override
    public void update( Message msg){

        if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_WON]){
            QAward award = levelMode.evaluateAwardForCurrentGame( true);
            levelMode.updateStateOfMode( true);
        } else if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_OVER]){
            QAward award = levelMode.evaluateAwardForCurrentGame( false);
            levelMode.updateStateOfMode( false);
        }

        levelMode.saveMode();
    }

    @Override
    public boolean startQuadrillionGame( int i ){

        QGame game = mode.playGame( i);
        if( game != null) {
            QPlayer player = mode.getPlayer();
            QThemeManager man = new QThemeManager();

            gazillionPanel = new QGazillionPanel(this, frame, player, man.getTheme(player.getCurrentTheme()), game);
            gazillionPanel.disableTimePowerup();
            frame.setActivePanel( gazillionPanel);
            return true;
        }

        return false;
    }
}

