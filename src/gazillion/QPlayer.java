package gazillion;

import logic.*;
import utils.Message;
import utils.Observable;
import utils.Observer;
import utils.QProfileManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */

public class QPlayer implements Observable {
    private List<Observer> observers;
    private String name;
    private int noHints;
    private int noHealth;
    private int noHealthPowerUp;
    private int noCoins;
    private int noTimeUp;
    private int[][] treasureModeGrid;
    private int[][] levelModeGrid;
    private int[][] treasureGrid;
    private int noPieces;
    private int highestLevel;
    private int lastDisplayedHint;
    private List<String> ownedThemes;
    private String currentTheme;
    private int highScore;

    public QPlayer(String name, int noHints, int noHealth, int noHealthPowerUp, int noCoins, int noTimeUp, List<String> ownedThemes, String currentTheme, int highScore) {
        observers = new ArrayList<>();
        this.name = name;
        this.noHints = noHints;
        this.noCoins = noCoins;
        this.noHealth = noHealth;
        this.noTimeUp = noTimeUp;
        this.noHealthPowerUp = noHealthPowerUp;
        this.treasureModeGrid = null;
        this.treasureGrid = null;
        this.noPieces = 0;
        this.highestLevel = 0;
        this.lastDisplayedHint = 0;
        this.ownedThemes = ownedThemes;
        this.currentTheme = currentTheme;
        this.highScore = highScore;
    }

    @Override
    public void addObserver(Observer o) {
        if(!observers.contains(o))
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o:observers) {
            o.update(new Message("1000"));
        }
    }

    ///////////////////////////////////////////// GETTERS //////////////////////////////////////////////////


    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int hs) {
        this.highScore = hs;
        notifyObservers();
    }

    public List<String> getOwnedThemes() {
        return ownedThemes;
    }

    public void addTheme(String theme) {
        ownedThemes.add(theme);
        notifyObservers();
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String theme) {
        currentTheme = theme;
        notifyObservers();
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

    public int getNoHealthPowerUp() {
        return noHealthPowerUp;
    }

    public int getNoTimeUp() { return noTimeUp; }

    public int getLatestLevel() { return highestLevel; }

    public int[][] getTreasureModeGrid() { return treasureModeGrid; }

    public int getNoPieces() { return noPieces; }

    public int[][] getTreasureGrid() { return treasureGrid; }

    public int getLastDisplayedHint() { return lastDisplayedHint; }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setNoHints(int noHints) {
        this.noHints = noHints;
        notifyObservers();
    }

    public void setNoCoins(int noCoins) {
        this.noCoins = noCoins;
        notifyObservers();
    }

    public void setNoHealthPowerUp(int noHealthPowerUp) {
        this.noHealthPowerUp = noHealthPowerUp;
        notifyObservers();
    }

    public void setHealth(int noHealth) {
        this.noHealth = noHealth;
        notifyObservers();
    }

    public void setNoTimeUp(int noTimeUp) {
        this.noTimeUp = noTimeUp;
        notifyObservers();
    }

    public void setNoPieces(int nr) {
        this.noPieces = nr;
        notifyObservers();
    }

    public void setLatestLevel(int no) {
        this.highestLevel = no;
        notifyObservers();
    }

    public void setLastDisplayedHint(int no) {
        this.lastDisplayedHint = no;
        notifyObservers();
    }

    public void setTreasureModeGrid(int[][] gameGrid) {
        this.treasureModeGrid = gameGrid;
        notifyObservers();
    }

    public void setTreasureGrid(int[][]treasureGrid) {
        this.treasureGrid = treasureGrid;
        notifyObservers();
    }

    public void unpackAward(QAward award) {

        if (award != null) {
            // does not store info about collected pieces in Treasure mode
            noHealthPowerUp = Math.max(0, noHealth + award.getHealthAwardNo());
            noHints = Math.max(0, noHints + award.getHintsAwardNo());
            noCoins = Math.max(0, noCoins + award.getCoinsAwardNo());
            noTimeUp = Math.max(0, noTimeUp + award.getTimeAwardNo());
            notifyObservers();
        }
    }
}