package quadrillion;

import java.util.List;

/**
 * Defines a piece by storing the coordinates occupied by the piece explicitly in its local coordinate system. Provides
 * functionality for rotation and the flipping of the pieces.
 *
 * @author Unsal Ozturk - uensalo
 * @version 20190316
 */
public class QPiece {
    List<QCoordinate> pieceCoordinates;
    QPieceType pieceType;

    /**
     * Constructs a QPiece instance given its coordinates and piece type.
     *
     * @param coords Coordinates which the QPiece instance will occupy.
     * @param type   The type of the piece as present in the original game, one among the 12 types.
     */
    QPiece(List<QCoordinate> coords, QPieceType type) {
        this.pieceCoordinates = coords;
        this.pieceType = type;
    }

    /**
     * Rotates the piece 90 degrees, clockwise or counter-clockwise according to the parameter supplied.
     *
     * @param direction True indicates a counter-clockwise rotation, false indicates a clockwise rotation.
     */
    public void rotate90(boolean direction) {
        if (direction) {
            for (QCoordinate q : pieceCoordinates) {
                q.set(-q.y(), q.x());
            }
        } else {
            for (QCoordinate q : pieceCoordinates) {
                q.set(q.y(), -q.x());
            }
        }
    }

    /**
     * Flips the piece along the x or y axis depending on the parameter supplied.
     *
     * @param direction True indicates a flip along the x axis, false indicates a flip along the y axis.
     */
    public void flip(boolean direction) {
        if (direction) {
            for (QCoordinate q : pieceCoordinates) {
                q.set(-q.x(), q.y());
            }
        } else {
            for (QCoordinate q : pieceCoordinates) {
                q.set(q.x(), -q.y());
            }
        }
    }

    /**
     * Returns a list of the coordinates which the piece occupies, in local coordinates.
     *
     * @return The list of occupied coordinates in the local coordinate system.
     */
    public List<QCoordinate> getPieceCoordinates() {
        return pieceCoordinates;
    }

    /**
     * Returns the type of the piece as present in the original game, one among the 12 types.
     *
     * @return The type of the piece.
     */
    public QPieceType getPieceType() {
        return pieceType;
    }

    public double getCenterOfMassX() {
        double avgX = 0;
        for(QCoordinate q: pieceCoordinates) {
            avgX += q.x();
        }
        return avgX / pieceCoordinates.size();
    }

    public double getCenterOfMassY() {
        double avgY = 0;
        for(QCoordinate q: pieceCoordinates) {
            avgY += q.y();
        }
        return avgY / pieceCoordinates.size();
    }
}
