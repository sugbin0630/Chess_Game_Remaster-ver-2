public class Board_data {
    private Cordinate index;
    /**
     * null: empty
     * " " : pwan
     * "B": bishop
     * "N": knight
     * "R": rook
     * "Q": queen
     * "K": king
     * "!": black
     */
    private String board_data[][] = 
          { { "R!", "N!", "B!", "K!", "Q!", "B!", "N!", "R!" },
            { " !", " !", " !", " !", " !", " !", " !", " !" },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
            { "R ", "N ", "B ", "K ", "Q ", "B ", "N ", "R " } };
    
    public String getpiece(Cordinate index){
        return board_data[index.getY()][index.getX()];
    }
}
