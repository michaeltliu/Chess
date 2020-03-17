import java.util.Set;

public class Board {
    public class Piece {
        private int color;  // 0 for white; 1 for black
        private int type;   // pawn 0; bishop 1; knight 2; rook 3; queen 4; king 5
        private int loc;

        public Piece(int color, int type) {
            this.color = color;
            this.type = type;
        }

        public Set<Integer> canMoveTo() {
            Set<Integer>
        }

        public void moveTo() {

        }

        public int getColor() {
            return color;
        }

        public int getType() {
            return type;
        }

        public int getLoc() {
            return loc;
        }

        public void setLoc(int loc) {
            this.loc = loc;
        }
    }

    private Set<Piece> pieces;

    public Board() {

    }

    public Set<Piece> getPieces() {
        return pieces;
    }
}
