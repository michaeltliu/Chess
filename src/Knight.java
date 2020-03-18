import java.util.Set;

public class Knight extends Piece {
    public final static int VALUE = 3;
    public Knight(int color, int loc, Board board) {
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
