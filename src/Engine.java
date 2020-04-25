import java.util.*;

public class Engine {
    private int color;
    private Board board;
    private Map<Board, Set<Board>> children;
    private Map<Board, Board> parents;
    private Map<Board, Integer> distance;

    public Engine(int color, Board board) {
        this.color = color;
        this.board = board;
        children = new HashMap<>();
        parents = new HashMap<>();
        distance = new HashMap<>();
    }

    public void makeGraphs() {
        distance.put(board, 0);
        Queue<Board> q = new LinkedList<>();
        q.add(board);

        while(!q.isEmpty()) {
            Board top = q.remove();
            Map<Integer, Piece> pieces = top.getPieces();
            for (Integer i : pieces.keySet()) {
                if (pieces.get(i).color != board.getTurn()) continue;
                // calling .canMoveTo() modifies pieces which causes the concurrent modification exception
                for (Integer j : pieces.get(i).canMoveTo()) {
                    Board copy = top.clone();
                    copy.getPieces().get(i).moveTo(j);
                    if (distance.get(top) < 2) {
                        children.putIfAbsent(top, new HashSet<>());
                        children.get(top).add(copy);
                        parents.put(copy, top);
                        distance.put(copy, distance.get(top) + 1);
                    }
                }
            }
            board.nextTurn();
        }
        System.out.println(children);
        System.out.println(parents);
        System.out.println(distance);
    }

    public int[] bestMove(Board board) {
        return null;
    }

    // Return value is positive if the engine is ahead in material.
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
