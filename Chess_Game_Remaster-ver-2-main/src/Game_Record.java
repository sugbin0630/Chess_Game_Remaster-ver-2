
import java.text.SimpleDateFormat;
import java.util.*;

public class Game_Record {

    /**
     * +: white, -: black
     */
    protected int turn;
    /**
     * index X of user selection
     */
    protected int index_X;
    /**
     * index Y of user selection
     */
    protected int index_Y;
    /**
     * number of pieces were moved
     */
    private int numMoved;
    /**
     * +: white, -: black, 0: not finished
     */
    private int win;
    /**
     * name of player1
     */
    private String player1;
    /**
     * name of player2
     */
    private String player2;
    /**
     * characteristic 4 digits code of game, use this when load game or delete
     * game
     */
    private String code;
    /**
     * Date when the game was created
     */
    private Date now = new Date();
    private String date;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Board_data board_data = new Board_data();

    /**
     * 체스 기본 보드 세팅 메소드
     */
    public void setBoard() {
        board_data.setBoard(this);
    }

    /**
     * Constructor
     *
     * @param player1 name
     * @param player2 name
     * @param numMoved
     * @param turn +: white, -: black
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
            this.board_data = record.board_data;
            resetDate();
            setBoard();
            return true;
        }
        return false;
    }

    /**
     * move the Chesspiece from index 1 to index 2
     *
     * @param start_index Start index of piece that will move
     * @param end_index index of the location that piece will move
     * @return true if moved successfully, false if not
     */
    public void move(Cordinate start_index, Cordinate end_index) {
        // 이동 관련 (Move Actions)
        boolean isCapture = false;        // 기물을 잡았는가 ('x')
        boolean isPromotion = false;      // 프로모션(폰 승급)이 발생했는가 ('=')
        boolean isCheck = false;          // 체크 상태인가 ('+')
        boolean isCheckmate = false;      // 체크메이트인가 ('#')

        // 캐슬링 관련 (Castling)
        boolean isKingsideCastling = false;  // 킹사이드 캐슬링인가 ('0-0')
        boolean isQueensideCastling = false; // 퀸사이드 캐슬링인가 ('0-0-0')

        // 특수 규칙 (Special Rules)
        boolean isEnPassant = false;      // 앙파상으로 잡았는가
        boolean isAmbiguous = false;      // 도착지가 같아 출발지를 명시해야 하는가 (모호성 제거)
        ChessPiece temp;



        temp = board_data.getPiece(start_index);
        this.board_data.setPiece(start_index, new Empty(this, start_index, false));
        this.board_data.setPiece(end_index, temp);

        this.numMoved++;
        this.setTurn(this.getTurn() * -1);
        setBoard();
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
