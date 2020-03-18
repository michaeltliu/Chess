import java.util.Set;

public class Rook extends Piece {
    public final static int VALUE = 5;
    private int side;
    private boolean hasMoved;

    public Rook(int color, int loc, Board board, int side) {
        super(color, loc, board);
        this.side = side;   // 0 if king side, 1 if queen side
        hasMoved = false;
    }

    @Override
    public Set<Integer> canMoveTo() {
        return null;
    }

    @Override
    public void moveTo(int dest) {
        if (!hasMoved) hasMoved = true;
    }
}
