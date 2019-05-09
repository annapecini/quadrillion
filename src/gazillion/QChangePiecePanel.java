package gazillion;

import logic.QModePanel;
import quadrillion.QGame;
import quadrillion.QPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class QChangePiecePanel extends QPanel{

    private QGame game;
    private QTheme theme;
    QPiece selectedPiece;
    QPieceCollectionPanel piecePanel;
    QGazillionPanel parent2;

    public QChangePiecePanel(QModePanel parent, QFrame frame, QPlayer player, QTheme theme, QGame game, QGazillionPanel parentGaz){
        super( parent, frame);
        this.game = game;
        this.theme = theme;
        selectedPiece = null;
        parent2 = parentGaz;

        this.setLayout( new BorderLayout());

        piecePanel = new QPieceCollectionPanel(this,frame ,game.getPieces(),theme);
        piecePanel.addMouseListener( new QPieceCollectionListener2());

        setPreferredSize( new Dimension(300,300));
        add( piecePanel, BorderLayout.CENTER);

        JButton ok = new JButton("OK");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( selectedPiece == null)
                    selectedPiece = parent2.getSelectedPiece();
                else {
                    List<QPiece> list = game.getPieces();

                    for (QPiece piece : list) {
                        if (piece.getID() == parent2.getSelectedPiece().getID()) {
                            piece.setPiece(selectedPiece.getPieceType());
                            parent2.getQPieceCollectionPanel().refresh( list);
                            break;
                        }
                    }
                }
                frame.setActivePanel( parent2);
            }
        });

        add(ok, BorderLayout.SOUTH);
    }

    public class QPieceCollectionListener2 extends MouseAdapter {
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
}
