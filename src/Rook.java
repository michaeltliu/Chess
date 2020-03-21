import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    private int val;
    private int side;
    private boolean hasMoved;

    public Rook(int color, int loc, Board board, int side) {
        super(color, loc, board);
        val = 5;
        this.side = side;   // 0 if king side, 1 if queen side
        hasMoved = false;
    }

    @Override
    public Set<Integer> canMoveTo() {
        Set<Integer> ret = new HashSet<>();

        int curr = loc;
        while (curr >= 8) {
            curr -= 8;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        curr = loc;
        while (curr <= 55) {
            curr += 8;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        curr = loc;
        while (curr % 8 > 0) {
            curr --;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        curr = loc;
        while (curr % 8 < 7) {
            curr ++;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }
        return ret;
    }

    @Override
    public void moveTo(int dest) {
        super.moveTo(dest);
        if (!hasMoved) hasMoved = true;
    }
}
