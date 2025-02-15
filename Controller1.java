package TypingGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 待機画面
class Controller1 implements ActionListener {
    private StayView stayView;
    private Model model;

    public Controller1(StayView stayView, Model model) {
        this.stayView = stayView;
        this.model = model;

        stayView.getStartButton().addActionListener(this);
        stayView.getLevelEasyButton().addActionListener(e -> model.setDifficulty(0)); // 簡単
        stayView.getLevelMediumButton().addActionListener(e -> model.setDifficulty(1)); // 普通
        stayView.getLevelHardButton().addActionListener(e -> model.setDifficulty(2)); // 難しい
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stayView.getStartButton()) {
            model.Game(); // ゲーム画面へ切り替える
        }
    }
}
