public class Piece_Gruop {
    protected ChessPiece[][] pieces = new ChessPiece[8][8];
    private Cordinate index;
    public ChessPiece getPiece(Cordinate index){
        return pieces[index.getY()][index.getX()];
    }
}
