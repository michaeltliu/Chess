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

    public void moveTo(int dest) {
        if (canMoveTo().contains(dest)) {
            if (pieces.containsKey(dest) && pieces.get(dest).color != this.color)
                pieces.remove(dest);
            pieces.remove(loc);
            loc = dest;
            pieces.put(loc, this);
        }
    }

    public boolean occupiedByTeam(int loc) {
        return pieces.containsKey(loc) && pieces.get(loc).color == this.color;
    }
}