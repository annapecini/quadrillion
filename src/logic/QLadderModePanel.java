package logic;

import gazillion.QFrame;
import gazillion.QPanel;
import utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLadderModePanel extends QModePanel{

    private QLadderMode ladderMode;

    public QLadderModePanel(QMode currMode, QPanel parent, QFrame frame) {
        super(currMode, parent, frame);

        ladderMode = (QLadderMode) currMode;

        // create the initial panel
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ladder Mode");
        JButton button = new JButton("Play Random Game");

        button.setPreferredSize( new Dimension(200,100));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                startQuadrillionGame( 0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout( new FlowLayout());
        panel.add( button);

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
        }

        //ladderMode.saveMode();
    }
}
