import java.util.Set;

public class Bishop extends Piece {
    private int value;
    public Bishop(int color, int loc, Board board) {
        super(color, loc, board);
        value = 3;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo() {

    }
}
