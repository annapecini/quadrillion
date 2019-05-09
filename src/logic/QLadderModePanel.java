package logic;

import gazillion.QFrame;
import gazillion.QPanel;
import utils.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

        JPanel titlePanel = new JPanel( new FlowLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 30, 0));
        titlePanel.setOpaque(false);

        JLabel label = new JLabel("Ladder Mode");
        label.setHorizontalAlignment( SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(30.0f));

        titlePanel.add( label);
        this.add( titlePanel, BorderLayout.NORTH);

        JPanel panel = new JPanel( new FlowLayout());
        panel.setOpaque(false);
        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout( new BoxLayout( labels,BoxLayout.Y_AXIS));

        timeLabel = new JLabel("Total time remaining: " + String.format("%.1f",ladderMode.getTimer().getTimeRemaining()/60000.0) + " minutes.");
        infoLabel = new JLabel("Games won so far: " + ladderMode.getGamesWon());
        highScore = new JLabel("Highest score: " + mode.player.getHighScore() + " levels in a go.");
        gameButton = new JButton("Play Next Game");

        labels.add( timeLabel);
        labels.add(Box.createVerticalStrut(30));
        labels.add(infoLabel);
        labels.add(Box.createVerticalStrut(30));
        labels.add(highScore);
        labels.add(Box.createVerticalStrut(80));
        labels.add( gameButton);
        labels.add(Box.createVerticalStrut(30));

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

        labels.add(reset);
        labels.add(Box.createVerticalStrut(30));
        labels.add(getBackButton());

        ladderMode.getTimer().addObserver(this);
        ladderMode.player.addObserver(this);

        panel.add( labels);
        this.add( panel, BorderLayout.CENTER);

        frame.setActivePanel(this);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("ladder.jpg"));
            g.drawImage(img,0, 0,null);
        } catch (IOException exp) {
            //exp.printStackTrace();
            //System.out.println( "Printim idiot");
        }
    }

    public void displayWinPanel(){
    }

    public void displayLosePanel(){
    }

    @Override
    public void update( Message msg){

        QAward award = null;

        if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_WON]){
            award = ladderMode.evaluateAwardForCurrentGame( true);
            ladderMode.updateStateOfMode( true);
        } else if( msg.getContents()[Message.VALID] && msg.getContents()[Message.GAME_OVER]){
            award = ladderMode.evaluateAwardForCurrentGame( false);
            ladderMode.updateStateOfMode( false);
            disableGameButton();
            gameButton.setText("Time is up! You have to start over.");
        } else if (msg.isValid() && msg.getContents()[Message.GAME_UP]) {
            ladderMode.getTimer().stop();
            disableGameButton();
            gameButton.setText("You gave up. You have to start over.");
        }

        if( award != null) {
            JOptionPane.showMessageDialog(frame, "You loot the island. Your spoils are: \n" +
                    award.getCoinsAwardNo() + " coins.\n" +
                    award.getTimeAwardNo() + " time powerup(s)\n" +
                    award.getHealthAwardNo() + " health powerup(s)\n" +
                    award.getHintsAwardNo() + " hint powerup(s).");
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
