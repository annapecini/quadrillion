package logic;
import gazillion.*;
import quadrillion.QGame;
import utils.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class QLevelModePanel extends QModePanel {

    private JButton[] buttons;
    private JPanel buttonPanel;
    private QLevelMode levelMode;
    private JPanel topPanel;
    private JLabel title;
    private JButton lastPlayed;
    private JPanel[] temps;

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

        title = new JLabel( "LEVEL MODE");
        title.setFont( title.getFont().deriveFont( 30.0f));
        title.setHorizontalAlignment( SwingConstants.CENTER);

        topPanel.add( title, BorderLayout.SOUTH);

        // add a back button maybe?
        topPanel.add(getBackButton(), BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        int gridSize = levelMode.getGridSize();
        //////////////////////// THE BUTTONS / LEVELS ///////////////////////////////////////////////////////////////
        buttonPanel = new JPanel( new GridLayout(gridSize, gridSize, 20, 20)){};
        buttonPanel.setBackground( new Color(200, 191, 231));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        gridSize = levelMode.getGridSize() * levelMode.getGridSize();

        buttons = new JButton[gridSize];
        temps = new JPanel[gridSize];

        for (int i = 0; i < gridSize; i++) {

            try{
                String levelName = "level" + ((int)(Math.random()* 8) + 1) + ".png";
                buttons[i] = new JButton( new ImageIcon( ImageIO.read(getClass().getResource(  levelName))));
            }
            catch( Exception e){
                buttons[i] = new JButton();
            }

            JPanel temp = new JPanel( new GridBagLayout());
            temps[i] = temp;
            temp.setBorder(BorderFactory.createLineBorder(Color.black));

            //remove button borders
            Border emptyBorder = BorderFactory.createEmptyBorder();
            buttons[i].setBorder(emptyBorder);
            buttons[i].setContentAreaFilled(false);

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
                temps[i].setBackground(new Color(238, 238, 238));
            }
            else{
                buttons[i].setEnabled(false);
                temps[i].setBackground(new Color(213, 213, 213));
            }
        }
        buttons[0].setEnabled(true);
        temps[0].setBackground(new Color(238, 238, 238));
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

