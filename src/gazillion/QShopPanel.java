package gazillion;

import utils.Message;
import utils.Observer;
import utils.QSoundLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QShopPanel extends QPanel implements Observer {

    private static final int TIME_PRICE = 5;
    private static final int HEALTH_PRICE = 5;
    private static final int HINT_PRICE = 5;

    private JPanel timePanel;
    private JPanel healthPanel;
    private JPanel hintPanel;

    private JLabel timeLabel;
    private JLabel healthLabel;
    private JLabel hintLabel;

    private JLabel timePriceLabel;
    private JLabel healthPriceLabel;
    private JLabel hintPriceLabel;

    private JButton buyTime;
    private JButton buyHealth;
    private JButton buyHint;
    private JButton buyTheme;

    private QPlayer player;
    private QThemeManager manager;

    private QThemeDisplay themeDisplay;


    public QShopPanel(QPanel parent, QFrame frame, QPlayer player, QThemeManager man) {
        super(parent, frame);
        this.player = player;
        this.manager = man;
        if(player != null) {
            init();
        }
    }

    public void init() {
        player.addObserver(this);
        timeLabel = new JLabel("Time Power-ups: " + player.getNoTimeUp());
        healthLabel = new JLabel("Health Power-ups: " + player.getNoHealthPowerUp());
        hintLabel = new JLabel("Hint Power-ups: " + player.getNoHints());

        timePriceLabel = new JLabel("Cost: " + TIME_PRICE);
        healthPriceLabel = new JLabel("Cost: " + HEALTH_PRICE);
        hintPriceLabel = new JLabel("Cost: " + HINT_PRICE);

        buyTime = new JButton("Buy Time Power-up");
        buyTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getNoCoins() >= TIME_PRICE) {
                    QSoundLoader.getInstance().playClip("chaching");
                    player.setNoTimeUp(player.getNoTimeUp() + 1);
                    player.setNoCoins(player.getNoCoins()- TIME_PRICE);
                }
            }
        });

        buyHealth = new JButton("Buy Health Power-up");
        buyHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getNoCoins() >= HEALTH_PRICE) {
                    QSoundLoader.getInstance().playClip("chaching");
                    player.setNoHealthPowerUp(player.getNoHealthPowerUp() + 1);
                    player.setNoCoins(player.getNoCoins() - HEALTH_PRICE);
                }
            }
        });

        buyHint = new JButton("Buy Hint Power-up");
        buyHint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getNoCoins() >= HINT_PRICE) {
                    QSoundLoader.getInstance().playClip("chaching");
                    player.setNoHints(player.getNoHints() + 1);
                    player.setNoCoins(player.getNoCoins() - HINT_PRICE);
                }
            }
        });

        themeDisplay = new QThemeDisplay(manager);
        themeDisplay.addObserver(this);

        buyTheme = new JButton("Buy Theme");
        buyTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getNoCoins() >= themeDisplay.getCurrentTheme().getCost()) {
                    QSoundLoader.getInstance().playClip("chaching");
                    player.addTheme(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()));
                    player.setNoCoins(player.getNoCoins() - themeDisplay.getCurrentTheme().getCost());
                    buyTheme.setEnabled(false);
                    buyTheme.setText("You own this theme");
                }
            }
        });

        if(player.getOwnedThemes().contains(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
            buyTheme.setEnabled(false);
            buyTheme.setText("You own this theme");
        } else {
            buyTheme.setEnabled(true);
            buyTheme.setText("Buy theme");
        }

        timePanel = new JPanel();
        healthPanel = new JPanel();
        hintPanel = new JPanel();

        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.Y_AXIS));
        hintPanel.setLayout(new BoxLayout(hintPanel, BoxLayout.Y_AXIS));

        timePanel.add(timeLabel);
        timePanel.add(timePriceLabel);
        timePanel.add(buyTime);

        healthPanel.add(healthLabel);
        healthPanel.add(healthPriceLabel);
        healthPanel.add(buyHealth);

        hintPanel.add(hintLabel);
        hintPanel.add(hintPriceLabel);
        hintPanel.add(buyHint);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1,3,30,30));
        upperPanel.add(timePanel);
        upperPanel.add(healthPanel);
        upperPanel.add(hintPanel);

        JPanel lowerPanel = new JPanel();
        lowerPanel.add(themeDisplay);
        lowerPanel.add(buyTheme);

        setLayout(new GridLayout(3,1));
        add(upperPanel);
        add(lowerPanel);
        add(getBackButton());
    }

    public void setPlayer(QPlayer player) {
        this.player = player;
        removeAll();
        init();
    }

    public void update(Message msg) {
        if(msg.isValid()) {
            if(!msg.getContents()[Message.GAME_OVER]) {
                timeLabel.setText("Time Power-ups: " + player.getNoTimeUp());
                healthLabel.setText("Health Power-ups: " + player.getNoHealthPowerUp());
                hintLabel.setText("Hint Power-ups: " + player.getNoHints());
            } else {
                if(player.getOwnedThemes().contains(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
                    buyTheme.setEnabled(false);
                    buyTheme.setText("You own this theme");
                } else {
                    buyTheme.setEnabled(true);
                    buyTheme.setText("Buy theme");
                }
            }
        }
    }
}
