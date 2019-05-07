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
                /*
                        OO
                        OO
                 */
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
                /*
                        o
                       o o
                        o
                 */
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
                return new QBoard(grids, offsets, BOARD_TYPE_2);
            }
            case BOARD_TYPE_3: {
                /*
                        swastika board
                        hitler sucks
                 */
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
                return new QBoard(grids, offsets, BOARD_TYPE_3);
            }
            case BOARD_TYPE_4: {
                /*
                        oooo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(8, 0));
                offsets.add(new QCoordinate(12, 0));
                return new QBoard(grids, offsets, BOARD_TYPE_4);
            }
            case BOARD_TYPE_5: {
                /*
                        oo
                       oo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(4, 4));
                offsets.add(new QCoordinate(8, 4));
                return new QBoard(grids, offsets, BOARD_TYPE_5);
            }
            case BOARD_TYPE_6: {
                /*
                        oo
                         oo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(4, -4));
                offsets.add(new QCoordinate(8, -4));
                return new QBoard(grids, offsets, BOARD_TYPE_6);
            }
            case BOARD_TYPE_7: {
                /*
                          o
                        ooo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(8, 0));
                offsets.add(new QCoordinate(8, 4));
                return new QBoard(grids, offsets, BOARD_TYPE_7);
            }
            case BOARD_TYPE_8: {
                /*
                        o
                        ooo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(0, -4));
                offsets.add(new QCoordinate(4, -4));
                offsets.add(new QCoordinate(8, -4));
                return new QBoard(grids, offsets, BOARD_TYPE_8);
            }
            case BOARD_TYPE_9: {
                /*
                         o
                        ooo
                 */
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, 0));
                offsets.add(new QCoordinate(8, 0));
                offsets.add(new QCoordinate(4, 4));
                return new QBoard(grids, offsets, BOARD_TYPE_9);
            }
            case BOARD_TYPE_A: {
                List<QCoordinate> offsets = new ArrayList<>();
                List<QGrid> grids = new ArrayList<>();
                grids.add(g1);
                grids.add(g2);
                grids.add(g3);
                grids.add(g4);
                offsets.add(new QCoordinate(0, 0));
                offsets.add(new QCoordinate(4, -2));
                offsets.add(new QCoordinate(0, -4));
                offsets.add(new QCoordinate(4, -6));
                return new QBoard(grids, offsets, BOARD_TYPE_A);
            }
            case BOARD_TYPE_B: {
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
                return new QBoard(grids, offsets, BOARD_TYPE_B);
            }
        }
        return null;
    }
}
