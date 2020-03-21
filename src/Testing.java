public class Testing {
    public static void main(String[] args) {
        King king = new King(0, 48, new Board(0));
        System.out.println(king.canMoveTo());
    }
}
