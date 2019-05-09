package gazillion;

import quadrillion.QCoordinate;
import quadrillion.QGame;
import quadrillion.QPiece;
import quadrillion.QTimer;
import utils.Message;
import utils.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * QUtilityPanel
 * TODO: OBSERVER PATTERN FOR TIMER UPDATE.
 * TODO: TIMER SHOULD IMPLEMENT OBSERVABLE AND THIS SHOULD IMPLEMENT OBSERVER
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QUtilityPanel extends QPanel implements Observer {
    //private JButton health;
    private JButton time;
    private JButton reset;
    private QPlayer player;
    private JLabel timeLeft;
    private QGame game;
    private boolean timerDisabled;
    public QUtilityPanel(QPanel panel, QFrame frame, QGame game, QPlayer player) {
        super(panel,frame);
        this.player = player;
        time = new JButton("Time!");
        time.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (player.getNoTimeUp() > 0) {
                    QTimer timer = game.getTimer();
                    timer.setTimeRemaining(timer.getTimeRemaining() + 120000);
                    player.setNoTimeUp(player.getNoTimeUp() - 1);
                }
            }
        });
        reset = new JButton("Reset Pieces");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<QCoordinate> coords = game.getBoard().getWorldCoordinates();
                for(QCoordinate q: coords) {
                    game.removePieceAt(q);
                }
                QGazillionPanel gp = (QGazillionPanel)parent;
                for(QPiece piece : game.getPieces()) {
                    gp.getQPieceCollectionPanel().getDisplayOfHostedPiece(piece).setAvailable(true);
                }
            }
        });
        timeLeft = new JLabel("Time Left: " + game.getTimer().getTimeRemaining() / 1000.0);
        this.game = game;

        //add(health);
        add(time);
        add(reset);
        add(timeLeft);
    }

    @Override
    public void update(Message msg) {
        if(!timerDisabled)
            timeLeft.setText("Time Left: " + game.getTimer().getTimeRemaining() / 1000.0);
        else {
            timeLeft.setText("Time Left: -");
        }
    }

    public void disableTimeButton() {
        time.setEnabled(false);
        time.setVisible(false);
        timerDisabled = true;
        update(new Message("0000"));
    }
}
