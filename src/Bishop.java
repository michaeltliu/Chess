import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    private int val;
    public Bishop(int color, int loc, Board board) {
        super(color, loc, board);
        val = 3;
    }

    @Override
    public Set<Integer> canMoveTo() {
        Set<Integer> ret = new HashSet<>();

        // northwest
        int curr = loc;
        while (curr >= 9 && curr % 8 > 0) {
            curr -= 9;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        // northeast
        curr = loc;
        while (curr >= 8 && curr % 8 < 7) {
            curr -= 7;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        // southwest
        curr = loc;
        while (curr <= 55 && curr % 8 > 0) {
            curr += 7;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }

        // southeast
        curr = loc;
        while (curr <= 54 && curr % 8 < 7) {
            curr += 9;
            if (!pieces.containsKey(curr)) ret.add(curr);
            else if (pieces.containsKey(curr) && pieces.get(curr).color != this.color) {
                ret.add(curr);
                break;
            }
            else break;
        }
        return ret;
    }
}
