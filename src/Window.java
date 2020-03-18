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
            System.out.println("reached");
            for (int i = 0; i <= 8; i ++) {
                g.drawLine(i * WIDTH/8, 0, i * WIDTH/8, HEIGHT);
                g.drawLine(0, i * HEIGHT/8, WIDTH, i * HEIGHT/8);
            }
        }

        private void drawPieces(Graphics g) {
            for (Piece piece : pieces) {
                int loc = piece.getLoc();
                int row = loc / 8;
                int col = loc % 8;
                if (piece instanceof Pawn) g.drawString("P", );
            }
        }
    }

    private JFrame frame;
    private Panel panel;
    private final static int HEIGHT = 600;
    private final static int WIDTH = 600;

    public Window(Board board) {
        panel = new Panel(board);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Play Chess!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
