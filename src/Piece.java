import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    int color;  // 0 for white; 1 for black
    int loc;
    Board board;
    Map<Integer, Piece> pieces;

    public Piece(int color, int loc, Board board) {
        this.color = color;
        this.loc = loc;
        this.board = board;
        pieces = board.getPieces();
    }

    public abstract Set<Integer> canMoveTo();

    public abstract void moveTo(int dest);
}