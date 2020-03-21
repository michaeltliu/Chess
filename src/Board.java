import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Integer, Piece> pieces;
    int c;

    public Board(int userColor) {
        pieces = new HashMap<>();
        c = userColor;

        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 8; i ++) {
            pieces.put(8 + i, new Pawn(1 - c,8 + i, this));
            pieces.put(48 + i, new Pawn(c, 48 + i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(2 + 3*i, new Bishop(1 - c, 2 + 3*i, this));
            pieces.put(58 + 3*i, new Bishop(c, 58 + 3*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(1 + 5*i, new Knight(1 - c, 1 + 5*i, this));
            pieces.put(57 + 5*i, new Knight(c, 57 + 5*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(7*i, new Rook(1 - c, 7*i, this, 1 - i));
            pieces.put(56 + 7*i, new Rook(c, 56 + 7*i, this, 1 - i));
        }
        pieces.put(3 + c, new Queen(1 - c, 3 + c, this));
        pieces.put(59 + c, new Queen(c, 59 + c, this));

        pieces.put(4 - c, new King(1 - c, 4 - c, this));
        pieces.put(60 - c, new King(c, 60 - c, this));
    }

    public Map<Integer, Piece> getPieces() {
        return pieces;
    }

    public static boolean onBoard(int[] loc) {
        return (loc[0] >= 0 && loc[0] < 8 && loc[1] >= 0 && loc[1] < 8);
    }

    public static int convertTo1D(int[] loc) {
        return 8 * loc[0] + loc[1];
    }

    public static int[] convertTo2D(int loc) {
        return new int[] {loc / 8, loc % 8};
    }
}
