import java.util.Map;

public class Engine {
    private Board board;
    private Map<Integer, Piece> pieces;
    private int color;

    public Engine(Board board, int color) {
        this.board = board;
        pieces = board.getPieces();
        this.color = color;
    }
}
