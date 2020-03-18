import java.util.Set;

public class Bishop extends Piece {
    public final static int VALUE = 3;
    public Bishop(int color, int loc, Board board) {
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
