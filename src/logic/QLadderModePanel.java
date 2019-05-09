package logic;

import gazillion.QFrame;
import gazillion.QPanel;
import utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLadderModePanel extends QModePanel {

    private QLadderMode ladderMode;
    private JLabel timeLabel;
    private JLabel infoLabel;
    private JLabel highScore;
    private JButton gameButton;
    private JButton reset;

    public QLadderModePanel(QMode currMode, QPanel parent, QFrame frame) {
        super(currMode, parent, frame);


        ladderMode = (QLadderMode) currMode;

        // create the initial panel
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ladder Mode");
        timeLabel = new JLabel("Total time remaining: " + String.format("%.1f",ladderMode.getTimer().getTimeRemaining()/60000.0) + " minutes.");
        infoLabel = new JLabel("Games won so far: " + ladderMode.getGamesWon());
        highScore = new JLabel("Highest score: " + mode.player.getHighScore() + " levels in a go.");
        gameButton = new JButton("Play Next Game");

        gameButton.setPreferredSize( new Dimension(200,100));

        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuadrillionGame( 0);
            }
        });

                reset = new JButton("Start over");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to start over?", "Ladder Mode Restart", JOptionPane.YES_NO_OPTION);
                if(choice == 0) {
                    ladderMode.getTimer().stop();
                    ladderMode.getTimer().restart();
                    ladderMode.setModeGamesWon(0);
                    gameButton.setEnabled(true);
                    gameButton.setText("Play Next Game");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add( gameButton);
        panel.add(timeLabel);
        panel.add(infoLabel);
        panel.add(highScore);
        panel.add(reset);
        ladderMode.getTimer().addObserver(this);
        ladderMode.player.addObserver(this);

        this.add( label, BorderLayout.NORTH);
        this.add( panel, BorderLayout.CENTER);
        this.add(getBackButton(),BorderLayout.SOUTH);

        frame.setActivePanel(this);

    }

    public void displayWinPanel(){

    }

    public void displayLosePanel(){

    }

    @Override
    public void update( Message msg){

        if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_WON]){
            QAward award = ladderMode.evaluateAwardForCurrentGame( true);
            ladderMode.updateStateOfMode( true);
        } else if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_OVER]){
            QAward award = ladderMode.evaluateAwardForCurrentGame( false);
            ladderMode.updateStateOfMode( false);
            disableGameButton();
            gameButton.setText("Time is up! You have to start over.");
        } else if (msg.isValid() && msg.getContents()[Message.GAME_UP]) {
            ladderMode.getTimer().stop();
            disableGameButton();
            gameButton.setText("You gave up. You have to start over.");
        }

        timeLabel.setText("Total time remaining: " + String.format("%.1f",ladderMode.getTimer().getTimeRemaining()/60000.0) + " minutes.");
        infoLabel.setText("Games won so far: " + ladderMode.getGamesWon());
        highScore.setText("Highest score: " + mode.player.getHighScore() + " levels in a go.");

    }

    public void disableGameButton() {
        gameButton.setEnabled(false);
        repaint();
        revalidate();
    }

}
