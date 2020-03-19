import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public final static int VALUE = 1;
    private boolean hasMoved;

    public Pawn(int color, int loc, Board board) {
        super(color, loc, board);
        hasMoved = false;
    }

    @Override
    public Set<Integer> canMoveTo() {
        Set<Integer> ret = new HashSet<>();
        int c = (int) Math.pow(-1, Math.abs(color - board.c));

        if (loc >= 8 && !pieces.containsKey(loc - c*8)) {
            ret.add(loc - c*8);
            if (!hasMoved && !pieces.containsKey(loc - c*16))
                ret.add(loc - c*16);
        }
        if (loc % 8 != 0 && pieces.containsKey(loc - c*9) && pieces.get(loc - c*9).color != this.color){
            ret.add(loc - c*9);
        }
        if (loc % 8 != 7 && pieces.containsKey(loc - c*7) && pieces.get(loc - c*7).color != this.color)
            ret.add(loc - c*7);
        return ret;
    }

    @Override
    public void moveTo(int dest) {
        super.moveTo(dest);
        if (!hasMoved) hasMoved = true;
    }
}
