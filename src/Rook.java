import java.util.Set;

public class Rook extends Piece {
    private int value;
    public Rook(int color, int loc, Board board) {
        super(color, loc, board);
        value = 5;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo() {

    }
}
