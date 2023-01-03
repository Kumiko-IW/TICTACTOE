import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class tictactoe {
	public static void main(String args[]) {
		//  ゲームボードに番地振ります
		String[][] gameboard = {{"11","12","13"},{"21","22","23"},{"31","32","33"}};
		String circle = "○";
		String cross  = "×";
		System.out.println(display_board);
		//System.out.println("11|12|13|"); 
		//System.out.println("21|22|23|");
		//System.out.println("31|32|33|");
		
		//countOfInputが９になるか、game_endsフラグがTRUEになるまで○と×に交互に入力を促す
		int countOfInput = 0;
		boolean game_ends = false;
		input_Number input_Circle_Number  = new input_Number;
		input_Number input_Cross_Number = new input_Number;
		
		for(countOfInput < 9) {
			while(!game_ends) {
				countOfInput += 1;
				//偶数なら”○”の番。それ以外は”×”の番
				if(countOfInput％2 == 0) {
					//入力受付メソッドの戻り値を受け取り、判定メソッドへ渡す
					String circle_input_str = input_Circle_Number(gameboard);
					//判定メソッドを呼び出し、勝敗がつけばgame_endsメソッドにTRUEを返す
					game_ends = game_record(circle_input_str);
					if(game_ends) {
						break;
				}else {
					//入力受付メソッドの戻り値を受け取り、判定メソッドへ渡す
					String cross_input_str = input_Cross_Number(gameboard);
					//判定メソッドを呼び出し、勝敗がつけばgame_endsメソッドにTRUEを返す
					game_ends = game_record(cross_input_str);
					if(game_ends) {
						break;
				}//End Of If-else文
			}//End Of while文
			System.out.println("あいこです")
		} //End Of for文
	}   //　メインメソッドここまで	

	//  入力受付と入力値バリデーションを行うメソッド。入力値を戻り値にする。
	private static String input_Number(String gameboard[][]) {
		//  入力値の検証がtrue（空き番地として存在する２桁の数字のみ）になるまで再入力を促す
		boolean valideted = false;
		while(!valideted) {
			//  ゲームボードを表示させるメソッドの呼び出し
			display_board(gameboard);
			//  メッセージ表示メソッド。○×を書く場所を数字2桁で入力してください
			display_input_message(flag);
			Scanner sc = new Scanner(System.in);
			String input_Number    = sc.nextLine();
			String input_arr[]  = input_str.split("");
			//入力値のバリデーション＆アドレス空き確認のメソッド呼び出し
			valideted = checkAddress(input_Number, input_arr,gameboard, valideted, flag);
			if(valideted == true){
			return input_Number;
			} // End Of if valideted = true
		}     // End Of while
		return null;
	}   // End Of turn

	private static void display_input_message(String flag) {
		System.out.println(flag + "を書く場所を数字2桁で入力してください");
	}

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
	
	//入力値のバリデーション＆アドレスの空き確認メソッド
	private static boolean checkAddress(String input_str, String input_arr[], String gameboard[][], boolean valideted ,String flag) {
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
	private static boolean game_record(String input_str) {
		
		List<Integer> address_list = new ArrayList<>();
		address_list.add((Integer.parseInt(input_str)));
		
		List<Integer>result_list = new ArrayList<>();
		//新しく受け取った入力値（input_str）をすでにある配列の数値と比較をしてその差分をリストに格納していく
		for(int i=0 ; i < address_list.size() ; i++) {
			result_list.add( Integer.parseInt(input_str) - address_list.get(i));	
		}
		//result_listの要素を順に比較して、同じ数が２つあればWIN
		int result =0 ;
		for (int i = 0; i < result_list.size(); i++) {
		    for (int k = 0; k < result_list.size(); k++) {
		        if (k != i && result_list.get(k) == result_list.get(i)) { 
		            result++;
		            if(result >= 2) {
		            	return true;
		            }//End Of result >= 2
		        } //End Of k != iのループ
		    }	//End Of Kのループ
		}//End Of iのループ
		return false;
	}//End Of game_recordメソッド
}//End Of Class
