public class King extends ChessPiece {
    public King(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "bk.png";
        }
        return IMAGE_LOCATION + "wk.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
