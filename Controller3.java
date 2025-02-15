package TypingGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 結果画面
class Controller3 implements ActionListener {
    private ResultView resultView;
    private Model model;

    public Controller3(ResultView resultView, Model model) {
        this.resultView = resultView;
        this.model = model;

        resultView.getRetryButton().addActionListener(this);
    }

    public void showResults() {
        // モデルから結果情報を取得
        int difficulty = model.getDifficulty();
        int score = model.getPoints();
        int correct = model.getCorrect();
        int miss = model.getMiss(); 
        resultView.updateResults(difficulty, score, correct, miss); // 結果ビューに反映
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resultView.getRetryButton()) {
            model.Stay(); // 待機画面へ切り替える
        }
    }
}