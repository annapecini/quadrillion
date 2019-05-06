package gazillion;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        QFrame frame = new QFrame();
        //QPanel menu = new QMainMenu(null, frame);
        QPanel menu = new QMainMenu(null, frame);
        System.out.println("a");
        frame.setLayout(new BorderLayout());
        frame.setActivePanel(menu);
        //frame.add(menu, BorderLayout.CENTER);
        //frame.pack();
        //frame.setContentPane(menu);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setVisible(true);
    }
}

