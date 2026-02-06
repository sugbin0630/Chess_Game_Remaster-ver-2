public abstract class ChessPiece {
    protected Game_Record game;
    protected Cordinate index;
    protected String image;
    final protected String IMAGE_LOCATION = System.getProperty("user.dir") + "/src/images/";
    public boolean isBlack;

    public ChessPiece(Game_Record game, Cordinate index, boolean isBlack) {
        this.game = game;
        this.index = index;
        this.isBlack = isBlack;
    }

    /**
     * Check required index is right index to move
     * 
     * @param index
     * @return empty: 0, team: 1,enemy: -1, Error: -2
     */
    public int isTurn(Cordinate index) {
        if (game.board_data.getPiece(index).isEmpty()) {
            return 0;
        }
        if (game.getTurn() > 0) {
            if (!game.board_data.getPiece(index).isBlack) {
                return 1;
            }
            return -1;
        }
        if (game.getTurn() < 0) {
            if (game.board_data.getPiece(index).isBlack) {
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
    abstract public boolean moveable(Cordinate index);

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
