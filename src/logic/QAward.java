package logic;
import quadrillion.*;

/**
 *
 * @author
 * @version
 */

public class QAward {
    private int healthAwardNo;
    private int coinsAwardNo;
    private int hintsAwardNo;
    private int timeAwardNo;
    private QPieceType pieceAward;

    public QAward( int health, int hints, int coins, int time, QPieceType piece) {
        healthAwardNo = health;
        hintsAwardNo = hints;
        coinsAwardNo = coins;
        timeAwardNo = time;
        pieceAward = piece;
    }

    public int getHealthAwardNo() {
        return healthAwardNo;
    }

    public int getHintsAwardNo() {
        return hintsAwardNo;
    }

    public int getCoinsAwardNo() {
        return coinsAwardNo;
    }

    public int getTimeAwardNo() {
        return timeAwardNo;
    }

    public QPieceType getPieceAward() {
        return pieceAward;
    }
}