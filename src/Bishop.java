public class Bishop extends ChessPiece {
    public Bishop(Game_Record game, int index_X, int index_Y) {
        super(game, index_X, index_Y);
    }

    public boolean moveable(int index_X, int index_Y) {
        int deltaX = index_X - this.index_X;
        int deltaY = index_Y - this.index_Y;
        int value = checkIndex(index_X, index_Y);
        if (checkIndex(this.index_X, this.index_Y) != 1) {
            return false;
        }
        // left down
        if (deltaX < 0 && -deltaX == deltaY) {
            for (int i = 1; i < deltaY; i++) {
                if (checkIndex(this.index_X - i, this.index_Y + i) != 0) {
                    return false;
                }
            }
            if (value == 0 || value == -1) {
                return true;
            }
        }
        // left up
        if (deltaX < 0 && deltaX == deltaY) {
            for (int i = 1; i < -deltaY; i++) {
                if (checkIndex(this.index_X - i, this.index_Y - i) != 0) {
                    return false;
                }
            }
            if (value == 0 || value == -1) {
                return true;
            }
        }
        // right down
        if (deltaX > 0 && deltaX == deltaY) {
            for (int i = 1; i < deltaY; i++) {
                if (checkIndex(this.index_X + i, this.index_Y + i) != 0) {
                    return false;
                }
            }
            if (value == 0 || value == -1) {
                return true;
            }
        }
        // right up
        if (deltaX > 0 && deltaX == -deltaY) {
            for (int i = 1; i < -deltaY; i++) {
                if (checkIndex(this.index_X + i, this.index_Y - i) != 0) {
                    return false;
                }
            }
            if (value == 0 || value == -1) {
                return true;
            }
        }
        return false;
    }

    public String getImage() {
        if (game.boardData[this.index_Y][this.index_X] < 0) {
            return IMAGE_LOCATION + "bb.png";
        }
        return IMAGE_LOCATION + "wb.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
