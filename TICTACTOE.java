import java.util.Scanner;
public class Tictactoe {
	
	public static void main(String args[]) {
		//  ゲームボードに番地振ります
		String[][] gameboard = {{"11","12","13"},{"21","22","23"},{"31","32","33"}};
		String circle = "○";
		String cross  = "×";
		
		//回数が９になるか、game_endsフラグがTRUEになるまで○と×に交互に入力を促す
		boolean game_ends = false;
		for(int i = 1; i <= 9; i++) {
			//奇数なら”○”の番。それ以外は”×”の番
			if(i % 2 != 0 ){
				//入力受付メソッドの戻り値を受け取り、判定メソッドへ渡す
				input_Number(circle, gameboard);
				//判定メソッドを呼び出し、勝敗がつけばgame_endsメソッドにTRUEを返す
				game_ends = game_record(circle, gameboard);
				if(game_ends) {
					break;
				}//End Of break文
			}else {
				//入力受付メソッドの戻り値を受け取り、判定メソッドへ渡す
				input_Number(cross, gameboard);
				//判定メソッドを呼び出し、勝敗がつけばgame_endsメソッドにTRUEを返す
				game_ends = game_record(cross, gameboard);
				if(game_ends) {
					break;
				}//End Of break文
				if (i==9) {
					System.out.println("あいこです");
					display_board(gameboard);
				}
			}//End Of If-else文
		} //End Of for文
		//System.out.println("あいこです");
	}   //　メインメソッドここまで	

	//  入力受付と入力値バリデーションを行うメソッド。入力値を戻り値にする。
	private static void input_Number(String flag, String gameboard[][]) {
		//  入力値の検証がtrue（空き番地として存在する２桁の数字のみ）になるまで再入力を促す
		boolean valideted = false;
		while(!valideted) {
			//  ゲームボードを表示させるメソッドの呼び出し
			display_board(gameboard);
			//  メッセージ表示メソッド。○×を書く場所を数字2桁で入力してください
			display_input_message(flag);
			Scanner sc = new Scanner(System.in);
			String input_Number    = sc.nextLine();
			String input_arr[]  = input_Number.split("");
			//入力値のバリデーション＆アドレス空き確認のメソッド呼び出し
			valideted = checkAddress(input_Number, input_arr,gameboard, valideted, flag);
			if(valideted == true){
			//return input_Number;
			} // End Of if valideted = true
		}     // End Of while
		//return null;
	}   // End Of turn

	//gameboadを表示させるメソッド
	private static void display_board(String gameboard[][]) {
		for(int i=0; i < gameboard.length; i++) {
			for(int j = 0; j < gameboard[i].length ; j++) {
				if(gameboard[i][j].equals("○")) {
					System.out.print("○");
				}else if(gameboard[i][j].equals("×")) {
					System.out.print("×");
				}else{
					System.out.print("□");
				}
				System.out.print("|");
				if(j == gameboard[i].length - 1) {
					System.out.println("");
				}
			}//End Of jループ
		}//End Of iループ
	}//End Of showboad
	
	static void display_input_message(String flag) {
		System.out.println(flag + "を書く場所を数字2桁で入力してください");
	}
	
	//入力値のバリデーション＆アドレスの空き確認メソッド
	static boolean checkAddress(String input_str, String input_arr[], String gameboard[][], boolean valideted ,String flag) {
		//入力値が２桁以外ならFALSEを返す。
		if(input_str.length() != 2) {
			System.out.println("アドレスは２桁で入力してください。");
			return valideted;
		//入力値が２桁なら、入力値が実在する番地か確認する。
		}else {
			for(int i = 0 ; i < gameboard.length ; i++ ) {
				for(int j = 0 ; j < gameboard[i].length; j++) {
					//入力値と合致するアドレスがあれば”○×”に置き換えてtrueを返す
					if(input_str.equals(gameboard[i][j])) {
						gameboard[i][j] = flag;
						valideted = true;
						return valideted;
					}//End Of iとgameboad[i][j]の比較ループ
				}//End Of jループ
			}//End Of iループ
			System.out.println("有効な番地の入力をお願いいたします。");
			return valideted;
		}//End Of else
	}//End Of checkAddressメソッド
	
	//3つ揃ったか確認するメソッド
	//配列の要素を全て比較して、差が同等のものが２つ以上揃えば上がり。
	private static boolean game_record(String cc ,String gameboard[][]) {
		if( gameboard[0][0] == cc && gameboard[0][1] == cc && gameboard[0][2] == cc ||
			gameboard[0][0] == cc && gameboard[1][1] == cc && gameboard[2][2] == cc ||
			gameboard[0][0] == cc && gameboard[1][0] == cc && gameboard[2][0] == cc ||
			gameboard[1][0] == cc && gameboard[1][1] == cc && gameboard[1][2] == cc ||
			gameboard[2][0] == cc && gameboard[2][1] == cc && gameboard[2][2] == cc ||	
			gameboard[2][0] == cc && gameboard[1][1] == cc && gameboard[0][2] == cc   ) {
			System.out.println(cc+"の勝ちです");
			display_board(gameboard);
			return true; 	
		} else {return false;
		}//End of if-else文
	}//End Of game_recordメソッド
}//End Of Class
