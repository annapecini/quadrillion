package gazillion;

import java.awt.*;
import java.swing.*;

public class QImagePanel extends QPanel {
    private BufferedImage image;

    pulic QImagePanel(QPanel parent, QFrame frame, String image){
        super(parent, frame);
        this.image = ImageIO.read(new File(image));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}