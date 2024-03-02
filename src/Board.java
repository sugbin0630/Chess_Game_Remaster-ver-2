/*TODO
 * 모듈화 작업
 * 체스 기능 밑 체스 디스플레이 기능
 * 화면 인터페이스 밑 저장기능
 * 각각 다른 클래스로 분리후 클린코드 작업
 * 
 * Board 클래스에서 각각의 기능이 구현된 Panel을 하니로 통합
 * 
 * 체크메이트 기능 구현
 * 기능구현을 위해 각 인덱스에 catchable variable 추가
 */

import java.awt.*;
import javax.swing.*;

public class Board extends JFrame {
    final static String FILE_NAME = "ChessGame_Records.txt";
    final static String FILE_LOCATION = "C:/Users/Sungbin Ko/Desktop/coding/Java/Chess_Game_Remaster/src/";
    private Container c = getContentPane();
    private static RecordGroup recordGroup = RecordGroup.getRecordGroup();
    static Game_Record newGame = new Game_Record("", "", 0, 1,
    recordGroup.getArrayList());
    static JPanel name_Input_Panel = new JPanel() {
    };
    static JPanel load_Window = new JPanel();

    Board(Game_Record game) {
        c.setLayout(null);
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        c.setBackground(new Color(45, 45, 45));
        name_Panel();
        setLoadWindow();
        setButton(game);
        setPieces(game);
        setBoard();
        setVisible(true);
    }

    /**
     * disply save, load, and record buttons on screen
     * add save function to saveButton
     * add load function to loadButton
     * add sort records function to recordButton
     */
    void setButton(Game_Record game) {

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton recordButton = new JButton("Records");

        saveButton.setBounds(80, 480, 120, 40);
        loadButton.setBounds(230, 480, 120, 40);
        recordButton.setBounds(380, 480, 120, 40);

        c.add(saveButton);
        c.add(loadButton);
        c.add(recordButton);
        JLabel j = new JLabel();
        j.setBounds(0, 0, 100, 100);
        j.setIcon(null);
        c.add(j);
        j.setEnabled(false);
        c.add(name_Input_Panel);

        saveButton.addActionListener(event -> {
            if (recordGroup.saveGame(game, FILE_LOCATION + FILE_NAME)) {
                JOptionPane.showMessageDialog(this, "Saved successfully!\ncode: " + game.getCode(), "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save", "Message", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadButton.addActionListener(event -> {
            // 패널 이용하기 뜯어고치기
            load_Window.setVisible(true);
            // JDialog loadWindow = new MyDialog(this);
            // window_bg.setVisible(true);
        });
    }

    /**
     * display chess pieces on screen
     * 
     * @param game loaded Game_Record
     */
    void setPieces(Game_Record game) {
        JLabel pieces_icon[][] = new JLabel[8][8];
        JButton pieces_Button[][] = new JButton[8][8];
        int[] index = new int[2];
        boolean[] isSelected = new boolean[1];
        isSelected[0] = false;

        game.setPieces();

        for (int index_Y = 0; index_Y < game.boardData[0].length; index_Y++) {
            for (int index_X = 0; index_X < game.boardData.length; index_X++) {
                pieces_icon[index_Y][index_X] = new JLabel();
                pieces_icon[index_Y][index_X].setIcon(new ImageIcon(game.pieces[index_Y][index_X].getImage()));
                pieces_icon[index_Y][index_X].setBounds(92 + index_X * 50, 45 + index_Y * 50, 50, 60);

                pieces_Button[index_Y][index_X] = new JButton();
                pieces_Button[index_Y][index_X].setIcon(null);
                pieces_Button[index_Y][index_X].setBounds(92 + index_X * 50, 45 + index_Y * 50, 50, 60);
                pieces_Button[index_Y][index_X].setContentAreaFilled(false);
                pieces_Button[index_Y][index_X].setOpaque(false);
                pieces_Button[index_Y][index_X].setBorderPainted(false);
                pieces_Button[index_Y][index_X].setEnabled(false);
                if (game.pieces[index_Y][index_X].checkIndex(index_X, index_Y) == 1) {
                    pieces_Button[index_Y][index_X].setEnabled(true);
                }

                c.add(pieces_icon[index_Y][index_X]);
                c.add(pieces_Button[index_Y][index_X]);
            }
        }

        for (int index_Y = 0; index_Y < game.boardData[0].length; index_Y++) {
            for (int index_X = 0; index_X < game.boardData.length; index_X++) {
                final int Y = index_Y;
                final int X = index_X;

                pieces_Button[Y][X].addActionListener(event -> {
                    if (isSelected[0] == false) {
                        buttonAction(game, pieces_Button, Y, X, index, isSelected);
                        c.repaint();
                    } else if (isSelected[0] == true) {
                        if (game.pieces[index[1]][index[0]].moveable(X, Y)) {
                            game.move(index[0], index[1], X, Y);
                            pieces_icon[index[1]][index[0]]
                                    .setIcon(new ImageIcon(game.pieces[index[1]][index[0]].getImage()));
                            pieces_icon[Y][X].setIcon(new ImageIcon(game.pieces[Y][X].getImage()));
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

    void buttonAction(Game_Record game, JButton[][] pieces_Button, int Y, int X, int[] index, boolean[] isSelected) {
        isSelected[0] = true;
        for (int i = 0; i < pieces_Button.length; i++) {
            for (int j = 0; j < pieces_Button.length; j++) {
                pieces_Button[j][i].setIcon(null);
                pieces_Button[j][i].setEnabled(false);
                if (game.pieces[j][i].checkIndex(i, j) == 1) {
                    pieces_Button[j][i].setEnabled(true);
                }

            }
        }

        pieces_Button[Y][X]
                .setIcon(new ImageIcon(FILE_LOCATION + "images/moveable_pressed.png"));
        for (int i = 0; i < pieces_Button.length; i++) {
            for (int j = 0; j < pieces_Button.length; j++) {
                if (game.pieces[Y][X].moveable(i, j)) {
                    index[0] = X;
                    index[1] = Y;
                    pieces_Button[j][i].setIcon(new ImageIcon(FILE_LOCATION + "images/moveable.png"));
                    pieces_Button[j][i].setEnabled(true);
                }
            }
        }
    }

    /**
     * display board on screen
     */
    void setBoard() {
        JLabel board = new JLabel();

        board.setIcon(new ImageIcon(FILE_LOCATION + "images/board.png"));
        board.setBounds(92, -50, 600, 600);

        c.add(board);
    }

    void setLoadWindow() {
        JButton records[] = new JButton[recordGroup.getArrayList().size()];
        TextField search_Bar = new TextField(10);
        JButton OK_Button = new JButton("OK");

        load_Window.setBackground(new Color(254, 254, 254));
        load_Window.setBounds(100, 10, 500, 400);
        load_Window.setLayout(new FlowLayout(FlowLayout.LEFT));

        load_Window.add(search_Bar);
        load_Window.add(OK_Button);

        for (int i = 0; i < records.length; i++) {
            records[i] = new JButton(recordGroup.getArrayList().get(i).getPlayer1() + " vs "
                    + recordGroup.getArrayList().get(i).getPlayer2() + " "
                    + recordGroup.getArrayList().get(i).getCode() + " "
                    + recordGroup.getArrayList().get(i).getDate());
            // records[i].setPreferredSize(new Dimension(400, 40));
            records[i].setBounds(0, 50 + i * 50, 400, 40);

            // int j = i;
            // records[i].addActionListener(event -> {
            // dispose();
            // new Board(recordGroup.getArrayList().get(j));
            // });
            load_Window.add(records[i]);
        }

        load_Window.setVisible(false);
        c.add(load_Window);
    }

    // setSize(500, 400);
    // }
    // }
    void name_Panel() {
        TextField player1_Name = new TextField(10);
        TextField player2_Name = new TextField(10);
        JButton OK_button = new JButton("OK");
        OK_button.addActionListener(event -> {
            name_Input_Panel.setEnabled(false);
            name_Input_Panel.setVisible(false);

            if (player1_Name.getText() !=
             "") {
                newGame.setPlayer1(player1_Name.getText());
            } else {
                newGame.setPlayer1("player1");
            }
            if (player1_Name.getText() != "") {
                newGame.setPlayer2(player2_Name.getText());
            } else {
                newGame.setPlayer2("player2");
            }
        });
        
        name_Input_Panel.setLayout(new BoxLayout(name_Input_Panel, BoxLayout.Y_AXIS));
        name_Input_Panel.setBackground(new Color(255, 255, 255));
        name_Input_Panel.setBounds(250, 0, 300, 300);
        name_Input_Panel.add(new JLabel("Enter player1 name"));
        name_Input_Panel.add(player1_Name);
        name_Input_Panel.add(new JLabel("Enter player2 name"));
        name_Input_Panel.add(player2_Name);

        name_Input_Panel.add(OK_button);

        c.add(name_Input_Panel);
    }
    
    public static void main(String[] args) {

        recordGroup.fileLoad(FILE_LOCATION + FILE_NAME);

        new Board(newGame);
    }    
}