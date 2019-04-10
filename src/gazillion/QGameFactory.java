package gazillion;

import quadrillion.QBoardType;
import quadrillion.QGame;
import quadrillion.QGridType;

import java.util.Random;

public class QGameFactory {
    public QGame getRandomQGame() {
        int[] types = new int[4];
        for(int i = 0; i < 4; i++) {
            types[i] = i;
        }

        /* Fisher-Yates shuffle, shamelessly stolen from Knuth's Art of Computer Programming*/
        Random rng = new Random();
        for (int i = 3; i >=1; i--) {

            int j = rng.nextInt(i+1);
            int tmp = types[i];
            types[i] = types[j];
            types[j] = tmp;
        }


        QGameBuilder builder = new QGameBuilder();
        return builder.setGrid(QGridType.values()[types[0]],rng.nextInt(4))
                      .setGrid(QGridType.values()[types[1]],rng.nextInt(4))
                      .setGrid(QGridType.values()[types[2]],rng.nextInt(4))
                      .setGrid(QGridType.values()[types[3]],rng.nextInt(4))
                      .setBoard(QBoardType.values()[rng.nextInt(QBoardType.values().length)])
                      .build(600000);
    }
}
