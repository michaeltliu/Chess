import java.util.Set;

public class Pawn extends Piece {
    private int value;
    private boolean hasMoved;

    public Pawn(int color, int loc, Board board) {
        super(color, loc, board);
        value = 1;
        hasMoved = false;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo() {
        if (!hasMoved) hasMoved = true;

    }
}
