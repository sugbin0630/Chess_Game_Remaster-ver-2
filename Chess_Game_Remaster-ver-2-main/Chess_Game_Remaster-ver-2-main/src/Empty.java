public class Empty extends ChessPiece {
    public Empty(Game_Record game, Cordinate index) {
        super(game, index);
    }

    public boolean moveable(int index_X, int index_Y) {
        return false;
    }

    public String getImage() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }
}
