package quadrillion;

import java.util.ArrayList;
import java.util.List;

import static quadrillion.QGridType.*;

/**
 * Defines a factory class for grid construction. As of this version, it is empty, because the developers do not
 * have a physical copy of the game.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QGridFactory {
    public QGrid getGridOfType(QGridType type) {
        switch(type) {
            case GRID_TYPE_1: {
                List<QCoordinate> front = new ArrayList<>();
                List<QCoordinate> back = new ArrayList<>();
                QCoordinate fb1 = new QCoordinate(0,0);
                QCoordinate fb2 = new QCoordinate(3,0);
                // back coords missing, need to buy the game!
                front.add(fb1);
                front.add(fb2);
                return new QGrid(front, back, GRID_TYPE_1);
            }
            case GRID_TYPE_2: {
                List<QCoordinate> front = new ArrayList<>();
                List<QCoordinate> back = new ArrayList<>();
                QCoordinate fb1 = new QCoordinate(1,0);
                // back coords missing, need to buy the game!
                front.add(fb1);
                return new QGrid(front, back, GRID_TYPE_1);
            }
            case GRID_TYPE_3: {
                List<QCoordinate> front = new ArrayList<>();
                List<QCoordinate> back = new ArrayList<>();
                QCoordinate fb1 = new QCoordinate(1,0);
                QCoordinate fb2 = new QCoordinate(2,0);
                // back coords missing, need to buy the game!
                front.add(fb1);
                front.add(fb2);
                return new QGrid(front, back, GRID_TYPE_1);
            }
            case GRID_TYPE_4: {
                List<QCoordinate> front = new ArrayList<>();
                List<QCoordinate> back = new ArrayList<>();
                QCoordinate fb1 = new QCoordinate(0,0);
                QCoordinate fb2 = new QCoordinate(2,-3);
                // back coords missing, need to buy the game!
                front.add(fb1);
                front.add(fb2);
                return new QGrid(front, back, GRID_TYPE_1);
            }
            default: {
                List<QCoordinate> front = new ArrayList<>();
                List<QCoordinate> back = new ArrayList<>();
                QCoordinate fb1 = new QCoordinate(0,0);
                QCoordinate fb2 = new QCoordinate(3,0);
                // back coords missing, need to buy the game!
                front.add(fb1);
                front.add(fb2);
                return new QGrid(front, back, GRID_TYPE_1);
            }
        }
    }
}
