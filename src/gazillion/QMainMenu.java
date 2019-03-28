package gazillion;

import quadrillion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * QMainMenu: PURELY TEST
 *
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QMainMenu extends QPanel {
    private JButton newGame;
    private JButton resumeGame;
    private QImagePanel background;
    private QGazillionPanel gamePanel;
    public QMainMenu(QPanel parent, QFrame frame) {
        super(parent, frame);
        newGame = new JButton("New Game");
        resumeGame = new JButton("Resume Game");

        background = new QImagePanel(parent, frame, "background.jpg");
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, null);
        }

        ActionListener newGameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QGameBuilder g = new QGameBuilder();
                QGame game = g.setGrid(QGridType.GRID_TYPE_1, 2)
                        .setGrid(QGridType.GRID_TYPE_2, 1)
                        .setGrid(QGridType.GRID_TYPE_3, 0)
                        .setGrid(QGridType.GRID_TYPE_4,1)
                        .setBoard(QBoardType.BOARD_TYPE_1)
                        .build(600000);
                QGridFactory gridFact = new QGridFactory();
                QBoardFactory boardFact = new QBoardFactory();

                QPlayer player = new QPlayer();
                QThemeManager man = new QThemeManager();

                gamePanel = new QGazillionPanel(QMainMenu.this, frame, player, man.getThemes().get(0), game);
                resumeGame.setEnabled(true);
                frame.setActivePanel(gamePanel);
            }
        };

        ActionListener resumeGameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setActivePanel(gamePanel);
            }
        };
        newGame.addActionListener(newGameListener);
        newGame.setEnabled(true);
        resumeGame.addActionListener(resumeGameListener);
        resumeGame.setEnabled(false);
        this.setLayout(new FlowLayout());
        this.add(newGame);
        this.add(resumeGame);
    }

    public void endGame() {
        gamePanel = null;
        resumeGame.setEnabled(false);
    }
}
