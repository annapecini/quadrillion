package gazillion;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        QFrame frame = new QFrame();
        QPanel menu = new QMainMenu(null, frame);
        System.out.println("a");
        frame.setActivePanel(menu);
    }
}
