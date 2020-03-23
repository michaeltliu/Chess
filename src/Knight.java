import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    private int val;
    public Knight(int color, int loc, Board board) {
        super(color, loc, board);
        val = 3;
    }

    @Override
    public Set<Integer> canMoveTo() {
        Set<Integer> ret = new HashSet<>();
        int[] locArray = Board.convertTo2D(loc);

        int[] copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 2; copy[1] -= 1;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 2; copy[1] += 1;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 1; copy[1] -= 2;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 1; copy[1] += 2;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 2; copy[1] -= 1;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 2; copy[1] += 1;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 1; copy[1] -= 2;
        checkSquare(ret, copy);

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 1; copy[1] += 2;
        checkSquare(ret, copy);

        removeIllegalMoves(ret);
        return ret;
    }

    private void checkSquare(Set<Integer> ret, int[] loc) {
        if (Board.onBoard(loc) && !occupiedByTeam(Board.convertTo1D(loc))) {
            ret.add(Board.convertTo1D(loc));
        }
    }
}
