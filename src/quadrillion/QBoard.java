package quadrillion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines a board class, composed of four QGrid objects, and maps the necessary geometric transformations from
 * the local coordinate system of the grids to the "world" coordinate system of the board.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QBoard {
    private QBoardType type;
    private Map<QGrid, QCoordinate> localToWorld;
    private List<QGrid> grids;

    /**
     * @param grids   The list of QGrid objects that make up the game board.
     * @param offsets The list of offsets that translate a grid from (0,0) in board coordinates to its offset.
     * @param type    The type of the board among 11 types, as present in the original game manual.
     */
    public QBoard(List<QGrid> grids, List<QCoordinate> offsets, QBoardType type) {
        this.grids = grids;
        this.localToWorld = new HashMap<>();
        for (int i = 0; i < grids.size(); i++) {
            localToWorld.put(grids.get(i), offsets.get(i));
        }
        this.type = type;
    }

    /**
     * Returns the type of the board.
     *
     * @return The type of the board.
     */
    public QBoardType getBoardType() {
        return type;
    }

    /**
     * Returns the coordinates of the blocking coordinates in the coordinate system of the board.
     *
     * @return Board coordinates of blockers.
     */
    public List<QCoordinate> getWorldCoordinateOfBlockers() {
        List<QCoordinate> blockerPos = new ArrayList<>();
        for (QGrid grid : grids) {
            List<QCoordinate> blockers = grid.getBlockers();
            // translate from local to world coordinates
            for (QCoordinate q : blockers) {
                QCoordinate offset = localToWorld.get(grid);
                blockerPos.add(new QCoordinate(q.x() + offset.x(), q.y() + offset.y()));
            }
        }
        return blockerPos;
    }

    /**
     * Returns the explicit list of coordinates of the board.
     *
     * @return The explicit list of coordinates of the board.
     */
    public List<QCoordinate> getWorldCoordinates() {
        List<QCoordinate> worldCoords = new ArrayList<>();
        for (QGrid grid : grids) {
            QCoordinate offset = localToWorld.get(grid);
            // each grid has coordinates (0,0) to (3,-3) implicitly
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j > -4; j--) {
                    worldCoords.add(new QCoordinate(i + offset.x(), j + offset.y()));
                }
            }
        }
        return worldCoords;
    }
}
