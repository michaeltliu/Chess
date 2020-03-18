import java.util.HashSet;
import java.util.Map;
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
        if (color == 0) {
            if (loc >= 8 && !pieces.containsKey(loc - 8)) {
                ret.add(loc - 8);
                if (!hasMoved && !pieces.containsKey(loc - 16))
                    ret.add(loc - 16);
            }
            if (loc % 8 != 0 && pieces.containsKey(loc - 9) && pieces.get(loc - 9).color != this.color){
                ret.add(loc - 9);
                System.out.println("reached");
            }
            if (loc % 8 != 7 && pieces.containsKey(loc - 7) && pieces.get(loc - 7).color != this.color)
                ret.add(loc - 7);
        }
        return ret;
    }

    @Override
    public void moveTo(int dest) {
        if (canMoveTo().contains(dest)) {
            if (pieces.containsKey(dest) && pieces.get(dest).color != this.color)
                pieces.remove(dest);
            pieces.remove(loc);
            loc = dest;
            pieces.put(loc, this);
        }
        if (!hasMoved) hasMoved = true;
    }
}
