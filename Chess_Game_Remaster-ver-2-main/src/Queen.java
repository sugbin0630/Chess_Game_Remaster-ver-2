public class Queen extends ChessPiece {
    public Queen(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "bq.png";
        }
        return IMAGE_LOCATION + "wq.png";
    }

    public boolean isEmpty() {
        return false;
    }
    public String getName(){
        return "Q";
    }
}
