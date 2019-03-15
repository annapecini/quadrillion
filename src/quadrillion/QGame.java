package quadrillion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines a game of Quadrillion in the style of Gazillion, using QPiece objects, a QBoard object, a QTimer object, and
 * provides the necessary functionality for piece placement and removal to and from the board.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QGame {
    private QTimer timer;
    private QBoard board;
    private List<QPiece> pieces;
    private Map<QPiece, List<QCoordinate>> pieceToCoordinateMap;
    private Map<QCoordinate, QPiece> coordinateToPieceMap;
    private List<QCoordinate> blockerCoordinates;

    /**
     * Constructor for the game object.
     *
     * @param board         QBoard object on which the game will be played.
     * @param timeRemaining Time before which the level must be completed.
     */
    public QGame(QBoard board, long timeRemaining) {
        // init timer
        timer = new QTimer(timeRemaining);
        // set board object of game
        // TODO: Add neat board init functionality to QGame class
        // Developer may give grid configs in a more precise manner
        this.board = board;
        // init pieces
        pieces = new ArrayList<>();
        QPieceFactory pieceFactory = new QPieceFactory();
        for (QPieceType qType : QPieceType.values()) {
            pieces.add(pieceFactory.getPieceOfType(qType));
        }
        // init lists and maps
        pieceToCoordinateMap = new HashMap<>();
        for (QPiece q : pieces) {
            pieceToCoordinateMap.put(q, null);
        }
        coordinateToPieceMap = new HashMap<>();
        for (QCoordinate q : board.getWorldCoordinates()) {
            coordinateToPieceMap.put(q, null);
        }
        //
        blockerCoordinates = board.getWorldCoordinateOfBlockers();
    }

    /**
     * Places a piece at a specified location on the board, such that the (0,0) coordinate of the piece fits that
     * particular location.
     *
     * @param piece    The QPiece instance to be placed
     * @param location The location at which the piece is to be placed
     * @return True if the piece has been placed correctly, false if the location is occupied by another piece, or
     * coordinate is out of bounds, or blocker is blocking the piece.
     */
    public boolean placePieceAt(QPiece piece, QCoordinate location) {
        // translate piece to the location of placement
        List<QCoordinate> pieceCoords = new ArrayList<>();
        for (QCoordinate q : piece.getPieceCoordinates()) {
            pieceCoords.add(new QCoordinate(q.x() + location.x(), q.y() + location.y()));
        }
        // check available positions
        for (QCoordinate q : pieceCoords) {
            if (coordinateToPieceMap.get(q) != null || blockerCoordinates.contains(q)) {
                // position occupied by a piece or is a blocker
                return false;
            }
        }
        // check complete, place piece
        // do coordinate to piece mapping
        for (QCoordinate q : pieceCoords) {
            coordinateToPieceMap.put(q, piece);
        }
        // do piece to coordinate mapping
        pieceToCoordinateMap.put(piece, pieceCoords);
        return true;
    }

    /**
     * Removes a piece occupying a particular coordinate on the game board.
     *
     * @param location The location on the board from which a piece will be removed.
     * @return True if a piece was removed successfully from the board, false if the remove operation failed, i.e. no
     * piece is available at the desired location.
     */
    public boolean removePieceAt(QCoordinate location) {
        // check coordinate to piece mapping for finding piece to be removed
        QPiece rem = coordinateToPieceMap.get(location);
        if (rem == null) {
            // no piece to remove
            return false;
        }
        // get list of occupied coordinates
        List<QCoordinate> occ = pieceToCoordinateMap.get(rem);
        // remove piece from grid by
        // 1: Setting the piece hosted by the coordinate to null
        // 2: Setting the coordinate filled by the piece to null
        for (QCoordinate q : occ) {
            coordinateToPieceMap.put(q, null);
        }
        pieceToCoordinateMap.put(rem, null);
        return true;
    }

    /**
     * Checks if the game has been won or not.
     *
     * @return True if game was won, false otherwise.
     */
    public boolean hasWon() {
        // win condition: all pieces are in play
        // check if there is a piece that is not in play
        // i.e. the places it occupies is null
        for (QPiece piece : pieces) {
            if (pieceToCoordinateMap.get(piece) == null) {
                // some piece not in play
                return false;
            }
        }
        return true;
    }

    /**
     * Returns if the user has run out of time.
     *
     * @return True if the user has run out of time, false otherwise.
     */
    public boolean isOutOfTime() {
        return timer.isOutOfTime();
    }

    /**
     * Starts the timer object.
     */
    public void startTimer() {
        timer.start();
    }

    /**
     * Stops the timer object.
     */
    public void stopTimer() {
        timer.stop();
    }

    /**
     * Increments the time remaining by a specified amount.
     *
     * @param increment The increment in time in milliseconds.
     */
    public void incrementTimeRemaining(long increment) {
        timer.setTimeRemaining(timer.getTimeRemaining() + increment);
    }
}
