package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// 結果画面
class ResultView extends JFrame {
    private JLabel endLabel;
    private JLabel stageLabel;
    private JLabel scoreTitleLabel;
    private JLabel correctTitleLabel;
    private JLabel missTitleLabel;
    private JLabel scoreLabel;
    private JLabel correctLabel;
    private JLabel missLabel;
    private JButton retryButton;

    public ResultView() {
        setTitle("TAIPUEC");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 背景パネル
        StayBackgroundPanel backgroundPanel = new StayBackgroundPanel("view13_bg.png");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        // ステージ
        endLabel = new JLabel("ステージ", SwingConstants.CENTER);
        endLabel.setFont(new Font("Arial", Font.BOLD, 16));
        endLabel.setForeground(Color.GRAY);
        endLabel.setBounds(300, 20, 400, 50);
        backgroundPanel.add(endLabel);

        // ステージ
        stageLabel = new JLabel("基礎科学実験", SwingConstants.CENTER);
        stageLabel.setFont(new Font("Arial", Font.BOLD, 32));
        stageLabel.setForeground(Color.GRAY);
        stageLabel.setBounds(300, 60, 400, 50);
        backgroundPanel.add(stageLabel);

        // スコアタイトルラベル
        scoreTitleLabel = new JLabel("スコア", SwingConstants.CENTER);
        scoreTitleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        scoreTitleLabel.setForeground(Color.BLACK);
        scoreTitleLabel.setBounds(300, 160, 400, 50);
        backgroundPanel.add(scoreTitleLabel);

        // スコアラベル
        scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 64));
        scoreLabel.setForeground(new Color(20, 40, 139));
        scoreLabel.setBounds(300, 220, 400, 50);
        backgroundPanel.add(scoreLabel);

        // 正解数タイトルラベル
        correctTitleLabel = new JLabel("正解数", SwingConstants.CENTER);
        correctTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        correctTitleLabel.setForeground(Color.BLACK);
        correctTitleLabel.setBounds(250, 300, 250, 50);
        backgroundPanel.add(correctTitleLabel);

        // 正解数ラベル
        correctLabel = new JLabel("0", SwingConstants.CENTER);
        correctLabel.setFont(new Font("Arial", Font.BOLD, 56));
        correctLabel.setForeground(Color.BLACK);
        correctLabel.setBounds(250, 360, 250, 50);
        backgroundPanel.add(correctLabel);

        // ミス数タイトルラベル
        missTitleLabel = new JLabel("ミスタイプ回数", SwingConstants.CENTER);
        missTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        missTitleLabel.setForeground(Color.BLACK);
        missTitleLabel.setBounds(500, 300, 250, 50);
        backgroundPanel.add(missTitleLabel);

        // ミス数ラベル
        missLabel = new JLabel("0", SwingConstants.CENTER);
        missLabel.setFont(new Font("Arial", Font.BOLD, 56));
        missLabel.setForeground(Color.BLACK);
        missLabel.setBounds(500, 360, 250, 50);
        backgroundPanel.add(missLabel);

        // 半透明背景
        TransparentPanel transparentPanel = new TransparentPanel();
        transparentPanel.setBounds(250, 150, 500, 300);
        backgroundPanel.add(transparentPanel);

        // 'もう一度プレイ'ボタン
        retryButton = new JButton("もう一度プレイ");
        retryButton.setFont(new Font("Arial", Font.BOLD, 18));
        retryButton.setBounds(0, 510, 1000, 60);
        backgroundPanel.add(retryButton);
    }

    // 結果の更新
    public void updateResults(int difficulty, int score, int correct, int miss) {
        String stage = "";
        switch (difficulty) {
        case 0:
            stage = "基礎科学実験";
			break;
		case 1:
			stage = "線形代数";
			break;
		case 2:
			stage = "微積";
			break;
		default:
			throw new IllegalArgumentException("無効な難易度: " + difficulty);
        }
        stageLabel.setText(stage);
        scoreLabel.setText("" + score);
        correctLabel.setText("" + correct);
        missLabel.setText("" + miss);
    }

    public JButton getRetryButton() {
        return retryButton;
    }
}

// 半透明パネルクラス
class TransparentPanel extends JPanel {
    public TransparentPanel() {
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 150));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
    }
}
class ResultBackgroundPanel extends JPanel {
    private Image backgroundImage;
    public ResultBackgroundPanel(String imagePath) {
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