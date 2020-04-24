import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Window implements MouseListener {
    public class Panel extends JPanel {
        private Map<String, BufferedImage> pieceImages;
        private final int WIDTH = Window.WIDTH;
        private final int HEIGHT = Window.HEIGHT;

        public Panel() throws IOException {
            pieceImages = new HashMap<>();
            String[] pieceTypes = new String[] {"pawn", "bishop", "knight", "rook", "queen", "king"};
            for (int i = 0; i < 2; i ++) {
                for (String s : pieceTypes) {
                    String pathname = "";
                    if (i == 0) pathname += "w";
                    else pathname += "b";

                    pathname += s;
                    BufferedImage image;
                    try {   // If machine is Windows
                        image = ImageIO.read(new File(".\\img\\" + pathname + ".png"));
                    } catch (IOException e) {   // If machine is Mac
                        image = ImageIO.read(new File("../img/" + pathname + ".png"));
                    }
                    BufferedImage resized = resizeImage(image, 65, 65);
                    pieceImages.put(pathname, resized);
                }
            }
        }

        public void paint(Graphics g) {
            super.paint(g);
            drawBoard(g);
            drawKingInCheck(g);
            if (selectedPiece >= 0)
                drawCanMoveTo(g);
            drawPieces(g);
        }

        public void drawBoard(Graphics g) {
            g.setColor(Color.BLACK);
            for (int i = 0; i <= 8; i ++) {
                g.drawLine(i * WIDTH/8, 0, i * WIDTH/8, HEIGHT);
                g.drawLine(0, i * HEIGHT/8, WIDTH, i * HEIGHT/8);
            }
        }

        private void drawPieces(Graphics g) {
            for (Piece piece : pieces.values()) {
                String pathname = "";
                int loc = piece.loc;
                int row = loc / 8;
                int col = loc % 8;

                if (piece.color == 0) pathname += "w";
                else pathname += "b";

                if (piece instanceof Pawn) pathname += "pawn";
                else if (piece instanceof Bishop) pathname += "bishop";
                else if (piece instanceof Knight) pathname += "knight";
                else if (piece instanceof Rook) pathname += "rook";
                else if (piece instanceof Queen) pathname += "queen";
                else pathname += "king";

                g.drawImage(pieceImages.get(pathname), col * WIDTH/8 + 5, row * HEIGHT/8 + 5, this);
            }

            int[] arr = board.getLastMove();
            int prev = arr[0];
            int post = arr[1];
            if (prev != post) {
                g.setColor(Color.BLACK);
                g.drawOval((prev % 8) * WIDTH / 8, (prev / 8) * HEIGHT / 8, WIDTH / 8, HEIGHT / 8);
                g.drawOval((post % 8) * WIDTH / 8, (post / 8) * HEIGHT / 8, WIDTH / 8, HEIGHT / 8);
            }
        }

        private void drawKingInCheck(Graphics g) {
            King king = board.getKing(board.getTurn());
            if (king.inCheck()) {
                g.setColor(Color.RED);
                int row = king.loc / 8;
                int col = king.loc % 8;
                g.fillRect(col * WIDTH/8 + 1, row * HEIGHT/8 + 1, 73, 73);
            }
        }

        private void drawCanMoveTo(Graphics g) {
            g.setColor(Color.GREEN);
            Piece piece = pieces.get(selectedPiece);
            Set<Integer> available = piece.canMoveTo();
            for (Integer i : available) {
                int row = i / 8;
                int col = i % 8;
                g.fillRect(col * WIDTH/8 + 1, row * HEIGHT/8 + 1, 73, 73);
            }
        }

        private BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            return dimg;
        }
    }

    private Panel panel;
    private Board board;
    private Map<Integer, Piece> pieces;
    private int selectedPiece;
    private final static int HEIGHT = 600;
    private final static int WIDTH = 600;

    public Window(Board board) throws IOException {
        this.board = board;
        pieces = board.getPieces();
        selectedPiece = -1;

        panel = new Panel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.addMouseListener(this);

        JFrame frame = new JFrame("Play Chess!");
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
            if (pieces.containsKey(mouseLoc) && pieces.get(mouseLoc).color == board.getTurn())
                selectedPiece = mouseLoc;
        }
        else {
            Piece p = pieces.get(selectedPiece);
            if (p.canMoveTo().contains(mouseLoc)) {
                p.moveTo(mouseLoc);
                selectedPiece = -1;
                board.nextTurn();
            }
            else if (selectedPiece == mouseLoc) selectedPiece = -1;
            else if (pieces.containsKey(mouseLoc) && pieces.get(mouseLoc).color == board.getTurn())
                selectedPiece = mouseLoc;
            else selectedPiece = -1;
        }

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
