import java.util.Set;

public class Pawn extends Piece {
    private int value;
    public Pawn(int color, int loc, Board board) {
        super(color, loc, board);
        value = 1;
    }

    @Override
    public Set<Integer> canMoveTo() {

    }

    @Override
    public void moveTo() {

    }
}
