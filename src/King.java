import java.util.Set;

public class King extends Piece {
    private int value;
    public King(int color, int loc, Board board) {
        super(color, loc, board);
        value = 99;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo() {

    }
}
