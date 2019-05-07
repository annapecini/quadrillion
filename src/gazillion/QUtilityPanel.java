package gazillion;

import quadrillion.QGame;
import quadrillion.QTimer;
import utils.Message;
import utils.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //hint = new JButton("Hint!");
        timeLeft = new JLabel("Time Left: " + game.getTimer().getTimeRemaining() / 1000.0);
        this.game = game;

        //add(health);
        add(time);
        //add(hint);
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
