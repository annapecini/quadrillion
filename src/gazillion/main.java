package gazillion;

import utils.QProfileManager;
import utils.QSoundLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class main {
    public static void main(String[] args) {

        setUIFont( getCoolFont());
        //QSoundLoader.getInstance().playClip("victory");
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

    public static void setUIFont ( Font font){
        UIManager.put("Button.font", font);
        UIManager.put("ToggleButton.font",font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("ColorChooser.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.font", font);
        UIManager.put("MenuBar.font",font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("PopupMenu.font",font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("Panel.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("TabbedPane.font",font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font",font);
        UIManager.put("TextArea.font",font);
        UIManager.put("TextPane.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("Tree.font", font);
    }



    public static Font getCoolFont(){

        Font font;
        try {
            //create the font to use. Specify the size!
            InputStream myStream = new BufferedInputStream(new FileInputStream("C:/Windows/Fonts/Gameplay.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            font = font.deriveFont(Font.PLAIN, 12);

        } catch (IOException |FontFormatException e) {
            font = new Font("Serif", Font.BOLD, 12);
            e.printStackTrace();
        }

        return font;
    }
}
