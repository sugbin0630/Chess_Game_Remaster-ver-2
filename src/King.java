public class King extends ChessPiece {
    public King(Game_Record game, int index_X, int index_Y) {
        super(game, index_X, index_Y);
    }

    public boolean moveable(int index_X, int index_Y) {
        int deltaX = index_X - this.index_X;
        int deltaY = index_Y - this.index_Y;
        int value = checkIndex(index_X, index_Y);
        if (checkIndex(this.index_X, this.index_Y) > 0) {
            if (deltaX == 0 && deltaY == 0) {
                return false;
            }

            if (Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1 && (deltaX != 0 || deltaY != 0)) {
                if (value == 0 || value == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getImage() {
        if (game.boardData[this.index_Y][this.index_X] < 0) {
            return IMAGE_LOCATION + "bk.png";
        }
        return IMAGE_LOCATION + "wk.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
