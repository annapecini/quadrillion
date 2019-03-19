package quadrillion;

import java.util.ArrayList;
import java.util.List;

import static quadrillion.QPieceType.*;

/**
 * Defines a factory class for pieces as present in the original Quadrillion game.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QPieceFactory {
    // provides definition of pieces, in a local coordinate system

    /**
     * @param type The type of the QPiece to be constructed.
     * @return The QPiece instance with the specified type.
     */
    public QPiece getPieceOfType(QPieceType type) {
        switch (type) {
            case PIECE_TYPE_1: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(0, 1);
                QCoordinate q3 = new QCoordinate(1, 1);
                QCoordinate q4 = new QCoordinate(0, -1);
                QCoordinate q5 = new QCoordinate(1, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_1);
            }
            case PIECE_TYPE_2: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(1, 0);
                QCoordinate q3 = new QCoordinate(2, 0);
                QCoordinate q4 = new QCoordinate(0, -1);
                QCoordinate q5 = new QCoordinate(-1, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_2);
            }
            case PIECE_TYPE_3: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(1, 0);
                QCoordinate q3 = new QCoordinate(1, 1);
                QCoordinate q4 = new QCoordinate(0, -1);
                QCoordinate q5 = new QCoordinate(-1, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_3);
            }
            case PIECE_TYPE_4: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(1, 0);
                QCoordinate q3 = new QCoordinate(-1, 0);
                QCoordinate q4 = new QCoordinate(0, 1);
                QCoordinate q5 = new QCoordinate(1, 1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_4);
            }
            case PIECE_TYPE_5: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(0, 1);
                QCoordinate q3 = new QCoordinate(0, 2);
                QCoordinate q4 = new QCoordinate(-1, 0);
                QCoordinate q5 = new QCoordinate(-2, 0);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_5);
            }
            case PIECE_TYPE_6: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(1, 0);
                QCoordinate q3 = new QCoordinate(-1, 0);
                QCoordinate q4 = new QCoordinate(2, 0);
                QCoordinate q5 = new QCoordinate(-1, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_6);
            }
            case PIECE_TYPE_7: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(1, 0);
                QCoordinate q3 = new QCoordinate(2, 0);
                QCoordinate q4 = new QCoordinate(0, 1);
                QCoordinate q5 = new QCoordinate(0, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_7);
            }
            case PIECE_TYPE_8: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(-1, 0);
                QCoordinate q3 = new QCoordinate(1, 0);
                QCoordinate q4 = new QCoordinate(2, 0);
                QCoordinate q5 = new QCoordinate(0, 1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_8);
            }
            case PIECE_TYPE_9: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(0, 0);
                QCoordinate q3 = new QCoordinate(0, 1);
                QCoordinate q4 = new QCoordinate(1, -1);
                QCoordinate q5 = new QCoordinate(-1, -1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_9);
            }
            case PIECE_TYPE_A: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(-1, 0);
                QCoordinate q2 = new QCoordinate(0, 0);
                QCoordinate q3 = new QCoordinate(0, 1);
                QCoordinate q4 = new QCoordinate(1, 1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                return new QPiece(piece, PIECE_TYPE_A);
            }
            case PIECE_TYPE_B: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(-1, 0);
                QCoordinate q2 = new QCoordinate(0, 0);
                QCoordinate q3 = new QCoordinate(1, 0);
                QCoordinate q4 = new QCoordinate(0, -1);
                QCoordinate q5 = new QCoordinate(1, 1);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                piece.add(q4);
                piece.add(q5);
                return new QPiece(piece, PIECE_TYPE_B);
            }
            case PIECE_TYPE_C: {
                List<QCoordinate> piece = new ArrayList<>();
                QCoordinate q1 = new QCoordinate(0, 0);
                QCoordinate q2 = new QCoordinate(0, 1);
                QCoordinate q3 = new QCoordinate(-1, 0);
                piece.add(q1);
                piece.add(q2);
                piece.add(q3);
                return new QPiece(piece, PIECE_TYPE_C);
            }
            default:
                throw new RuntimeException("Illegal Piece Type");
        }
    }
}
