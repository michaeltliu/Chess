import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class Window implements MouseListener {
    public class Panel extends JPanel {
        private Set<Board.Piece> pieces;

        public Panel(Board board) {
            pieces = board.getPieces();
        }

        public void paint(Graphics g) {
            super.paint(g);
        }
    }

    private JFrame frame;
    private Panel panel;

    public Window(Board board) {
        frame = new JFrame("Play Chess!");
        panel = new Panel(board);

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(panel);
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
