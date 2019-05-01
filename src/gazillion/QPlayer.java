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


    public QPlayer(String name, int noHints, int noHealth, int noHealthPowerUp, int noCoins, int noTimeUp) {
        observers = new ArrayList<>();
        this.name = name;
        this.noHints = noHints;
        this.noCoins = noCoins;
        this.noHealth = noHealth;
        this.noTimeUp = noTimeUp;
        this.noHealthPowerUp = noHealthPowerUp;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o:observers) {
            o.update(new Message("1000"));
        }
    }

    ///////////////////////////////////////////// GETTERS //////////////////////////////////////////////////
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

    public int getNoTimeUp() {
        return noTimeUp;
    }


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