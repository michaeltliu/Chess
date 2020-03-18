import java.util.Set;

public class King extends Piece {
    public final static int VALUE = 99;
    public King(int color, int loc, Board board) {
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
