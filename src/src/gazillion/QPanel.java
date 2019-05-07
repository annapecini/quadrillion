package gazillion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * QPanel
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QPanel extends JPanel {
    protected QPanel parent;
    protected QFrame frame;

    public QPanel(QPanel parent, QFrame frame) {
        super();
        this.parent = parent;
        this.frame = frame;
    }

    public QPanel getParentPanel() {
        return parent;
    }

    public JButton getBackButton() {
        if(parent != null) {
            JButton button = new JButton("Back");
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setActivePanel(parent);
                }
            });
            return button;
        }
        return null;
    }
}
