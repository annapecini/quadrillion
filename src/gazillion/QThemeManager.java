package gazillion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * QThemeManager - Actually reads from file.
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QThemeManager {
    public static final int NO_THEMES = 3;
    private Map<String, QTheme> themes;
    private Map<QTheme, String> themes2;
    private static final String DEFAULT = "/themes/default/default";
    private static final String THEME1 = "/themes/theme1/theme1";
    private static final String THEME2 = "/themes/theme2/theme2";

    public QThemeManager() {
        themes = new HashMap<>();
        themes2 = new HashMap<>();

        QTheme theme1 = readFrom(THEME1);
        theme1.setMessage("You're not as smart as Pollefeys :p");

        QTheme theme2 = readFrom(THEME2);
        theme2.setMessage("The good old switcheroo!");

        QTheme defTh = readFrom(DEFAULT);
        defTh.setMessage("You gave up :(");

        themes.put("Default", defTh);
        themes.put("Theme1", theme1);
        themes.put("Theme2", theme2);

        themes2.put(defTh, "Default");
        themes2.put(theme1,"Theme1");
        themes2.put(theme2,"Theme2");
    }

    public QTheme getTheme(String theme) {
        return themes.get(theme);
    }

    public List<String> getThemeNames() {
        return new ArrayList<>(themes.keySet());
    }

    public String getKeyOfTheme(QTheme theme) {
        return themes2.get(theme);
    }


    public QTheme readFrom(String prefix) {
        List<BufferedImage> images = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            String tmp = i + "";
            try {
                images.add(ImageIO.read(getClass().getResource(prefix + tmp + ".png")));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return new QTheme(prefix, images, true, 20);
    }
}
