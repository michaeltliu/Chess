import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Integer, Piece> pieces;
    private int turn;
    private int[] lastMove;
    int c;

    public Board(int userColor, boolean boardinit) {
        pieces = new HashMap<>();
        turn = 0;
        lastMove = new int[2];
        c = userColor;

        if (boardinit) initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 8; i ++) {
            pieces.put(8 + i, new Pawn(1 - c,8 + i, this));
            pieces.put(48 + i, new Pawn(c, 48 + i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(2 + 3*i, new Bishop(1 - c, 2 + 3*i, this));
            pieces.put(58 + 3*i, new Bishop(c, 58 + 3*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(1 + 5*i, new Knight(1 - c, 1 + 5*i, this));
            pieces.put(57 + 5*i, new Knight(c, 57 + 5*i, this));
        }
        for (int i = 0; i < 2; i ++) {
            pieces.put(7*i, new Rook(1 - c, 7*i, this, (1-c) + (int) Math.pow(-1, 1-c) * i));
            pieces.put(56 + 7*i, new Rook(c, 56 + 7*i, this, (1-c) + (int) Math.pow(-1, 1-c) * i));
        }
        pieces.put(3 + c, new Queen(1 - c, 3 + c, this));
        pieces.put(59 + c, new Queen(c, 59 + c, this));

        pieces.put(4 - c, new King(1 - c, 4 - c, this));
        pieces.put(60 - c, new King(c, 60 - c, this));
    }

    public Map<Integer, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Map<Integer, Piece> m) {
        this.pieces = new HashMap<>();
        for (Integer i : m.keySet()) {
            pieces.put(i, m.get(i).clone(this));
        }
    }

    public King getKing(int aColor) {
        if (!(aColor == 0 || aColor == 1)) {
            System.out.println("Invalid king color");
            return null;
        }
        for (Piece p : pieces.values()) {
            if (p.color == aColor && p instanceof King) {
                return (King) p;
            }
        }
        System.out.println("No king found!");
        return null;
    }

    public boolean aKingInCheck(int aColor) {
        King king = getKing(aColor);
        return king.inCheck();
    }

    public static boolean onBoard(int[] loc) {
        return (loc[0] >= 0 && loc[0] < 8 && loc[1] >= 0 && loc[1] < 8);
    }

    public static int convertTo1D(int[] loc) {
        return 8 * loc[0] + loc[1];
    }

    public static int[] convertTo2D(int loc) {
        return new int[] {loc / 8, loc % 8};
    }

    public void nextTurn() {
        turn ++;
        turn %= 2;
    }

    public int getTurn() {
        return turn;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int[] lastMove) {
        for (int i = 0; i < 2; i ++) {
            this.lastMove[i] = lastMove[i];
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                int loc = 8*i + j;
                if (!pieces.containsKey(loc)) {
                    str += "__ ";
                }
                else {
                    Piece piece = pieces.get(loc);

                    if (piece.color == 0) str += "w";
                    else str += "b";

                    if (piece instanceof Pawn) str += "P";
                    else if (piece instanceof Bishop) str += "B";
                    else if (piece instanceof Knight) str += "N";
                    else if (piece instanceof Rook) str += "R";
                    else if (piece instanceof Queen) str += "Q";
                    else str += "K";

                    str += " ";
                }
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public Board clone() {
        Board newBoard = new Board(c, false);
        newBoard.setPieces(pieces);
        if (newBoard.getTurn() != turn) {
            newBoard.nextTurn();
        }
        newBoard.setLastMove(lastMove);
        return newBoard;
    }
}
