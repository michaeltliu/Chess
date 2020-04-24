import java.io.IOException;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter 0 if you want to play white and 1 if you want to play black: ");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        Board board = new Board(x, true);
        Window window = new Window(board);
    }
}
