package gazillion;

import quadrillion.QPieceType;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * QTheme
 * Actually reads from file
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QTheme {
    private List<BufferedImage> tileSet;
    private boolean isUnlocked;
    private int cost;
    private String name;

    public QTheme(String name, List<BufferedImage> tileSet, boolean isUnlocked, int cost) {
        this.name = name;
        this.tileSet = tileSet;
        this.isUnlocked = isUnlocked;
        this.cost = cost;
    }

    public List<BufferedImage> getAssets() {
        return tileSet;
    }

    public BufferedImage getAssetOf(QPieceType type) {
        QPieceType[] vals = QPieceType.values();
        for(int i = 0; i < vals.length; i++) {
            if(vals[i] == type) {
                return tileSet.get(i);
            }
        }
        return null;
    }


    public int getCost() {
        return cost;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return tileSet.get(0).getHeight();
    }
}
