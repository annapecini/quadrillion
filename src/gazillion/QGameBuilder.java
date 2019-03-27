package gazillion;

import quadrillion.*;

/**
 * QGameBuilder: NOT THREAD SAFE.
 * Comment: Fancy builder pattern
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QGameBuilder {
    private QGrid g1;
    private QGrid g2;
    private QGrid g3;
    private QGrid g4;
    private QBoard b;

    public QGameBuilder() {}

    public Expression1 setGrid(QGridType type, int noClockWiseRotations) {
        QGridFactory fact = new QGridFactory();
        g1 = fact.getGridOfType(type);
        for(int i = 0; i < noClockWiseRotations % 4; i++) {
            g1.rotate90(true);
        }
        return new Expression1();
    }

    public class Expression1 {
        public Expression2 setGrid(QGridType type, int noClockWiseRotations) {
            QGridFactory fact = new QGridFactory();
            g2 = fact.getGridOfType(type);
            for(int i = 0; i < noClockWiseRotations % 4; i++) {
                g2.rotate90(true);
            }
            return new Expression2();
        }
    }

    public class Expression2 {
        public Expression3 setGrid(QGridType type, int noClockWiseRotations) {
            QGridFactory fact = new QGridFactory();
            g3 = fact.getGridOfType(type);
            for(int i = 0; i < noClockWiseRotations % 4; i++) {
                g3.rotate90(true);
            }
            return new Expression3();
        }
    }

    public class Expression3 {
        public Expression4 setGrid(QGridType type, int noClockWiseRotations) {
            QGridFactory fact = new QGridFactory();
            g4 = fact.getGridOfType(type);
            for(int i = 0; i < noClockWiseRotations % 4; i++) {
                g4.rotate90(true);
            }
            return new Expression4();
        }
    }

    public class Expression4 {
        public Expression5 setBoard(QBoardType type) {
            QBoardFactory fact = new QBoardFactory();
            b = fact.getBoardOfType(g1,g2,g3,g4,type);
            return new Expression5();
        }
    }

    public class Expression5 {
        public QGame build(int time) {
            return new QGame(b, time);
        }
    }

}
