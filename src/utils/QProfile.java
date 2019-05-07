package utils;

import gazillion.QPlayer;
import logic.QTreasureMode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Unsal Ozturk
 * @Version 20190501
 * Wrapper object for QPlayer object serialization
 * Implements the prototype pattern for in-memory caching
 */
public class QProfile implements Serializable, QDiskPersistable, Observer {

    private static final long serialVersionUID = 2124854180970585336L;

    transient public static final int INITIAL_HEALTH = 5;
    transient public static final int INITIAL_HINTS = 5;
    transient public static final int INITIAL_COINS = Integer.MAX_VALUE;
    transient public static final int INITIAL_TIME_UP = 5;
    transient public static final int INITIAL_HEALTH_POWER_UP = 3;
    transient private static final String FILE_DIRECTORY = "\\src\\files\\profiles\\";
    transient private static final String FILE_PATTERN = "profile";
    transient private static final String FILE_EXTENSION = ".gzp";
    transient private QPlayer playerInstance;
    transient boolean valid;

    private int ID;
    private String name;
    private int noHints;
    private int noHealth;
    private int noHealthPowerUp;
    private int noCoins;
    private int noTimeUp;
    private int[][] treasureModeGrid;
    private int[][] treasureGrid;
    private int noPieces;
    private int latestLevel;
    private List<String> ownedThemes;
    private String currentTheme;

    // First time profile creation
    public QProfile(int ID, String name) {
        this.ID = ID;
        this.name = name;
        noHints = INITIAL_HINTS;
        noHealth = INITIAL_HEALTH;
        noCoins = INITIAL_COINS;
        noTimeUp = INITIAL_TIME_UP;
        noHealthPowerUp = INITIAL_HEALTH_POWER_UP;
        ownedThemes = new ArrayList<>();
        ownedThemes.add("Default");
        currentTheme = "Default";
        playerInstance = new QPlayer(name, noHints, noHealth, noHealthPowerUp, noCoins, noTimeUp, ownedThemes, currentTheme);

        treasureModeGrid = null;
        treasureGrid = null;
        noPieces = 0;
        latestLevel = -1;

        playerInstance.addObserver(this);
        valid = true;
        encode();
    }

    // Read profile from disk
    public QProfile(int ID) {
        this.valid = false;
        this.ID = ID;
        QProfile read = (QProfile)decode();
        if(read != null) {
            this.name = read.name;
            this.noHints = read.noHints;
            this.noHealth = read.noHealth;
            this.noHealthPowerUp = read.noHealthPowerUp;
            this.noCoins = read.noCoins;
            this.noTimeUp = read.noTimeUp;
            this.valid = true;
            this.ownedThemes = read.ownedThemes;
            this.currentTheme = read.currentTheme;
            this.playerInstance = new QPlayer(name, noHints, noHealth, noHealthPowerUp, noCoins, noTimeUp, ownedThemes, currentTheme);
            this.playerInstance.addObserver(this);

            treasureModeGrid = read.treasureModeGrid;
            treasureGrid = read.treasureGrid;
            noPieces = read.noPieces;
            latestLevel = read.latestLevel;
        }
    }

    public void update(Message msg) {
        if(msg.isValid()) {
            noHints = playerInstance.getNoHints();
            noCoins = playerInstance.getNoCoins();
            noHealth = playerInstance.getNoHealth();
            noTimeUp = playerInstance.getNoTimeUp();
            noHealthPowerUp = playerInstance.getNoHealthPowerUp();
            noTimeUp = playerInstance.getNoHealthPowerUp();
            ownedThemes = playerInstance.getOwnedThemes();
            treasureModeGrid = playerInstance.getTreasureModeGrid();
            treasureGrid = playerInstance.getTreasureGrid();
            noPieces = playerInstance.getNoPieces();
            latestLevel = playerInstance.getLatestLevel();
            currentTheme = playerInstance.getCurrentTheme();
        }
    }

    public void encode() {
        QFileManager.write(this, FILE_DIRECTORY, FILE_PATTERN + this.ID + FILE_EXTENSION);
    }

    public Object decode() {
        return QFileManager.read(FILE_DIRECTORY, FILE_PATTERN + this.ID + FILE_EXTENSION);
    }

    public boolean delete() {
        return QFileManager.delete(FILE_DIRECTORY, FILE_PATTERN + this.ID + FILE_EXTENSION);
    }

    public int getID() {
        return ID;
    }

    public QPlayer getPlayerInstance() {
        return playerInstance;
    }

    public boolean isValid() {
        return valid;
    }

}
