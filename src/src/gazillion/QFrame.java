package gazillion;

import javax.swing.*;

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

        setSize(1280, 1024);
        setVisible(true);
    }
}
