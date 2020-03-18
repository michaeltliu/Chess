import java.util.Set;

public class Queen extends Piece {
    private int value;
    public Queen(int color, int loc, Board board) {
        super(color, loc, board);
        value = 9;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo() {

    }
}
