import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class Window implements MouseListener {
    public class Panel extends JPanel {
        private Set<Piece> pieces;
        private final int WIDTH = Window.WIDTH;
        private final int HEIGHT = Window.HEIGHT;

        public Panel(Board board) {
            pieces = board.getPieces();
        }

        public void paint(Graphics g) {
            super.paint(g);
            drawBoard(g);
            drawPieces(g);
        }

        public void drawBoard(Graphics g) {
            for (int i = 0; i <= 8; i ++) {
                g.drawLine(i * WIDTH/8, 0, i * WIDTH/8, HEIGHT);
                g.drawLine(0, i * HEIGHT/8, WIDTH, i * HEIGHT/8);
            }
        }

        private void drawPieces(Graphics g) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 18));
            for (Piece piece : pieces) {
                int loc = piece.getLoc();
                if (getUser() == 1) loc = 63 - loc;     // Flip the board if the user plays black
                int row = loc / 8;
                int col = loc % 8;

                if (piece.getColor() == 0) g.setColor(Color.LIGHT_GRAY);
                else g.setColor(Color.BLACK);

                String pieceStr;
                if (piece instanceof Pawn) pieceStr = "P";
                else if (piece instanceof Bishop) pieceStr = "B";
                else if (piece instanceof Knight) pieceStr = "N";
                else if (piece instanceof Rook) pieceStr = "R";
                else if (piece instanceof Queen) pieceStr = "Q";
                else pieceStr = "K";

                g.drawString(pieceStr, col * WIDTH/8 + WIDTH/16 - 5,
                        (7 - row) * HEIGHT/8 + HEIGHT/16 + 5);
            }
        }
    }

    private JFrame frame;
    private Panel panel;
    private int user;
    private final static int HEIGHT = 600;
    private final static int WIDTH = 600;

    public Window(Board board, int userColor) {
        user = userColor;   // 0 if user is white, 1 if black

        panel = new Panel(board);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Play Chess!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X: " + e.getX() + " Y: " + e.getY());
    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }

    public int getUser() {
        return user;
    }
}
