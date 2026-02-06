public class Empty extends ChessPiece {
    public Empty(Game_Record game, Cordinate index, boolean isBlack) {
        super(game, index, isBlack);
    }

    public boolean moveable(Cordinate index) {
        return false;
    }

    public String getImage() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }
    public String getName(){
        return null;
    }
}
