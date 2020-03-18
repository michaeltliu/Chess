import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Set;

public class Window implements MouseListener {
    public class Panel extends JPanel {
        private final int WIDTH = Window.WIDTH;
        private final int HEIGHT = Window.HEIGHT;

        public void paint(Graphics g) {
            super.paint(g);
            drawBoard(g);
            drawPieces(g);
            if (selectedPiece >= 0)
                drawCanMoveTo(g);
        }

        public void drawBoard(Graphics g) {
            g.setColor(Color.BLACK);
            for (int i = 0; i <= 8; i ++) {
                g.drawLine(i * WIDTH/8, 0, i * WIDTH/8, HEIGHT);
                g.drawLine(0, i * HEIGHT/8, WIDTH, i * HEIGHT/8);
            }
        }

        private void drawPieces(Graphics g) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 18));
            for (Piece piece : pieces.values()) {
                int loc = piece.loc;
                if (user == 1) loc = 63 - loc;     // Flip the board if the user plays black
                int row = loc / 8;
                int col = loc % 8;

                if (piece.color == 0) g.setColor(Color.LIGHT_GRAY);
                else g.setColor(Color.BLACK);

                String pieceStr;
                if (piece instanceof Pawn) pieceStr = "P";
                else if (piece instanceof Bishop) pieceStr = "B";
                else if (piece instanceof Knight) pieceStr = "N";
                else if (piece instanceof Rook) pieceStr = "R";
                else if (piece instanceof Queen) pieceStr = "Q";
                else pieceStr = "K";

                g.drawString(pieceStr, col * WIDTH/8 + WIDTH/16 - 5,
                        row * HEIGHT/8 + HEIGHT/16 + 5);
            }
        }

        private void drawCanMoveTo(Graphics g) {
            g.setColor(Color.GREEN);
            Piece piece = pieces.get(selectedPiece);
            Set<Integer> available = piece.canMoveTo();
            for (Integer i : available) {
                int row = i / 8;
                int col = i % 8;
                g.drawOval(col * WIDTH/8 + WIDTH/16 - 15, row * HEIGHT/8 + HEIGHT/16 - 15,
                        30, 30);
            }
        }
    }

    private JFrame frame;
    private Panel panel;
    private Board board;
    private Map<Integer, Piece> pieces;
    private int user;
    private int selectedPiece;
    private final static int HEIGHT = 600;
    private final static int WIDTH = 600;

    public Window(Board board, int userColor) {
        this.board = board;
        pieces = board.getPieces();
        user = userColor;   // 0 if user is white, 1 if black
        selectedPiece = -1;

        panel = new Panel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.addMouseListener(this);

        frame = new JFrame("Play Chess!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / (HEIGHT/8);
        int c = e.getX() / (WIDTH/8);
        int mouseLoc = 8 * r + c;

        if (selectedPiece == -1) {
            if (pieces.containsKey(mouseLoc)) selectedPiece = mouseLoc;
        }
        else {
            Piece p = pieces.get(selectedPiece);
            if (p.canMoveTo().contains(mouseLoc)) {
                p.moveTo(mouseLoc);
                selectedPiece = -1;
            }
            else if (selectedPiece == mouseLoc) selectedPiece = -1;
            else if (pieces.containsKey(mouseLoc)) selectedPiece = mouseLoc;
            else selectedPiece = -1;
        }

        System.out.println(selectedPiece);
        panel.repaint();
    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }

}
