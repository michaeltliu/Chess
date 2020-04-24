import java.util.Map;

public class Testing {
    public static void main(String[] args) {
        Board board = new Board(0, true);
        System.out.println(board.toString());
        Board board2 = board.clone();
        Map<Integer, Piece> pieces2 = board2.getPieces();
        pieces2.get(52).moveTo(36);
        System.out.println(board2.toString());
        System.out.println(board.toString());
        Map<Integer, Piece> pieces = board.getPieces();
        pieces.get(49).moveTo(33);
        System.out.println(board2.toString());
        System.out.println(board.toString());
    }
}
