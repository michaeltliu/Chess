import java.util.*;

public class King extends Piece {
    private boolean hasMoved;
    public King(int color, int loc, Board board) {
        super(color, loc, board);
        val = 100;
        hasMoved = false;
    }

    @Override
    public Set<Integer> canMoveTo() {
        Set<Integer> ret = new HashSet<>();

        for (Integer i : range()) {
            validateSquare(ret, Board.convertTo2D(i));
        }
        ret.addAll(canCastle().keySet());
        return ret;
    }

    public Map<Integer, Rook> canCastle() {
        Map<Integer, Rook> ret = new HashMap<>();
        if (!hasMoved && !inCheck()) {
            int c = (int) (Math.pow(-1, board.c));

            boolean kingSideClear = true;
            if (pieces.containsKey(loc + c) || pieces.containsKey(loc + 2*c) ||
                    !isSafeSquare(Board.convertTo2D(loc + c)) || !isSafeSquare(Board.convertTo2D(loc + 2*c)))
                kingSideClear = false;

            boolean queenSideClear = true;
            if (pieces.containsKey(loc - c) || pieces.containsKey(loc - 2*c) || pieces.containsKey(loc - 3*c)
                    || !isSafeSquare(Board.convertTo2D(loc - c)) || !isSafeSquare(Board.convertTo2D(loc - 2*c))
                    || !isSafeSquare(Board.convertTo2D(loc - 3*c)))
                queenSideClear = false;

            for (Piece p : pieces.values()) {
                if (p.color == this.color && p instanceof Rook && ((Rook) p).getHasMoved() == false) {
                    Rook rook = (Rook) p;
                    if (rook.getSide() == 0) {
                        if (kingSideClear)
                            ret.put(loc + 2 * c, rook);
                        else continue;
                    }
                    else if (rook.getSide() == 1) {
                        if (queenSideClear)
                            ret.put(loc - 2 * c, rook);
                        else continue;
                    }
                }
            }
        }
        return ret;
    }
    public boolean inCheck() {
        boolean safe = true;
        for (Piece p : pieces.values()) {
            if (p.color != this.color) {
                if ((p instanceof Pawn && ((Pawn) p).canCapture().contains(loc)) ||
                        (p instanceof King && ((King) p).range().contains(loc)) ||
                        (!(p instanceof Pawn) && !(p instanceof King) && p.canMoveTo().contains(loc))) {
                    System.out.println("Unsafe square: " + loc);
                    System.out.println(p.toString());
                    safe = false;
                    break;
                }
            }
        }
        return !safe;
    }

    public Set<Integer> range() {
        Set<Integer> ret = new HashSet<>();
        int[] locArray = Board.convertTo2D(loc);

        // lmfao who needs modularization when you have ~ copy/paste ~
        int[] copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 1; copy[1] -= 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[1] -= 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 1; copy[1] -= 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] -= 1; copy[1] += 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[1] += 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        copy = Arrays.copyOf(locArray, locArray.length);
        copy[0] += 1; copy[1] += 1;
        if (Board.onBoard(copy)) ret.add(Board.convertTo1D(copy));

        return ret;
    }

    private void validateSquare(Set<Integer> ret, int[] destArr) {
        int destInt = Board.convertTo1D(destArr);
        if (Board.onBoard(destArr) && !occupiedByTeam(destInt)) {
            if (isSafeSquare(destArr)) ret.add(Board.convertTo1D(destArr));
        }
    }

    private boolean isSafeSquare(int[] destArr) {
        boolean ret;
        int destInt = Board.convertTo1D(destArr);

        // Saving the state of things because we will be moving things around
        int saveLoc = loc;
        Piece savePiece = null;
        if (pieces.containsKey(destInt)) savePiece = pieces.get(destInt);

        pieces.remove(loc);
        loc = destInt;
        pieces.put(destInt, this);
        if (!inCheck()) ret = true;
        else ret = false;

        // Putting things back in place
        pieces.remove(destInt);
        loc = saveLoc;
        pieces.put(saveLoc, this);
        if (savePiece != null) pieces.put(destInt, savePiece);

        return ret;
    }

    @Override
    public void moveTo(int dest) {
        Map<Integer, Rook> map = canCastle();
        if (map.keySet().contains(dest)) {
            pieces.remove(loc);
            board.setLastMove(new int[] {loc, dest});
            loc = dest;
            pieces.put(loc, this);

            Rook rook = map.get(dest);
            if (rook.getSide() == 0) {
                rook.castle(this.loc - 1);
            }
            else if (rook.getSide() == 1) {
                rook.castle(this.loc + 1);
            }
        }
        else {
            super.moveTo(dest);
        }

        if (!hasMoved) hasMoved = true;
    }

    @Override
    public King clone(Board b) {
        King newKing = new King(color, loc, b);
        newKing.hasMoved = this.hasMoved;
        return newKing;
    }
}
