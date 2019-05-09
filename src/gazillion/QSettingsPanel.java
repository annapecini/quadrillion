package gazillion;

import utils.Message;
import utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QSettingsPanel extends QPanel implements Observer {
    private QThemeDisplay themeDisplay;
    private QPlayer player;
    private QThemeManager manager;
    private JButton selectTheme;
    public QSettingsPanel(QPanel parent, QFrame frame, QPlayer player, QThemeManager man) {
        super(parent, frame);
        //this.setLayout(new FlowLayout());
        this.setLayout(new GridLayout(2,1,0,0));
        this.setBackground(new Color(65,68,73));
        this.player = player;
        this.manager = man;
        if(player != null) {
            init();
        }
    }

    @Override
    public void update(Message msg) {
        if(msg.isValid() && msg.getContents()[Message.GAME_OVER]) {
            if(player.getCurrentTheme().equals(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
                selectTheme.setEnabled(false);
                selectTheme.setText("Currently Selected");
            } else if(!player.getOwnedThemes().contains(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
                selectTheme.setEnabled(false);
                selectTheme.setText("You do not own this theme.");
            } else {
                selectTheme.setEnabled(true);
                selectTheme.setText("Select Theme");
            }
        }
    }

    public void init() {
        themeDisplay = new QThemeDisplay(manager);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(themeDisplay);
        //this.add(themeDisplay);
        themeDisplay.addObserver(this);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        selectTheme = new JButton("Select Theme");
        if(player.getCurrentTheme().equals(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
            selectTheme.setEnabled(false);
            selectTheme.setText("Currently Selected");
        } else if(!player.getOwnedThemes().contains(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()))) {
            selectTheme.setEnabled(false);
            selectTheme.setText("You do not own this theme.");
        }
        selectTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.setCurrentTheme(manager.getKeyOfTheme(themeDisplay.getCurrentTheme()));
                selectTheme.setEnabled(false);
                selectTheme.setText("Currently Selected");
            }
        });
        
        bottomPanel.add(selectTheme);
        bottomPanel.add(getBackButton());
        this.add(topPanel);
        this.add(bottomPanel);
        
        //this.add(selectTheme);
        //this.add(getBackButton());
    }

    public void setPlayer(QPlayer player) {
        this.player = player;
        removeAll();
        init();
    }


}
