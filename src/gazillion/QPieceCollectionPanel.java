package gazillion;

import quadrillion.QCoordinate;
import quadrillion.QPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * QPieceCollectionPanel
 * TODO: SCALE THE PIECES (AS OF NOW, IT DOES NOT FIT INTO THE SCREEN)
 * @author Unsal Ozturk
 * @version 20190328
 */
public class QPieceCollectionPanel extends QPanel {
    private List<QPiece> pieces;
    private List<QPieceDisplay> pieceDisplays;
    private QTheme theme;

    public QPieceCollectionPanel(QPanel parent, QFrame frame, List<QPiece> pieces, QTheme theme) {
        super(parent, frame);
        this.pieces = pieces;
        this.theme = theme;
        setLayout(new GridLayout(4,3));
        pieceDisplays = new ArrayList<>();
        for(int i = 0; i < pieces.size(); i++) {
            QPieceDisplay pieceDisplay = new QPieceDisplay(this, frame, pieces.get(i), theme.getAssets().get(i));
            pieceDisplays.add(pieceDisplay);
            add(pieceDisplay);
        }
    }

    public QPiece getHostedPiece(Component display) {
        return pieceDisplays.get(pieceDisplays.indexOf(display)).getPiece();
    }

    public boolean getAvailabilityOfDisplay(Component display) {
        return pieceDisplays.get(pieceDisplays.indexOf(display)).available;
    }

    public QPieceDisplay getDisplayOfHostedPiece(QPiece piece) {
        for(QPieceDisplay display: pieceDisplays) {
            if(piece.getPieceType() == display.getPiece().getPieceType()) {
                return display;
            }
        }
        return null;
    }

    public class QPieceDisplay extends QPanel {
        private QPiece piece;
        private BufferedImage pieceImage;
        private boolean available;
        public QPieceDisplay(QPanel parent, QFrame frame, QPiece piece, BufferedImage pieceImage) {
            super(parent, frame);
            this.piece = piece;
            this.pieceImage = pieceImage;
            available = true;
            setBorder(BorderFactory.createLineBorder(Color.black));
            setPreferredSize(new Dimension(200,50));
        }

        public void setAvailable(boolean b) {
            available = b;
        }

        public QPiece getPiece() {
            return piece;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(available) {
                int midX = getSize().width / 2;
                int midY = getSize().height / 2;
                int offsetX = midX - theme.getSize() / 2;
                int offsetY = midY - theme.getSize() / 2;
                Graphics2D g2d = (Graphics2D) g;
                List<QCoordinate> coordinates = piece.getPieceCoordinates();
                for(QCoordinate q: coordinates) {
                    int x = offsetX + q.x() * theme.getSize() - (int)(piece.getCenterOfMassX() * theme.getSize());
                    int y = offsetY - q.y() * theme.getSize() + (int)(piece.getCenterOfMassY() * theme.getSize());
                    g2d.drawImage(pieceImage, x, y, null);
                }
            }
            revalidate();
            repaint();
        }

        @Override
        public String toString() {
            return piece.getPieceType() + " Component hosts";
        }
    }
}
