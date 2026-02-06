public class Rook extends ChessPiece {
    public Rook(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "br.png";
        }
        return IMAGE_LOCATION + "wr.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
