import java.util.Set;

public class Knight extends Piece {
    private int value;
    public Knight(int color, int loc, Board board) {
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
