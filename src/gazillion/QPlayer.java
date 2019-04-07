package gazillion;

/**
 * QPlayer: PURELY TEST
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QPlayer {
    private String name;
    private int noHints;
    private int noHealth;
    private int noCoins;
    private int noTimeUp;

    public QPlayer() {
        noHints = 0;
        noCoins = 221;
        noHealth = 3;
        noTimeUp = 5;
        name = "John Doe";

    }

    public String getName() {
        return name;
    }

    public int getNoHints() {
        return noHints;
    }

    public int getNoHealth() {
        return noHealth;
    }

    public int getNoCoins() {
        return noCoins;
    }

    public int getNoTimeUp() {
        return noTimeUp;
    }

    public void setNoHints(int noHints) {
        this.noHints = noHints;
    }

    public void setNoHealth(int noHealth) {
        this.noHealth = noHealth;
    }

    public void setNoCoins(int noCoins) {
        this.noCoins = noCoins;
    }

    public void setNoTimeUp(int noTimeUp) {
        this.noTimeUp = noTimeUp;
    }
}
