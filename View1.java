package TypingGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.io.File;


// 待機画面
class StayView extends JFrame {
    private JButton startButton;
    private RoundedButton levelEasyButton;
    private RoundedButton levelMediumButton;
    private RoundedButton levelHardButton;
    private RoundedButton selectedButton;

    public StayView() {
        setTitle("TAIPUEC");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 背景パネル
        ResultBackgroundPanel backgroundPanel = new ResultBackgroundPanel("view13_bg.png");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // レイアウトマネージャを無効化

        // ゲームタイトル
        try {
            // カスタムフォントの読み込み
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("play_pretend/Play Pretend.otf")).deriveFont(96f); // サイズを大きく
        
            // "TAIP" ラベル
            JLabel titleLabelTAIP = new JLabel("TAIP          ", SwingConstants.CENTER);
            titleLabelTAIP.setFont(customFont); // カスタムフォントを適用
            titleLabelTAIP.setForeground(Color.BLACK); // 黒色
            titleLabelTAIP.setBounds(0, 80, 1000, 120); // 位置とサイズを調整
            backgroundPanel.add(titleLabelTAIP);
        
            // "UEC" ラベル
            JLabel titleLabelUEC = new JLabel("         UEC", SwingConstants.CENTER);
            titleLabelUEC.setFont(customFont); // 同じフォントを適用
            titleLabelUEC.setForeground(new Color(20, 40, 139)); // 青色
            titleLabelUEC.setBounds(0, 80, 1000, 120); // "UEC" の位置を調整
            backgroundPanel.add(titleLabelUEC);
        
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("フォントの読み込みに失敗しました");
        }
        
        

        // 案内ラベル
        JLabel levelLabel = new JLabel(
            "<html><div style='text-align: center; font-size: 16px; color: black; font-weight: bold;'>ステージを選択してください</div></html>",
            SwingConstants.CENTER
        );
        levelLabel.setBounds(0, 225, 1000, 50);
        backgroundPanel.add(levelLabel);

        // レベル選択ボタン
        levelEasyButton = createLevelButton("基礎科学実験");
        levelEasyButton.setBounds(160, 300, 200, 60);
        backgroundPanel.add(levelEasyButton);

        levelMediumButton = createLevelButton("線形代数");
        levelMediumButton.setBounds(400, 300, 200, 60);
        backgroundPanel.add(levelMediumButton);

        levelHardButton = createLevelButton("微積");
        levelHardButton.setBounds(640, 300, 200, 60);
        backgroundPanel.add(levelHardButton);

        // ゲーム開始ボタン
        startButton = new JButton("ゲーム開始");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBounds(0, 510, 1000, 60);
        backgroundPanel.add(startButton);
    }

    private RoundedButton createLevelButton(String text) {
        RoundedButton button = new RoundedButton(text, Color.WHITE, 30, 30);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    // 以前の選択ボタンの状態をリセット
                    selectedButton.setSelected(false);
                    selectedButton.setBackground(Color.WHITE);
                    selectedButton.setForeground(new Color(20, 40, 139));
                    selectedButton.repaint();
                }
                    // 現在の選択ボタンを更新
                    selectedButton = button;
                    selectedButton.setSelected(true);
                    selectedButton.setBackground(new Color(20, 40, 139));
                    selectedButton.setForeground(Color.WHITE);
                    selectedButton.repaint();
            }
        });
        return button;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public RoundedButton getLevelEasyButton() {
        return levelEasyButton;
    }

    public RoundedButton getLevelMediumButton() {
        return levelMediumButton;
    }

    public RoundedButton getLevelHardButton() {
        return levelHardButton;
    }
}

class StayBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public StayBackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class RoundedButton extends JButton {
    private int arcWidth;
    private int arcHeight;
    private boolean isSelected; // 選択状態のフラグ

    public RoundedButton(String text, Color backgroundColor, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.isSelected = false;
        setBackground(backgroundColor);
        setForeground(new Color(20, 40, 139));
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - fm.getDescent());
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected) {
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.WHITE);
        } else {
            g2.setStroke(new BasicStroke(1));
            g2.setColor(new Color(20, 40, 139));
        }
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }
}

