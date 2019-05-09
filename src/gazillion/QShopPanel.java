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
    
    private JPanel profilePanel;

//    private JLabel timePriceLabel;
//    private JLabel healthPriceLabel;
//    private JLabel hintPriceLabel;
    
    private JLabel timeIconLabel;
    private JLabel hintIconLabel;
    private JLabel healthIconLabel;
    
    private JLabel coinIconLabel1;    
    private JLabel coinIconLabel2;
    private JLabel coinIconLabel3;

    private JButton buyTime;
    private JButton buyHealth;
    private JButton buyHint;
    private JButton buyTheme;
    
    private JButton backButton;

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
        timeLabel = new JLabel("" + player.getNoTimeUp());
        healthLabel = new JLabel("" + player.getNoHealthPowerUp());
        hintLabel = new JLabel("" + player.getNoHints());
//
//        timePriceLabel = new JLabel("Cost: " + TIME_PRICE);
//        healthPriceLabel = new JLabel("Cost: " + HEALTH_PRICE);
//        hintPriceLabel = new JLabel("Cost: " + HINT_PRICE);

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
        /////////////

        timePanel = new JPanel();
        
        timePanel.setBackground(new Color(65,68,73));
        
        healthPanel = new JPanel();
        healthPanel.setBackground(new Color(65,68,73));
        
        hintPanel = new JPanel();
        hintPanel.setBackground(new Color(65,68,73));
        
        timePanel.setLayout(null);
        healthPanel.setLayout(null);
        hintPanel.setLayout(null);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1,3,30,30));
        upperPanel.setBackground(new Color(65,68,73));
        upperPanel.add(timePanel);
        
        //timeLabel = new JLabel("");
        timeLabel.setFont(new Font("Serif", Font.BOLD, 48));
        timeLabel.setForeground(Color.red);
        timeLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Time-Power-Up.png")));
        timeLabel.setBounds(118, 47, 255, 40);
        timePanel.add(timeLabel);
        
        timeIconLabel = new JLabel("");
        timeIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/time2.jpg")));
        timeIconLabel.setBounds(67, 47, 41, 40);
        timePanel.add(timeIconLabel);
        
        coinIconLabel1 = new JLabel("5");
        coinIconLabel1.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel1.setBounds(98, 118, 76, 50);
        timePanel.add(coinIconLabel1);
        
        buyTime = new JButton("Buy");
        buyTime.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(player.getNoCoins() >= TIME_PRICE) {
                    QSoundLoader.getInstance().playClip("chaching");
                    player.setNoTimeUp(player.getNoTimeUp() + 1);
                    player.setNoCoins(player.getNoCoins()- TIME_PRICE);
                }
        	}
        });
        buyTime.setBounds(184, 132, 66, 23);
        timePanel.add(buyTime);
        upperPanel.add(healthPanel);
        
        //healthLabel = new JLabel("");
        healthLabel.setFont(new Font("Serif", Font.BOLD, 48));
        healthLabel.setForeground(Color.red);
        healthLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Health-Power-Up.png")));
        healthLabel.setBounds(107, 50, 260, 44);
        healthPanel.add(healthLabel);
        
        healthIconLabel = new JLabel("");
        healthIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/health2.jpg")));
        healthIconLabel.setBounds(52, 50, 47, 44);
        healthPanel.add(healthIconLabel);
        
        coinIconLabel2 = new JLabel("5");
        coinIconLabel2.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel2.setBounds(87, 126, 73, 44);
        healthPanel.add(coinIconLabel2);
        
        buyHealth = new JButton("Buy");
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
        buyHealth.setBounds(170, 137, 66, 23);
        healthPanel.add(buyHealth);
        upperPanel.add(hintPanel);
        
        //hintLabel = new JLabel("");
        hintLabel.setFont(new Font("Serif", Font.BOLD, 48));
        hintLabel.setForeground(Color.red);
        hintLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/Hint-Power-Up.png")));
        hintLabel.setBounds(131, 52, 230, 45);
        hintPanel.add(hintLabel);
        
        hintIconLabel = new JLabel("");
        hintIconLabel.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/hint2.jpg")));
        hintIconLabel.setBounds(74, 52, 47, 45);
        hintPanel.add(hintIconLabel);
        
        coinIconLabel3 = new JLabel("5");
        coinIconLabel3.setIcon(new ImageIcon(ShopPanel.class.getResource("/img/coins2.png")));
        coinIconLabel3.setBounds(130, 122, 66, 57);
        hintPanel.add(coinIconLabel3);
        
        buyHint = new JButton("Buy");

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
        buyHint.setBounds(212, 139, 66, 23);
        hintPanel.add(buyHint);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(new Color(65,68,73));
        
        lowerPanel.setLayout(new GridLayout(1, 2, 100, 50));
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(65,68,73));
        
        JPanel buyThemePanel = new JPanel();
        buyThemePanel.setBackground(new Color(65,68,73));
        //buyThemePanel.setLayout(new BorderLayout());
        
        buyThemePanel.add(themeDisplay);
     
        //lowerPanel.add(themeDisplay);
        //lowerPanel.add(buyTheme);
        
        lowerPanel.add(buyThemePanel);
        lowerPanel.add(profilePanel);

        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(65,68,73));
        backPanel.setLayout(null);
        backButton = getBackButton();
        
        buyTheme.setBounds(197, 11, 200, 23);
        backPanel.add(buyTheme);
        
        backButton.setBounds(42, 96, 89, 23);
        backPanel.add(backButton);
                
        setLayout(new GridLayout(3,1));
        add(upperPanel);
        add(lowerPanel);
        add(backPanel);
        ////////////////
        
        
    }

    public void setPlayer(QPlayer player) {
        this.player = player;
        removeAll();
        init();
    }

    public void update(Message msg) {
        if(msg.isValid()) {
            if(!msg.getContents()[Message.GAME_OVER]) {
                timeLabel.setText("" + player.getNoTimeUp());
                healthLabel.setText("" + player.getNoHealthPowerUp());
                hintLabel.setText("" + player.getNoHints());
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
