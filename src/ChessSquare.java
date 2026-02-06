import java.awt.*;
import javax.swing.*;

public class ChessSquare extends JPanel {
    private int ix; // index_X (0~7)
    private int iy; // index_Y (0~7)

    public ChessSquare(int index_X, int index_Y) {
        this.ix = index_X;
        this.iy = index_Y;

        // 레이아웃 및 배경 설정
        setLayout(new BorderLayout()); // 기물(JLabel)을 가운데 담기 위함
        
        // 체스판 특유의 격자무늬 배경색 설정
        if ((index_X + index_Y) % 2 == 0) {
            setBackground(new Color(235, 235, 208)); // 밝은 칸
        } else {
            setBackground(new Color(119, 149, 86));  // 어두운 칸
        }

        setBounds(92 + ix * 50, 45 + iy * 50, 50, 50);
    }
}