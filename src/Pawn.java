import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(int color, int loc, Board board) {
        super(color, loc, board);
        val = 1;
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
        ret.addAll(canCapture());
        removeIllegalMoves(ret);
        return ret;
    }

    @Override
    public void moveTo(int dest) {
        Set<Integer> enPassant = canEnPassant();
        if (enPassant.contains(dest)) {
            pieces.remove(loc);
            board.setLastMove(new int[] {loc, dest});
            loc = dest;
            pieces.put(loc, this);

            int c = (int) Math.pow(-1, Math.abs(color - board.c));
            pieces.remove(loc + c*8);
        }
        else {
            super.moveTo(dest);
        }
        if (!hasMoved) hasMoved = true;
    }

    public Set<Integer> canCapture() {
        Set<Integer> ret = new HashSet<>();

        // c is 1 if it is the user's pieces and -1 if it is the computer's
        int c = (int) Math.pow(-1, Math.abs(color - board.c));

        if (loc % 8 != (3.5 - c*3.5) && pieces.containsKey(loc - c*9) && pieces.get(loc - c*9).color != this.color){
            ret.add(loc - c*9);
        }
        if (loc % 8 != (3.5 + c*3.5) && pieces.containsKey(loc - c*7) && pieces.get(loc - c*7).color != this.color)
            ret.add(loc - c*7);

        ret.addAll(canEnPassant());
        return ret;
    }

    private Set<Integer> canEnPassant() {
        Set<Integer> ret = new HashSet<>();

        int c = (int) Math.pow(-1, Math.abs(color - board.c));
        if (loc / 8 == 3.5 - 0.5*c) {
            if (loc % 8 != (3.5 - c*3.5) && pieces.containsKey(loc - c) && pieces.get(loc - c).color != this.color
                    && pieces.get(loc - c) instanceof Pawn && board.getLastMove()[0] == (loc - c) - c*16) {
                ret.add((loc - c) - c*8);
            }
            if (loc % 8 != (3.5 + c*3.5) && pieces.containsKey(loc + c) && pieces.get(loc + c).color != this.color
                    && pieces.get(loc + c) instanceof Pawn && board.getLastMove()[0] == (loc + c) - c*16) {
                ret.add((loc + c) - c*8);
            }
        }
        return ret;
    }

    @Override
    public Pawn clone(Board b) {
        Pawn newPawn = new Pawn(color, loc, b);
        newPawn.hasMoved = this.hasMoved;
        return newPawn;
    }
}
