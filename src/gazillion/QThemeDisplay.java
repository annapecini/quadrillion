package gazillion;

import utils.Message;
import utils.Observable;
import utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QThemeDisplay extends JPanel implements Observable {
    private QThemeManager manager;
    private QTheme currentTheme;
    private JButton next;
    private JButton prev;
    private QTheme[] themes;
    private int themeIndex;
    private QThemePreview preview;
    private List<Observer> observers;

    public QThemeDisplay(QThemeManager manager) {
        this.manager = manager;
        observers = new ArrayList<>();
        currentTheme = manager.getTheme("Default");

        themes = new QTheme[QThemeManager.NO_THEMES];
        List<String> themeNames = manager.getThemeNames();
        for(int i = 0; i < QThemeManager.NO_THEMES; i++) {
            themes[i] = manager.getTheme(themeNames.get(i));
        }
        themeIndex = themeNames.indexOf("Default");
        next = new JButton("Next");
        prev = new JButton("Prev");
        preview = new QThemePreview(currentTheme);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                themeIndex = (themeIndex + 1) % themes.length;
                currentTheme = themes[themeIndex];
                preview.setTheme(currentTheme);
                notifyObservers();
            }
        });

        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                themeIndex = (themeIndex - 1) % themes.length;
                if(themeIndex < 0) themeIndex += themes.length;
                currentTheme = themes[themeIndex];
                preview.setTheme(currentTheme);
                notifyObservers();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(prev, BorderLayout.WEST);
        this.add(next, BorderLayout.EAST);
        this.add(preview);
    }

    public QTheme getCurrentTheme() {
        return currentTheme;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(new Message("1100"));
        }
    }

    public class QThemePreview extends JPanel {
        private QTheme theme;
        private static final int OFFSET_X = 50;
        private static final int OFFSET_Y = 75;
        public QThemePreview(QTheme theme) {
            this.theme = theme;
            setPreferredSize(new Dimension(300,300));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 4; j++) {
                    g2d.drawImage(theme.getAssets().get(4*i+j), OFFSET_X + theme.getSize()*i + theme.getSize() / 2, OFFSET_Y + theme.getSize()*j - theme.getSize() / 2, null);
                }
            }
        }

        public void setTheme(QTheme theme) {
            this.theme = theme;
            repaint();
            revalidate();
        }
    }
}
