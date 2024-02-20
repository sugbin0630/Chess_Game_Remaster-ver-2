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
