import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private Map<Integer, Piece> pieces;

    public Board() {
        pieces = new HashMap<>();
        for (int i = 0; i < 8; i ++) {
            pieces.put(8 + i, new Pawn(1,8 + i, this));
            pieces.put(48 + i, new Pawn(0, 48 + i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(2 + 3*i, new Bishop(1, 2 + 3*i, this));
            pieces.put(58 + 3*i, new Bishop(0, 58 + 3*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(1 + 5*i, new Knight(1, 1 + 5*i, this));
            pieces.put(57 + 5*i, new Knight(0, 57 + 5*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(7*i, new Rook(1, 7*i, this, 1 - i));
            pieces.put(56 + 7*i, new Rook(0, 56 + 7*i, this, 1 - i));
        }
        pieces.put(3, new Queen(1, 3, this));
        pieces.put(59, new Queen(0, 59, this));

        pieces.put(4, new King(1, 4, this));
        pieces.put(60, new King(0, 60, this));
    }

    public Map<Integer, Piece> getPieces() {
        return pieces;
    }

    public static boolean onBoard(int row, int col) {
        return (row >= 0 && row < 8 && col >= 0 && col < 8);
    }
}
