package gazillion;

import quadrillion.QCoordinate;
import quadrillion.QGame;
import quadrillion.QPiece;
import logic.*;
import utils.Message;
import utils.Observer;
import utils.QSoundLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import static utils.Message.*;

/**
 * QGazillionPanel
 * Safety goggles required
 * Game is bug-free but needs polishing (good assets, interesting themes, etc.)
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QGazillionPanel extends QPanel implements Observer {
    private QTheme theme;
    private QGame game;
    private QPlayer player;
    private QGamePanel gamePanel;
    private QPieceCollectionPanel piecePanel;
    private QPiece selectedPiece;
    private QPlayerInfoPanel playerInfo;
    private QUtilityPanel util;
    private JButton giveUp;
    private boolean locked;
    private int musicID;

    private int mouseX;
    private int mouseY;

    private static final int CORNER_OFFSET_X = 100;
    private static final int CORNER_OFFSET_Y = 250;

    private QModePanel parent;

    public QGazillionPanel(QModePanel parent, QFrame frame, QPlayer player, QTheme theme, QGame game) {
        super( parent, frame);
        this.parent = parent;

        musicID = -1;
        this.locked = false;
        this.player = player;
        this.theme = theme;
        this.game = game;

        gamePanel = new QGamePanel(this, frame);
        gamePanel.setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout());
        QGameListener gameListener = new QGameListener();
        gamePanel.addMouseListener(gameListener);
        gamePanel.addMouseWheelListener(gameListener);
        add(gamePanel, BorderLayout.CENTER);

        piecePanel = new QPieceCollectionPanel(this,frame,game.getPieces(),theme);
        add(piecePanel, BorderLayout.EAST);

        piecePanel.addMouseListener(new QPieceCollectionListener());
        gamePanel.addMouseMotionListener(new QGazillionMotionListener());

        playerInfo = new QPlayerInfoPanel(this, frame, player);
        add(playerInfo, BorderLayout.NORTH);

        giveUp = new JButton("Give Up!");
        giveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                giveUp.setEnabled(false);
                game.stopTimer();
                locked = true;
                QSoundLoader.getInstance().playClip("shot");
                musicID = QSoundLoader.getInstance().playSound("air");
                game.notifyObservers( );
                JOptionPane.showMessageDialog(frame, "It's okay to give up. We're cool. Not everybody can be a winner. Definitely not your fault.");
            }
        });
        util = new QUtilityPanel(this,frame,game);
        JButton back = this.getBackButton();
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                QSoundLoader.getInstance().stopSound(musicID);

            }
        });
        util.add(back);
        util.add(this.giveUp);

        add(util, BorderLayout.SOUTH);
        game.addObserver(this);
        game.addObserver(this.parent);
    }

    public class QGazillionMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }
    }

    public class QGameListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(!locked) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    double nx = Math.floor((e.getX() - CORNER_OFFSET_X + theme.getSize() / 2.0) / (theme.getSize()));
                    double ny = -Math.floor((e.getY() - CORNER_OFFSET_Y + theme.getSize() / 2.0) / (theme.getSize()));
                    if (selectedPiece != null) {
                        if (game.placePieceAt(selectedPiece, new QCoordinate((int) nx, (int) ny))) {
                            QSoundLoader.getInstance().playClip("click");
                            piecePanel.getDisplayOfHostedPiece(selectedPiece).setAvailable(false);
                            selectedPiece = null;
                        } else {
                            if (!game.getCoordinateToPieceMap().containsKey(new QCoordinate((int) nx, (int) ny))) {
                                Component c = piecePanel.getDisplayOfHostedPiece(selectedPiece);
                                ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(true);
                                selectedPiece = null;
                            }
                        }
                    } else {
                        QCoordinate coord = new QCoordinate((int) nx, (int) ny);
                        QPiece piece = game.getCoordinateToPieceMap().get(coord);
                        if (piece != null) {
                            game.removePieceAt(coord);
                            selectedPiece = piece;
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (selectedPiece != null) {
                        selectedPiece.flip(true);
                    }
                }
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (!locked) {
                if (selectedPiece != null) {
                    int notches = e.getWheelRotation();
                    System.out.println(notches);
                    boolean direction = true;
                    if (notches < 0) {
                        notches = -notches;
                        direction = false;
                    }
                    for (int i = 0; i < notches; i++) {
                        selectedPiece.rotate90(direction);
                    }
                }
            }
        }
    }

    public class QPieceCollectionListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (true) {
                Component c = e.getComponent().getComponentAt(e.getX(), e.getY());
                if (piecePanel.getAvailabilityOfDisplay(c)) {
                    if (selectedPiece == null) {
                        selectedPiece = piecePanel.getHostedPiece(c);
                        ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(false);
                    } else {
                        piecePanel.getDisplayOfHostedPiece(selectedPiece).setAvailable(true);
                        selectedPiece = piecePanel.getHostedPiece(c);
                        ((QPieceCollectionPanel.QPieceDisplay) c).setAvailable(false);
                    }
                }
            }
        }
    }

    public void update(Message msg) {
        if(msg.isValid()) {
            if (msg.getContents()[GAME_OVER]) {
                QSoundLoader.getInstance().playClip("shot");
                QSoundLoader.getInstance().playClip("die");
                //JOptionPane.showMessageDialog(frame, "You died.");
                game.stopTimer();
                locked = true;

            } else if (msg.getContents()[PLAY_BEAT]) {
                QSoundLoader.getInstance().playClip("beat");
            } else if (msg.getContents()[GAME_WON]){
                game.stopTimer();
                QSoundLoader.getInstance().playClip("victory");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //JOptionPane.showMessageDialog(null, "You are a huge nerd!.");
                    }
                });
                t.run();
                revalidate();
                repaint();
                locked = true;
            }
        }
        util.update(msg);
    }

    public class QGamePanel extends QPanel {
        private static final int IMAGE_SIZE = 50;
        public QGamePanel(QPanel parent, QFrame frame) {
            super(parent, frame);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
            qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
            ((Graphics2D)g).setRenderingHints( qualityHints );

            drawBoard(g);
            drawSelectedPieceAt(g, mouseX, mouseY);
            revalidate();
            repaint();
        }

        public void drawBoard(Graphics g) {
            Map<QCoordinate, QPiece> map = game.getCoordinateToPieceMap();
            Iterator i = map.entrySet().iterator();
            List<QCoordinate> blockers = game.getBlockerCoordinates();
            while (i.hasNext()) {
                Graphics2D g2d = (Graphics2D) g;
                Map.Entry<QCoordinate, QPiece> entry = (Map.Entry)i.next();
                QCoordinate coord = entry.getKey();

                double centerX = CORNER_OFFSET_X + coord.x() * theme.getSize();
                double centerY = CORNER_OFFSET_Y - coord.y() * theme.getSize();
                double radius = theme.getSize() / 2;
                Shape circle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
                g2d.draw(circle);
                try {
                    BufferedImage asset = theme.getAssetOf(((QPiece) (entry.getValue())).getPieceType());
                    g2d.drawImage(asset, (int)centerX - theme.getSize()/2, (int)centerY - theme.getSize()/2, null);
                } catch(NullPointerException e) {

                }

            }
            i = blockers.iterator();
            while (i.hasNext()) {
                Graphics2D g2d = (Graphics2D) g;
                QCoordinate coord = (QCoordinate) i.next();
                double centerX = CORNER_OFFSET_X + coord.x() * theme.getSize();
                double centerY = CORNER_OFFSET_Y - coord.y() * theme.getSize();
                double radius = theme.getSize() / 2;
                Shape blocker = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
                g2d.fill(blocker);
            }
        }

        public void drawSelectedPieceAt(Graphics g, int x, int y) {
            if(selectedPiece != null) {
                BufferedImage asset = theme.getAssetOf(selectedPiece.getPieceType());
                int offsetX = x - theme.getSize() / 2;
                int offsetY = y - theme.getSize() / 2;
                Graphics2D g2d = (Graphics2D) g;
                List<QCoordinate> coordinates = selectedPiece.getPieceCoordinates();
                int avgX = 0;
                int avgY = 0;
                for (QCoordinate q : coordinates) {
                    int lx = offsetX + q.x() * theme.getSize();
                    int ly = offsetY - q.y() * theme.getSize();
                    g2d.drawImage(asset, lx , ly, null);
                }
                revalidate();
                repaint();
            }
        }
    }
}