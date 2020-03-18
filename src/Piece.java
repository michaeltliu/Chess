import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    private int color;  // 0 for white; 1 for black
    private int row;
    private int col;
    private int value;
    private Board board;

    public Piece(int color, int loc, Board board) {
        this.color = color;
        row = loc / 8;
        col = loc % 8;
        this.board = board;
    }

    public abstract Set<Integer> canMoveTo();

    public abstract void moveTo();

    public int getColor() {
        return color;
    }

    public int getLoc() {
        return 8 * row + col;
    }

    public void setLoc(int loc) {
        row = loc / 8;
        col = loc % 8;
    }
}
