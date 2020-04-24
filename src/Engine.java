import java.util.Map;
import java.util.Stack;

public class Engine {
    private Board board;
    private Map<Integer, Piece> pieces;
    private int color;

    public Engine(Board board, int color) {
        this.board = board;
        pieces = board.getPieces();
        this.color = color;
    }

    public int[] bestMove() {
        Stack<Board> stack = new Stack<>();
        stack.push(board);
        while (!stack.isEmpty()) {
            Board top = stack.pop().clone();
            
        }
        return null;
    }

    // Return value is positive if the engine's side is ahead in material.
    public int computeScoreAdvantage(Board board) {
        Map<Integer, Piece> m = board.getPieces();
        int score = 0;
        for (Integer i : m.keySet()) {
            Piece piece = m.get(i);
            if (piece.color == this.color) score += piece.val;
            else score -= piece.val;
        }
        return score;
    }
}
