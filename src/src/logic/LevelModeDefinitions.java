package logic;

import gazillion.QGameBuilder;
import quadrillion.QBoardType;
import quadrillion.QGame;
import quadrillion.QGridType;

public class LevelModeDefinitions {

    public static QGame getLevel( int index) {

        QGameBuilder builder = new QGameBuilder();

        return builder.setGrid(QGridType.values()[index % 4], 0)
                .setGrid(QGridType.values()[(index + 1) % 4], 0)
                .setGrid(QGridType.values()[(index + 2) % 4], 0)
                .setGrid(QGridType.values()[(index + 3) % 4], 0)
                .setBoard(QBoardType.values()[index % 11])
                .build(-1);
    }
}
