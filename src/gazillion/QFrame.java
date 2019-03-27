package gazillion;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * QFrame
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QFrame extends JFrame {
    private QPanel activePanel;
    public QFrame() {
        super();
        init();
    }

    public void setActivePanel(QPanel panel) {
        if(activePanel != null) {
            remove(activePanel);
        }
        this.activePanel = panel;
        this.add(activePanel);
        //pack();
        revalidate();
        repaint();
    }

    private void init() {
        setTitle("Gazillion");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1280, 1024);
        setVisible(true);
    }
}
