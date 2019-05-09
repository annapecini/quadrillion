package gazillion;

import utils.QProfileManager;
import utils.QSoundLoader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main {
    public static void main(String[] args) {
        QFrame frame = new QFrame();
        QProfileMenu prof = new QProfileMenu(null, frame);
        frame.setActivePanel(prof);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.dispose();
                QProfileManager.persistProfiles();
                System.exit(0);
            }
        });
    }
}
