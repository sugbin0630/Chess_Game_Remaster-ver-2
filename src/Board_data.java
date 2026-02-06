
public class Board_data {

    private ChessPiece[][] board_data = new ChessPiece[8][8];

    /**
     * 체스 기본 보드 세팅 메소드
     * @param game
     */
    public void setBoard(Game_Record game) {
        // 0행: 주요 기물 (Black)
        board_data[0][0] = new Rook(game, new Cordinate(0, 0), true);
        board_data[0][1] = new Knight(game, new Cordinate(1, 0), true);
        board_data[0][2] = new Bishop(game, new Cordinate(2, 0), true);
        board_data[0][3] = new Queen(game, new Cordinate(3, 0), true);
        board_data[0][4] = new King(game, new Cordinate(4, 0), true);
        board_data[0][5] = new Bishop(game, new Cordinate(5, 0), true);
        board_data[0][6] = new Knight(game, new Cordinate(6, 0), true);
        board_data[0][7] = new Rook(game, new Cordinate(7, 0), true);

// 1행: 폰 (Black)
        board_data[1][0] = new Pawn(game, new Cordinate(0, 1), true);
        board_data[1][1] = new Pawn(game, new Cordinate(1, 1), true);
        board_data[1][2] = new Pawn(game, new Cordinate(2, 1), true);
        board_data[1][3] = new Pawn(game, new Cordinate(3, 1), true);
        board_data[1][4] = new Pawn(game, new Cordinate(4, 1), true);
        board_data[1][5] = new Pawn(game, new Cordinate(5, 1), true);
        board_data[1][6] = new Pawn(game, new Cordinate(6, 1), true);
        board_data[1][7] = new Pawn(game, new Cordinate(7, 1), true);
// 2행
        board_data[2][0] = new Empty(game, new Cordinate(0, 2), true);
        board_data[2][1] = new Empty(game, new Cordinate(1, 2), true);
        board_data[2][2] = new Empty(game, new Cordinate(2, 2), true);
        board_data[2][3] = new Empty(game, new Cordinate(3, 2), true);
        board_data[2][4] = new Empty(game, new Cordinate(4, 2), true);
        board_data[2][5] = new Empty(game, new Cordinate(5, 2), true);
        board_data[2][6] = new Empty(game, new Cordinate(6, 2), true);
        board_data[2][7] = new Empty(game, new Cordinate(7, 2), true);

// 3행
        board_data[3][0] = new Empty(game, new Cordinate(0, 3), true);
        board_data[3][1] = new Empty(game, new Cordinate(1, 3), true);
        board_data[3][2] = new Empty(game, new Cordinate(2, 3), true);
        board_data[3][3] = new Empty(game, new Cordinate(3, 3), true);
        board_data[3][4] = new Empty(game, new Cordinate(4, 3), true);
        board_data[3][5] = new Empty(game, new Cordinate(5, 3), true);
        board_data[3][6] = new Empty(game, new Cordinate(6, 3), true);
        board_data[3][7] = new Empty(game, new Cordinate(7, 3), true);

// 4행
        board_data[4][0] = new Empty(game, new Cordinate(0, 4), true);
        board_data[4][1] = new Empty(game, new Cordinate(1, 4), true);
        board_data[4][2] = new Empty(game, new Cordinate(2, 4), true);
        board_data[4][3] = new Empty(game, new Cordinate(3, 4), true);
        board_data[4][4] = new Empty(game, new Cordinate(4, 4), true);
        board_data[4][5] = new Empty(game, new Cordinate(5, 4), true);
        board_data[4][6] = new Empty(game, new Cordinate(6, 4), true);
        board_data[4][7] = new Empty(game, new Cordinate(7, 4), true);

// 5행
        board_data[5][0] = new Empty(game, new Cordinate(0, 5), true);
        board_data[5][1] = new Empty(game, new Cordinate(1, 5), true);
        board_data[5][2] = new Empty(game, new Cordinate(2, 5), true);
        board_data[5][3] = new Empty(game, new Cordinate(3, 5), true);
        board_data[5][4] = new Empty(game, new Cordinate(4, 5), true);
        board_data[5][5] = new Empty(game, new Cordinate(5, 5), true);
        board_data[5][6] = new Empty(game, new Cordinate(6, 5), true);
        board_data[5][7] = new Empty(game, new Cordinate(7, 5), true);
// 6행: 폰 (White)
        board_data[6][0] = new Pawn(game, new Cordinate(0, 6), false);
        board_data[6][1] = new Pawn(game, new Cordinate(1, 6), false);
        board_data[6][2] = new Pawn(game, new Cordinate(2, 6), false);
        board_data[6][3] = new Pawn(game, new Cordinate(3, 6), false);
        board_data[6][4] = new Pawn(game, new Cordinate(4, 6), false);
        board_data[6][5] = new Pawn(game, new Cordinate(5, 6), false);
        board_data[6][6] = new Pawn(game, new Cordinate(6, 6), false);
        board_data[6][7] = new Pawn(game, new Cordinate(7, 6), false);

// 7행: 주요 기물 (White)
        board_data[7][0] = new Rook(game, new Cordinate(0, 7), false);
        board_data[7][1] = new Knight(game, new Cordinate(1, 7), false);
        board_data[7][2] = new Bishop(game, new Cordinate(2, 7), false);
        board_data[7][3] = new Queen(game, new Cordinate(3, 7), false);
        board_data[7][4] = new King(game, new Cordinate(4, 7), false);
        board_data[7][5] = new Bishop(game, new Cordinate(5, 7), false);
        board_data[7][6] = new Knight(game, new Cordinate(6, 7), false);
        board_data[7][7] = new Rook(game, new Cordinate(7, 7), false);
    }

    public ChessPiece getPiece(Cordinate index) {
        return board_data[index.getY()][index.getX()];
    }

    public void setPiece(Cordinate index, ChessPiece piece) {
        board_data[index.getY()][index.getX()] = piece;
    }
}
