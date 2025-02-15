package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

class GameBackground extends JPanel {
    private Image backgroundImage;

    public GameBackground(String imagePath) {
        // 背景画像をロード
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new GridLayout(5,1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = backgroundImage.getWidth(this);
            int imgHeight = backgroundImage.getHeight(this);
            double scaleX = (double) panelWidth / imgWidth;
            double scaleY = (double) panelHeight / imgHeight;
            double scale = Math.max(scaleX, scaleY);  // 縦横比を維持
            // 新しい幅と高さを計算
            int newWidth = (int) (imgWidth * scale);
            int newHeight = (int) (imgHeight * scale);
            // 画像を中央に配置するための座標を計算
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;
            // 画像を描画
            g.drawImage(backgroundImage, x, y, newWidth, newHeight, this);
        }
    }
}

class GameView extends JFrame implements Observer{
    protected Model model;
    protected JLabel romajiLabel;
    protected JLabel kanjiLabel;
    protected JLabel scoreLabel;       // スコアを表示
    protected JLabel timeLabel;        // 残り時間を表示
    protected JLabel corLabel;         // 正解数を表示
    protected JLabel missLabel;        // ミス数を表示
    protected JButton endButton;       // 終了ボタン
    protected JLabel deltaTime;		// 追加時間
    
    public GameView(Model m) {
    	this.model=m;
    	model.addObserver(this);
    	
    	// 背景画像付きのパネルを設定
    	GameBackground backgroundPanel = new GameBackground("bb.png");
        setContentPane(backgroundPanel);
        
        setTitle("TAIPUEC"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel topPanel = new JPanel(new GridLayout(1,3));
        JPanel t1 = new JPanel();  // 空白
        timeLabel = new JLabel("",SwingConstants.CENTER);
        deltaTime = new JLabel("",SwingConstants.LEFT);
        timeLabel.setFont(new Font("Impact",Font.PLAIN, 35));
        deltaTime.setFont(new Font("Impact",Font.PLAIN, 25));
        topPanel.add(t1);
        topPanel.add(timeLabel);
        topPanel.add(deltaTime);

        JPanel sentencePanel = new JPanel(new GridLayout(2,1));
        sentencePanel.setOpaque(false); // 背景を透過
        romajiLabel = new JLabel("",SwingConstants.CENTER);
        kanjiLabel = new JLabel("",SwingConstants.CENTER);
        romajiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        kanjiLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        sentencePanel.add(kanjiLabel);
        sentencePanel.add(romajiLabel);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        scoreLabel = new JLabel("",SwingConstants.CENTER);
        corLabel = new JLabel("",SwingConstants.CENTER);
        missLabel = new JLabel("",SwingConstants.CENTER);
        endButton = new JButton("ゲームを終了");
        scoreLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        corLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        missLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        endButton.setFont(new Font("Impact",Font.PLAIN, 20));
        bottomPanel.add(scoreLabel);
        bottomPanel.add(corLabel);
        bottomPanel.add(missLabel);
        bottomPanel.add(endButton);
        bottomPanel.setBorder(new LineBorder(new Color(20, 40, 139), 4));
        bottomPanel.setBackground(new Color(220, 220, 220));
        
        backgroundPanel.add(topPanel);
        JPanel n1 = new JPanel(); n1.setOpaque(false);
        backgroundPanel.add(n1); // レイアウトの調整のための空白
        backgroundPanel.add(sentencePanel);
        JPanel n2 = new JPanel(); n2.setOpaque(false);
        backgroundPanel.add(n2); // レイアウトの調整のための空白
        backgroundPanel.add(bottomPanel);
    }
    
    public JButton getEndButton() { return endButton; }

    public void update(Observable o, Object arg) {
    	switch(model.getdelta()) {
    	case 0:
    		deltaTime.setText("");
    		break;
    	case 1:
    		deltaTime.setText("<html><span style='color:rgb(20, 40, 139);'>+"+5*model.getprevLength()/3+" pts</span></html>");
    		break;
    	case 2:
    		deltaTime.setText("<html><span style='color:rgb(200,50,50);'>-1.0 秒</span></html>");
    		break;
    	}
    	// sentencePanel
    	romajiLabel.setText(model.getSentence());
    	kanjiLabel.setText(model.getKanji());
    	// bottomPanel
    	scoreLabel.setText("<html>スコア: <span style='color:rgb(20, 40, 139); font-size:20px;'>"+model.getPoints()+"</span> pts</html>");
    	corLabel.setText("<html>正解: <span style='font-size:20px;'>"+model.getCorrect()+"</span> 個</html>");
    	missLabel.setText("<html>ミスタイプ: <span style='font-size:20px;'>"+model.getMiss()+"</span> 回</html>");
    }
}
