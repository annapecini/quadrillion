package gazillion;

import utils.Message;
import utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * QPlayerInfoPanel
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QPlayerInfoPanel extends QPanel implements Observer {
    private QPlayer player;
    private JLabel nameLabel;
    private JLabel coinLabel;
    private JLabel healthLabel;
    private JLabel healthPowerUpLabel;
    private JLabel timePowerUpLabel;
    private JLabel hintLabel;
    public QPlayerInfoPanel(QPanel parent, QFrame frame, QPlayer player) {
        super(parent, frame);
        this.player = player;
        this.player.addObserver(this);

        nameLabel = new JLabel("Name: " + player.getName());
        coinLabel = new JLabel(" Coins: " + player.getNoCoins());
        healthLabel = new JLabel(" Health: " + player.getNoHealth() + "                      ");
        healthPowerUpLabel = new JLabel(" Health PowerUps: " + player.getNoHealthPowerUp());
        timePowerUpLabel = new JLabel(" Time PowerUps: " + player.getNoTimeUp());
        hintLabel = new JLabel(" Hints: " + player.getNoHints());

//        nameLabel.setFont(font);
//        coinLabel.setFont(font);
//        healthLabel.setFont(font);
//        healthPowerUpLabel.setFont( font);
//        timePowerUpLabel.setFont( font);
//        hintLabel.setFont( font);


        this.setLayout(new FlowLayout());
        this.add(nameLabel);
        this.add(coinLabel);
        this.add(healthLabel);
        this.add(healthPowerUpLabel);
        this.add(timePowerUpLabel);
        this.add(hintLabel);
    }

    @Override
    public void update(Message msg) {
        if(msg.isValid()) {
            nameLabel.setText("Name: " + player.getName());
            coinLabel.setText(" Coins: " + player.getNoCoins());
            healthLabel.setText(" Health " + player.getNoHealth() + "                      ");
            hintLabel.setText(" Hints: " + player.getNoHints());
            healthPowerUpLabel.setText(" Health PowerUps: " + player.getNoHealthPowerUp());
            timePowerUpLabel.setText(" Time PowerUps: " + player.getNoTimeUp());
        }
    }
}
