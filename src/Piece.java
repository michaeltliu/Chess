import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    int color;  // 0 for white; 1 for black
    int loc;
    Board board;
    Map<Integer, Piece> pieces;
    int val;

    public Piece(int color, int loc, Board board) {
        this.color = color;
        this.loc = loc;
        this.board = board;
        pieces = board.getPieces();
    }

    public abstract Set<Integer> canMoveTo();

    public void moveTo(int dest) {
        if (canMoveTo().contains(dest)) {
            pieces.remove(loc);
            board.setLastMove(new int[] {loc, dest});
            loc = dest;
            pieces.put(loc, this);
        }
    }

    // For debugging purposes only. Bypasses canMoveTo()
    public void forceMoveTo(int dest) {
        pieces.remove(loc);
        board.setLastMove(new int[] {loc, dest});
        loc = dest;
        pieces.put(loc, this);
    }

    public boolean occupiedByTeam(int loc) {
        return pieces.containsKey(loc) && pieces.get(loc).color == this.color;
    }

    public boolean myKingInCheck() {
        King king = board.getKing(this.color);
        if (king.inCheck()) return true;
        return false;
    }

    public void removeIllegalMoves(Set<Integer> ret) {
        if (board.getTurn() == color) {
            Set<Integer> cpy = new HashSet<>(ret);
            for (Integer i : cpy) {
                int saveLoc = loc;
                Piece savePiece = null;
                if (pieces.containsKey(i)) savePiece = pieces.get(i);

                pieces.remove(loc);
                loc = i;
                pieces.put(i, this);
                if (myKingInCheck()) ret.remove(i);

                pieces.remove(i);
                loc = saveLoc;
                pieces.put(saveLoc, this);
                if (savePiece != null) pieces.put(i, savePiece);
            }
        }
    }

    public abstract Piece clone(Board b);

    // Mostly for debugging purposes
    @Override
    public String toString() {
        String ret = "";
        if (color == 0) ret += "White ";
        else ret += "Black ";

        if (this instanceof Pawn) ret += "pawn ";
        else if (this instanceof Bishop) ret += "bishop ";
        else if (this instanceof Knight) ret += "knight " ;
        else if (this instanceof Rook) ret += "rook ";
        else if (this instanceof Queen) ret += "queen ";
        else if (this instanceof King) ret += "king ";

        ret += "at " + loc;

        return ret;
    }
}