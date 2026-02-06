public class Pawn extends ChessPiece {
    public Pawn(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        if (isBlack) {
            return IMAGE_LOCATION + "bp.png";
        }
        return IMAGE_LOCATION + "wp.png";
    }

    public boolean isEmpty() {
        return false;
    }
    public char getName(){
        return ' ';
    }
}
