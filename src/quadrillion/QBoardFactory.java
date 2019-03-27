package quadrillion;

import java.util.ArrayList;
import java.util.List;

import static quadrillion.QBoardType.*;
/**
 * Defines a factory class for board construction. As of this version, it is empty, because the developers do not
 * have a physical copy of the game.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QBoardFactory {
    public QBoard getBoardOfType(QGrid g1, QGrid g2, QGrid g3, QGrid g4, QBoardType type) {
        switch (type) {
            case BOARD_TYPE_1: {
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(0, -4));
                offsets.add(new QCoordinate(4, -4));
                return new QBoard(grids, offsets, BOARD_TYPE_1);
            }
            case BOARD_TYPE_2: {
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(2, 4));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(2, -4));
                return new QBoard(grids, offsets, BOARD_TYPE_4);
            }
            case BOARD_TYPE_3: {
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 2));
                offsets.add(new QCoordinate(6, -2));
                offsets.add(new QCoordinate(2, -4));
                return new QBoard(grids, offsets, BOARD_TYPE_4);
            }
        }
        return null;
    }
}
