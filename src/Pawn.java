public class Pawn extends ChessPiece {
    public Pawn(Game_Record game, int index_X, int index_Y) {
        super(game, index_X, index_Y);
    }

    public boolean moveable(int index_X, int index_Y) {
        int deltaX = index_X - this.index_X;
        int deltaY = index_Y - this.index_Y;
        int value = checkIndex(index_X, index_Y);
        boolean isWhite = game.boardData[this.index_Y][this.index_X] > 0;

        if (checkIndex(this.index_X, this.index_Y) > 0) {
            if (deltaX == 0 && deltaY == 0) {
                return false;
            }

            if (value == 0) {
                if (deltaX == 0 && deltaY == -1 && isWhite) {
                    return true;
                }
                if (deltaX == 0 && deltaY == 1 && !isWhite) {
                    return true;
                }
                if (this.index_Y == 6 && isWhite) {
                    if (deltaX == 0 && deltaY == -1) {
                        return true;
                    }
                    if (checkIndex(this.index_X, this.index_Y - 1) == 0) {
                        if (deltaX == 0 && (deltaY == -2)) {
                            return true;
                        }
                    }
                }
                if (this.index_Y == 1 && !isWhite) {
                    if (deltaX == 0 && deltaY == 1) {
                        return true;
                    }
                    if (checkIndex(this.index_X, this.index_Y + 1) == 0) {
                        if (deltaX == 0 && deltaY == 2) {
                            return true;
                        }
                    }
                }
            }
            if (value == -1) {
                if (isWhite && (deltaX == 1 || deltaX == -1) && deltaY == -1) {
                    return true;
                }
                if (!isWhite && (deltaX == 1 || deltaX == -1) && deltaY == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getImage() {
        if (game.boardData[this.index_Y][this.index_X] < 0) {
            return IMAGE_LOCATION + "bp.png";
        }
        return IMAGE_LOCATION + "wp.png";
    }

    public boolean isEmpty() {
        return false;
    }
}
