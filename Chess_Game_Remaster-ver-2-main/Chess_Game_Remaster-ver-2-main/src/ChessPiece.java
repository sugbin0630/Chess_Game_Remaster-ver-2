public abstract class ChessPiece {
    protected Game_Record game;
    protected Cordinate index;
    protected String image;
    final protected String IMAGE_LOCATION = System.getProperty("user.dir") + "/src/images/";
    public boolean isBlack;

    public ChessPiece(Game_Record game, Cordinate index, boolean isBlack) {
        this.game = game;
        this.index.setCordinate(index.getX(), index.getY());
        this.isBlack = isBlack;
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

    /**
     * @return String Notation(SAN)
     * this method retunr String tpye of Notation that shows current movement of piece
     * start location -> piece name -> does catch -> end location -> speacial rules (castle -> promotion -> En passant) -> check, checkmate
     * 
     */
    //abstract public String returnNotation();
}
