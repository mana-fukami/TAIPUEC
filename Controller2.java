package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

class Controller2 implements KeyListener,ActionListener{
    private GameView gameView;
    private Model model;
    /* 制限時間に関する変数 */
	private Timer timer;
    private boolean timerRunning = false; // タイマーの状態を追跡

    public Controller2(GameView gameView, Model model) {
        this.gameView = gameView;
        this.model = model;
        // イベントリスナーを登録
        this.gameView.setFocusable(true);
        this.gameView.addKeyListener(this);
        this.gameView.getEndButton().addActionListener(this);
        // タイマーの初期化（まだ開始しない）
        timer = new Timer(40, this); // 40 ms間隔で更新
    }

    public void startTimer() {// mainから呼び出される
        if (!timerRunning) {
            model.setTime(60 - model.getTime()); // 制限時間をリセット
            timer.start();
            timerRunning = true;
        }
    }

    public void resetTimer() {
        if (timerRunning) {
            timer.stop();
            timerRunning = false;
        }
        model.setTime(60 - model.getTime()); // 制限時間をリセット
    }

    public void resetGame() {
        resetTimer(); // タイマーをリセット
        model.resetGame(); // モデルをリセット
        gameView.setFocusable(true); // 再フォーカス設定
        gameView.requestFocusInWindow(); // キー入力を有効化
    }

    // キー入力
    @Override
    public void keyTyped(KeyEvent e) {
        char typedChar = e.getKeyChar(); 
        model.Check(typedChar); // 文字が入力されたらCheckを呼び出す
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameView.getEndButton()) {
            model.Result(); // 結果画面へ切り替える 
            timer.stop();
        }
        if(model.getTime()<=0) { // ゲームを終了
        	model.Result();
        	timer.stop();
        }else { // ゲーム継続中
        	model.setTime(-0.04);
        	gameView.timeLabel.setText(String.format("00:%05.2f", model.getTime()));
            // 10秒以下ならラベルを赤色に
            if (model.getTime() <= 10) {
                gameView.timeLabel.setForeground(Color.RED);
                gameView.timeLabel.setFont(new Font("Impact",Font.BOLD, 40));
            } else {
                gameView.timeLabel.setForeground(Color.BLACK); // それ以外は黒に戻す
                gameView.timeLabel.setFont(new Font("Impact",Font.PLAIN, 35));
            }            
        }
    }
}
