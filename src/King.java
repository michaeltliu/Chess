import java.util.*;

public class King extends Piece {
    private int val;
    public King(int color, int loc, Board board) {
        super(color, loc, board);
        val = 100;
    }

    @Override
    public Set<Integer> canMoveTo() {
        System.out.println("Reached");
        Set<Integer> ret = new HashSet<>();
        for (Integer i : range()) {
            checkSquare(ret, Board.convertTo2D(i));
        }
        System.out.println(ret);
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

    private void checkSquare(Set<Integer> ret, int[] destArr) {
        int destInt = Board.convertTo1D(destArr);
        if (Board.onBoard(destArr) && !occupiedByTeam(destInt)) {
            int saveLoc = loc;
            Piece savePiece = null;
            if (pieces.containsKey(destInt)) savePiece = pieces.get(destInt);
            pieces.remove(loc);
            loc = destInt;
            pieces.put(destInt, this);
            if (!inCheck()) ret.add(Board.convertTo1D(destArr));
            pieces.remove(destInt);
            loc = saveLoc;
            pieces.put(saveLoc, this);
            if (savePiece != null) pieces.put(destInt, savePiece);
        }
    }
}
