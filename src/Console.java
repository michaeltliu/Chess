import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Enter 0 if you want to play white and 1 if you want to play black: ");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        Window window = new Window(board, x);
    }
}
