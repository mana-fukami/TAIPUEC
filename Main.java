package TypingGame;

public class Main {
	public static StayView stayview;
	public static GameView gameview;
	public static ResultView resultview;
	public static Model model;

	public static Controller1 stayController;
    public static Controller2 gameController;
    public static Controller3 resultController;
	
	public static int flag;//0:待機,1:ゲーム,2:結果
	//-----setter-----
	public void setFlag(int num) {
		flag=num;
		viewChange();
		//System.out.println(flag);
	}
	//-----getter-----
	public int getFlag() {return flag;}
	//-----メイン関数-----
	public static void main(String[] args) {
		// モデルの生成
		model=new Model();
		//文字入力のローマ字は"ローマ字入力表別フォント.pdf"の()じゃない方で指定
		// ビューの生成
        stayview = new StayView();
        gameview = new GameView(model);
        resultview = new ResultView();

		// コントローラーの生成（初期化を一度だけ実施）
        stayController = new Controller1(stayview, model);
        gameController = new Controller2(gameview, model);
        resultController = new Controller3(resultview, model);
        
        //待機画面の表示
        model.Stay();
		//viewChange();
	}

	public static void viewChange() {
		switch(flag) {
		case 0:
			resultview.setVisible(false);
			stayview.setVisible(true);
			break;
		case 1:
			stayview.setVisible(false);
			gameview.setVisible(true);
			gameController.resetGame();
            gameController.startTimer();
			break;
		case 2:
			gameview.setVisible(false);
			resultview.setVisible(true);
			resultController.showResults();
			break;
		}
	}

}
