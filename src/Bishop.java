public class Bishop extends ChessPiece {
    public Bishop(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "bb.png";
        }
        return IMAGE_LOCATION + "wb.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
