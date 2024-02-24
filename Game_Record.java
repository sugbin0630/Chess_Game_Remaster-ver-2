import java.text.SimpleDateFormat;
import java.util.*;

public class Game_Record {
    /** +: white, -: black */
    protected int turn;
    /** index X of user selection */
    protected int index_X;
    /** index Y of user selection */
    protected int index_Y;
    /** number of pieces were moved */
    private int numMoved;
    /** +: white, -: black, 0: not finished */
    private int win;
    /** name of player1 */
    private String player1;
    /** name of player2 */
    private String player2;
    /**
     * characteristic 4 digits code of game, use this when load game or delete game
     */
    private String code;
    /** Date when the game was created */
    private Date now = new Date();
    private String date;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 1: first index_X
     * 2: first index_Y
     * 3: second index_X
     * 4: second index_Y
     * movement.add(new Integer[]{})
     */
    ArrayList<Integer> movement = new ArrayList<Integer>();

    ChessPiece[][] pieces = new ChessPiece[8][8];

    /**
     * 0: empty
     * |1|: pwan
     * |2|: bishop
     * |3|: knight
     * |4|: rook
     * |5|: queen
     * |6|: king
     * +: white -: black
     */
    int boardData[][] = { { -4, -3, -2, -5, -6, -2, -3, -4 },
            { -1, -1, -1, -1, -1, -1, -1, -1 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 4, 3, 2, 5, 6, 2, 3, 4 } };

    /**
     * Constructor
     * 
     * @param player1  name
     * @param player2  name
     * @param numMoved
     * @param turn     +: white, -: black
     * @param records
     */
    public Game_Record(String player1, String player2, int numMoved, int turn, ArrayList<Game_Record> records) {
        this.player1 = player1;
        this.player2 = player2;
        this.numMoved = numMoved;
        this.code = codeGenerator(records);
        this.now = new Date();
        this.date = formatter.format(now);
        this.turn = turn;
        this.win = 0;
    }

    /**
     * set ChessPiece[] pieces
     * change integers of boardData[index_X][intdex_Y] and create new
     * Object(ChessPiece) at pieces
     */
    public void setPieces() {
        for (int index_X = 0; index_X < boardData.length; index_X++) {
            for (int index_Y = 0; index_Y < boardData[0].length; index_Y++) {
                switch (boardData[index_Y][index_X]) {
                    case 0:
                        pieces[index_Y][index_X] = new Empty(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 1:
                        pieces[index_Y][index_X] = new Pawn(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 2:
                        pieces[index_Y][index_X] = new Bishop(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 3:
                        pieces[index_Y][index_X] = new Knight(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 4:
                        pieces[index_Y][index_X] = new Rook(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 5:
                        pieces[index_Y][index_X] = new Queen(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case 6:
                        pieces[index_Y][index_X] = new King(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -1:
                        pieces[index_Y][index_X] = new Pawn(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -2:
                        pieces[index_Y][index_X] = new Bishop(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -3:
                        pieces[index_Y][index_X] = new Knight(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -4:
                        pieces[index_Y][index_X] = new Rook(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -5:
                        pieces[index_Y][index_X] = new Queen(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    case -6:
                        pieces[index_Y][index_X] = new King(this, index_X, index_Y);
                        pieces[index_Y][index_X].index_X = index_X;
                        pieces[index_Y][index_X].index_Y = index_Y;
                        break;
                    default:
                        break;
                }
            }
        }

    }

    /**
     * remove current game and load selected Game
     * 
     * @param record
     * @return true if load successfully, false if not
     */
    public boolean loadGame(Game_Record record) {
        if (record.getWin() == 0) {
            this.setWin(0);
            this.resetDate();
            this.setPlayer1(record.getPlayer1());
            this.setPlayer2(record.getPlayer2());
            this.setCode(record.getCode());
            this.setTurn(record.getTurn());
            this.boardData = record.boardData;
            this.movement = record.movement;
            resetDate();
            setPieces();
            return true;
        }
        return false;
    }

    /**
     * move the Chesspiece from index 1 to index 2
     * 
     * @param index_X1 index_X of piece location
     * @param index_Y1 index_Y of piece location
     * @param index_X2 index_X of the location that piece will move
     * @param index_Y2 index_Y of the location that piece will move
     * @return true if moved successfully, false if not
     */
    public void move(int index_X1, int index_Y1, int index_X2, int index_Y2) {
        int temp;
        temp = boardData[index_Y1][index_X1];
        this.boardData[index_Y1][index_X1] = 0;
        this.boardData[index_Y2][index_X2] = temp;
        this.numMoved++;
        this.movement.add(index_X1);
        this.movement.add(index_Y1);
        this.movement.add(index_X2);
        this.movement.add(index_Y2);
        this.setTurn(this.getTurn() * -1);
        setPieces();
    }

    /**
     * check winner of chessgame
     * check the survive of White king and Black king, and edit win value
     */
    public void winner() {
        boolean Wking_alive = false;
        boolean Bking_alive = false;

        for (int i = 0; i < boardData[0].length; i++) {
            for (int j = 0; j < boardData.length; j++) {
                if (boardData[i][j] == 6) {
                    Wking_alive = true;
                }
                if (boardData[i][j] == -6) {
                    Bking_alive = true;
                }
            }
        }
        if (Wking_alive && !Bking_alive) {
            this.win = 1;
        }
        if (!Wking_alive && Bking_alive) {
            this.win = -1;
        }
    }

    /**
     * reset Date of Game_Record to current date
     */
    public void resetDate() {
        this.date = formatter.format(new Date());
    }

    /**
     * Generate new no-overlapping Characteristic code
     * 
     * @param records
     * @return new code
     */
    public String codeGenerator(ArrayList<Game_Record> records) {
        String generatedString;
        boolean check;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        do {
            check = false;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            generatedString = buffer.toString();
            if (records != null) {
                for (int i = 0; i < records.size(); i++) {
                    if (records.get(i).code.equals(generatedString)) {
                        check = true;
                    }
                }
            }
        } while (check);
        return generatedString;
    }

    /**
     * @return int win of selected Game_Record
     */
    public int getWin() {
        return this.win;
    }

    /**
     * @return int numMoved of selected Game_Record
     */
    public int getNumMoved() {
        return this.numMoved;
    }

    /**
     * @return String player1 of selected Game_Record
     */
    public String getPlayer1() {
        return this.player1;
    }

    /**
     * @return String player2 of selected Game_Record
     */
    public String getPlayer2() {
        return this.player2;
    }

    /**
     * @return 4 digits code of selected Game_Record
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return date of selected Game_Record
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @return turn (+: white, -: black)
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * @param win change win value to given integer
     */
    public void setWin(int win) {
        this.win = win;
    }

    /**
     * @param int numMoved of selected Game_Record
     */
    public void setNumMoved(int numMoved) {
        this.numMoved = numMoved;
    }

    /**
     * @param String player1 of selected Game_Record
     */
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    /**
     * @param String player2 of selected Game_Record
     */
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    /**
     * @param String 4 digits code of selected Game_Record
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param date of selected Game_Record
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param turn +: white, -: black
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }
}