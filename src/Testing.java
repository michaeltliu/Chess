import java.util.Map;

public class Testing {
    public static void main(String[] args) {
        Board board = new Board(0, true);
        System.out.println(board.toString());
        Engine eng = new Engine(1, board);
        eng.makeGraphs();
    }
}
