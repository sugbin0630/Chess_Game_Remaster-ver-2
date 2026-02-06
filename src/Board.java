/*TODO
 * 체스기능
 * 인터페이스 기능
 * 체크메이트 기능 모듈화
 */

import java.awt.*;
import javax.swing.*;

public class Board extends JFrame {

    final static String FILE_NAME = "ChessGame_Records.txt";
    final static String FILE_LOCATION = System.getProperty("user.dir") + "/src/";

    private final Container c = getContentPane();
    private static RecordGroup recordGroup = RecordGroup.getRecordGroup();

    private static ChessSquare[][] squares = new ChessSquare[8][8];

    Board(Game_Record game) {
        c.setLayout(null);
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(45, 45, 45));
        game.setBoard();

        setInterface(game);
        setPanel(game);
        c.repaint();
        setVisible(true);
    }

    /**
     * 기물들의 이미지와 버튼을 담는 패널을 생성하는 메소드
     */
    public void setPanel(Game_Record game) {
        setTitle("Chess Game");
        setSize(600, 600);
        setLayout(null); // setBounds를 사용하므로 레이아웃은 null로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Cordinate index = new Cordinate(0, 0);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                index.setCordinate(x, y);

                //기물 선택용 JButton 생성
                JButton button = new JButton();
                //기물 이미지 표시
                button.setIcon(new ImageIcon(game.board_data.getPiece(index).getImage()));
                button.setDisabledIcon(new ImageIcon(game.board_data.getPiece(index).getImage()));
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setBorderPainted(false);
                if (game.board_data.getPiece(index).isTurn(index) == 1) {
                    button.setEnabled(true);
                }
                // 1. 패널 객체 생성 (공식 자동 적용)
                squares[y][x] = new ChessSquare(x, y);
                // 2. 패널에 버튼 추가
                squares[y][x].add(button, -1);
                // 3. 프레임에 추가
                c.add(squares[y][x], 0);
            }
        }

        setVisible(true);
    }

    /**
     * disply save, load, and record buttons on screen add save function to
     * saveButton add load function to loadButton add sort records function to
     * recordButton
     */
    void setInterface(Game_Record game) {

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton recordButton = new JButton("Records");

        saveButton.setBounds(80, 480, 120, 40);
        loadButton.setBounds(230, 480, 120, 40);
        recordButton.setBounds(380, 480, 120, 40);

        c.add(saveButton, 0);
        c.add(loadButton, 0);
        c.add(recordButton, 0);
        JLabel j = new JLabel();
        j.setBounds(0, 0, 100, 100);
        j.setIcon(null);
        c.add(j, 0);
        j.setEnabled(false);

        saveButton.addActionListener(event -> {
            if (recordGroup.saveGame(game, FILE_LOCATION + FILE_NAME)) {
                JOptionPane.showMessageDialog(this, "Saved successfully!\ncode: " + game.getCode(), "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save", "Message", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadButton.addActionListener(event -> {
            //패널 이용하기 뜯어고치기
            setLoadWindow();
            // JDialog loadWindow = new MyDialog(this);
            //window_bg.setVisible(true);
        });
    }

    /**
     * move piece and save it into boardData
     */
    void move(Game_Record game, Cordinate startPoint, Cordinate endPoint) {

    }

    /**
     * display chess pieces on screen
     *
     * @param game loaded Game_Record
     *//* 
    void setPieces(Game_Record game) {
        
        JButton pieces_Button[][] = new JButton[8][8];
        Cordinate index = new Cordinate(0, 0);
        boolean[] isSelected = new boolean[1];
        isSelected[0] = false;
        //나도 뭔지 몰라 나중에 지워
        int[] remove_index = new int[2];

        for (int index_Y = 0; index_Y < 8; index_Y++) {
            for (int index_X = 0; index_X < 8; index_X++) {
                final int Y = index_Y;
                final int X = index_X;

                pieces_Button[Y][X].addActionListener(event -> {
                    if (isSelected[0] == false) {
                        buttonAction(game, pieces_Button, Y, X, remove_index, isSelected);
                        c.repaint();
                    } else if (isSelected[0] == true) {
                        if (game.board_data.getPiece(new Cordinate(remove_index[0], remove_index[1])).moveable(new Cordinate(X, Y))) {
                            game.move(new Cordinate(remove_index[0], remove_index[1]), new Cordinate(X, Y));
                            pieces_icon[remove_index[1]][remove_index[0]]
                                    .setIcon(new ImageIcon(game.board_data.getPiece(new Cordinate(remove_index[0], remove_index[1])).getImage()));
                            pieces_icon[Y][X].setIcon(new ImageIcon(game.board_data.getPiece(new Cordinate(X, Y)).getImage()));
                        }
                        for (int i = 0; i < pieces_Button.length; i++) {
                            for (int j = 0; j < pieces_Button.length; j++) {
                                pieces_Button[j][i].setIcon(null);
                            }
                        }
                        isSelected[0] = false;
                        c.repaint();
                    }
                });
            }
        }

    }
     */
//TODO 이거 좀 고쳐봐
    /**
     * void buttonAction(Game_Record game, JButton[][] pieces_Button, int Y, int
     * X, int[] index, boolean[] isSelected) { isSelected[0] = true; for (int i
     * = 0; i < pieces_Button.length; i++) { for (int j = 0; j <
     * pieces_Button.length; j++) { Cordinate inde = new Cordinate(i, j);
     * pieces_Button[j][i].setIcon(null); pieces_Button[j][i].setEnabled(false);
     * if (game.board_data.getPiece(new Cordinate(i, j)).isTurn(inde) == 1) {
     * pieces_Button[j][i].setEnabled(true); }

     *      *}
     * }

     *      *pieces_Button[Y][X] .setIcon(new ImageIcon(FILE_LOCATION +
     * "images/moveable_pressed.png")); for (int i = 0; i <
     * pieces_Button.length; i++) { for (int j = 0; j < pieces_Button.length;
     * j++) { if (game.board_data.getPiece(new Cordinate(X, Y)).moveable(new
     * Cordinate(i, j))) { index[0] = X; index[1] = Y;
     * pieces_Button[j][i].setIcon(new ImageIcon(FILE_LOCATION +
     * "images/moveable.png")); pieces_Button[j][i].setEnabled(true); } } }
    }
     */

    void setLoadWindow() {
        JLabel window_bg = new JLabel();
        window_bg.setBounds(100, 100, 500, 400);
        window_bg.setBackground(new Color(255, 255, 255));
        window_bg.setVisible(true);
        window_bg.setOpaque(true);
        c.add(window_bg);
        c.repaint();
    }
    // class MyDialog extends JDialog {
    // JTextField textField = new JTextField(10);
    // JButton records[] = new JButton[recordGroup.getArrayList().size()];

    // public MyDialog(JFrame frame) {
    // setLayout(new FlowLayout());
    // add(textField);
    // for (int i = 0; i < records.length; i++) {
    // records[i] = new JButton(recordGroup.getArrayList().get(i).getPlayer1() + "
    // vs "
    // + recordGroup.getArrayList().get(i).getPlayer2() + " "
    // + recordGroup.getArrayList().get(i).getCode() + " "
    // + recordGroup.getArrayList().get(i).getDate());
    // records[i].setPreferredSize(new Dimension(400, 40));
    // int j = i;
    // records[i].addActionListener(event -> {
    // dispose();
    // new Board(recordGroup.getArrayList().get(j));
    // });
    // add(records[i]);
    // }
    // setSize(500, 400);
    // }
    // }
    public static void main(String[] args) {
        Game_Record newGame = new Game_Record("player1", "player2", 0, 1,
                recordGroup.getArrayList());

        recordGroup.fileLoad(FILE_LOCATION + FILE_NAME);

        /*String player1 = JOptionPane.showInputDialog("Enter player1 name");
        String player2 = JOptionPane.showInputDialog("Enter player2 name");
        if (player1 != null) {
            newGame.setPlayer1(player1);
        }
        if (player2 != null) {
            newGame.setPlayer2(player2);
        }*/
        newGame.setPlayer1(null);
        newGame.setPlayer2(null);
        new Board(newGame);
    }
}
