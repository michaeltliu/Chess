import java.util.Set;

public class Rook extends Piece {
    private int value;
    private int side;
    private boolean hasMoved;

    public Rook(int color, int loc, Board board, int side) {
        super(color, loc, board);
        value = 5;
        this.side = side;   // 0 if king side, 1 if queen side
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
