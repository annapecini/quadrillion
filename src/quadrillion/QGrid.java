package quadrillion;

import java.util.List;

/**
 * Defines a Grid class, which implicitly has a bounding box of (0,0) to (3,-3), i.e. composed of a 4x4 QCoordinate grid
 * whose top left corner is situated at (0,0), and bottom right corner is situated at (3,-3). Only the blocking tiles
 * are explicitly stored. Has two faces.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QGrid {
    QGridType type;
    boolean activeFace;
    List<QCoordinate> blockersFace1;
    List<QCoordinate> blockersFace2;

    /**
     * Constructs a grid given the blocking tiles on each face.
     *
     * @param blockersFront List of blocking tiles on one face of the grid.
     * @param blockersBack  List of blocking tiles on the other face of the grid.
     * @param type          The grid type of the grid, one among four grids as present in the original game.
     */
    public QGrid(List<QCoordinate> blockersFront, List<QCoordinate> blockersBack, QGridType type) {
        blockersFace1 = blockersFront;
        blockersFace2 = blockersBack;
        this.type = type;
    }

    /**
     * Flips the face of the grid to the other side.
     */
    public void flip() {
        // swap face
        activeFace = !activeFace;
    }

    /**
     * Rotates the grid 90 degrees clockwise or counter-clockwise.
     *
     * @param direction The direction of the rotation. True indicates a counter-clockwise 90 degree rotation, and false
     *                  indicates a 90 degree clockwise rotation.
     */
    public void rotate90(boolean direction) {
        // perform roto-translation
        // rotate 90 degrees and translate back into original bounding box
        if (direction) {
            for (QCoordinate q : blockersFace1) {
                q.set(-q.y(), q.x() - 3);
            }
        } else {
            for (QCoordinate q : blockersFace2) {
                q.set(q.y() + 3, -q.x());
            }
        }
    }

    /**
     * Returns the coordinates of the blocking tiles of the active face of the grid.
     *
     * @return The list of coordinates of the blocking tiles.
     */
    public List<QCoordinate> getBlockers() {
        // return blocked positions on active face
        if (activeFace) {
            return blockersFace1;
        } else {
            return blockersFace2;
        }
    }

    /**
     * Returns the current face of the grid. Note that the grid has two faces.
     *
     * @return True for one face, false for the other.
     */
    public boolean getCurrentFace() {
        return activeFace;
    }

    /**
     * Returns the grid type of the grid, one among four grids as present in the original game.
     *
     * @return The grid type.
     */
    public QGridType getGridType() {
        return type;
    }
}
