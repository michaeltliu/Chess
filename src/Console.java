import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        System.out.println("Enter 0 if you want to play white and 1 if you want to play black: ");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        Board board = new Board(x);
        Window window = new Window(board);
    }
}
