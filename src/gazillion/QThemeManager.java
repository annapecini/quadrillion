package gazillion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * QThemeManager - Actually reads from file.
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QThemeManager {
    private List<QTheme> themes;
    private String fileName;

    public QThemeManager() {
        themes = new ArrayList<>();
        fileName = "/themes/default";
        themes = new ArrayList<>();
        themes.add(readFrom(fileName));
    }

    public List<QTheme> getThemes() {
        return themes;
    }

    public QTheme readFrom(String prefix) {
        List<BufferedImage> images = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            String tmp = i + "";
            try {
                images.add(ImageIO.read(getClass().getResource(prefix+tmp + ".png")));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return new QTheme("Test", images, true, 0);
    }
}
