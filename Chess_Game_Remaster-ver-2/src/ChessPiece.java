public abstract class ChessPiece {
    protected Game_Record game;
    protected int index_X;
    protected int index_Y;
    protected String image;
    final protected String IMAGE_LOCATION = "C:/Users/Sungbin Ko/Desktop/coding/Java/Chess_Game_Remaster/src/images/";

    public ChessPiece(Game_Record game, int index_X, int index_Y) {
        this.game = game;
        this.index_X = index_X;
        this.index_Y = index_Y;
    }

    /**
     * Check required index and return integer(state of the index)
     * 
     * @param index_X
     * @param index_Y
     * @return empty: 0, team: 1,enemy: -1, out of range: 2, Error: -2
     */
    public int checkIndex(int index_X, int index_Y) {
        int value = game.boardData[index_Y][index_X];

        if (value == 0) {
            return 0;
        }
        if (index_X < 0 || index_X > 7 || index_Y < 0 || index_Y > 7) {
            return 2;
        }
        if (game.getTurn() > 0) {
            if (value > 0) {
                return 1;
            }
            return -1;
        }
        if (game.getTurn() < 0) {
            if (value > 0) {
                return -1;
            }
            return 1;
        }
        return -2;
    }

    /**
     * 
     * @param index_X
     * @param index_Y
     * @return
     */
    abstract public boolean moveable(int index_X, int index_Y);

    /**
     * 
     * @return
     */
    abstract public String getImage();

    abstract public boolean isEmpty();
}

class King extends ChessPiece {
    King(Game_Record game, int index_X, int index_Y) {
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

class Queen extends ChessPiece {
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

class Bishop extends ChessPiece {
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

class Knight extends ChessPiece {
    public Knight(Game_Record game, int index_X, int index_Y) {
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

            if ((Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1) ||
                    (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2)) {
                if (value == 0 || value == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getImage() {
        if (game.boardData[this.index_Y][this.index_X] < 0) {
            return IMAGE_LOCATION + "bn.png";
        }
        return IMAGE_LOCATION + "wn.png";
    }

    public boolean isEmpty() {
        return false;
    }
}

class Rook extends ChessPiece {
    public Rook(Game_Record game, int index_X, int index_Y) {
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
            return IMAGE_LOCATION + "br.png";
        }
        return IMAGE_LOCATION + "wr.png";
    }

    public boolean isEmpty() {
        return false;
    }
}

class Pawn extends ChessPiece {
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

class Empty extends ChessPiece {
    public Empty(Game_Record game, int index_X, int index_Y) {
        super(game, index_X, index_Y);
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