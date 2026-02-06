public class Knight extends ChessPiece {
    public Knight(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "bn.png";
        }
        return IMAGE_LOCATION + "wn.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
