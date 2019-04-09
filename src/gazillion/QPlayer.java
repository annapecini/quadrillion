package gazillion;

import logic.*;

/**
 *
 * @author
 * @version
 */

public class QPlayer {

    private int initialHealth = 5;
    private int initialHints= 5;
    private int initialCoins = 20;
    private int initialTimeUp = 5;

    private String name;
    private int noHints;
    private int noHealth;
    private int noHealthPowerUp;
    private int noCoins;
    private int noTimeUp;


    public QPlayer( String plname) {
        name = plname;                      // maaybe might check later for stored players
        noHints = initialHints;
        noCoins = initialCoins;
        noHealth = initialHealth;
        noTimeUp = initialTimeUp;
        noHealthPowerUp = 0;
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

    ///////////////////////////////////////////// SETTERS //////////////////////////////////////////////////

    public void setNoHints( int newNo) { noHints = newNo; }

    public void  setNoTimeUp( int newNo ) { noTimeUp = newNo; }

    public void setNoHealthPowerUp( int newNo) { noHealthPowerUp = newNo; }

    public void setHealth( int newNo){ noHealth = newNo;}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void unpackAward( QAward award) {

        if (award != null){
            // does not store info about collected pieces in Treasure mode
            noHealthPowerUp = Math.max(0, noHealth + award.getHealthAwardNo());
            noHints = Math.max(0, noHints + award.getHintsAwardNo());
            noCoins = Math.max(0, noCoins + award.getCoinsAwardNo());
            noTimeUp = Math.max(0, noTimeUp + award.getTimeAwardNo());
        }
    }
}