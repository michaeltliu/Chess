import java.util.HashSet;
import java.util.Set;

public class Board {
    private Set<Piece> pieces;

    public Board() {
        pieces = new HashSet<>();
        for (int i = 0; i < 8; i ++) {
            pieces.add(new Pawn(0,8 + i, this));
            pieces.add(new Pawn(1, 48 + i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.add(new Bishop(0, 2 + 3*i, this));
            pieces.add(new Bishop(1, 58 + 3*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.add(new Knight(0, 1 + 5*i, this));
            pieces.add(new Knight(1, 57 + 5*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.add(new Rook(0, 7*i, this, 1 - i));
            pieces.add(new Rook(1, 56 + 7*i, this, 1 - i));
        }
        pieces.add(new Queen(0, 3, this));
        pieces.add(new Queen(1, 59, this));

        pieces.add(new King(0, 4, this));
        pieces.add(new King(1, 60, this));
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public static boolean onBoard(int row, int col) {
        return (row >= 0 && row < 8 && col >= 0 && col < 8);
    }
}
