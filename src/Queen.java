import java.util.Set;

public class Queen extends Piece {
    public final static int VALUE = 9;
    public Queen(int color, int loc, Board board) {
        super(color, loc, board);
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo(int dest) {

    }
}
