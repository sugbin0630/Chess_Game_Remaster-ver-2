public class Queen extends ChessPiece {
    public Queen(Game_Record game, int index_X, int index_Y) {
        super(game, index_X, index_Y);
    }

    public boolean moveable(int index_X, int index_Y) {
        int deltaX = index_X - this.index_X;
        int deltaY = index_Y - this.index_Y;
        int value = checkIndex(index_X, index_Y);

        if (checkIndex(this.index_X, this.index_Y) == 1) {
            if (deltaX == 0 && deltaY == 0) {
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

            // down
            if (deltaX == 0 && deltaY > 0) {
                for (int i = this.index_Y + 1; i < index_Y; i++) {
                    if (checkIndex(this.index_X, i) != 0) {
                        return false;
                    }
                }
                if (value == 0 || value == -1) {
                    return true;
                }
            }
            // up
            if (deltaX == 0 && deltaY < 0) {
                for (int i = this.index_Y - 1; i > index_Y; i--) {
                    if (checkIndex(this.index_X, i) != 0) {
                        return false;
                    }
                }
                if (value == 0 || value == -1) {
                    return true;
                }
            }
            // right
            if (deltaY == 0 && deltaX > 0) {
                for (int i = this.index_X + 1; i < index_X; i++) {
                    if (checkIndex(i, this.index_Y) != 0) {
                        return false;
                    }
                }
                if (value == 0 || value == -1) {
                    return true;
                }
            }
            // left
            if (deltaY == 0 && deltaX < 0) {
                for (int i = this.index_X - 1; i > index_X; i--) {
                    if (checkIndex(i, this.index_Y) != 0) {
                        return false;
                    }
                }
                if (value == 0 || value == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getImage() {
        if (game.boardData[this.index_Y][this.index_X] < 0) {
            return IMAGE_LOCATION + "bq.png";
        }
        return IMAGE_LOCATION + "wq.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
