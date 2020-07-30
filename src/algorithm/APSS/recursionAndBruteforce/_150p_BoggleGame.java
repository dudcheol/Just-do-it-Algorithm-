package algorithm.APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 150p - 보글게임 - 완전탐색으로 풀기
 * 코드별로 '회고'기록 있음
 */
import java.io.*;

public class _150p_BoggleGame {
	static String[] words;
	static char[][] board;
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[] dy = { 1, 1, 1, 0, 0, -1, -1, -1 };

/* 나의 접근 */
	/*
	 * 완전탐색-재귀를 이용해 풀려고 했으나, 판안에 있는 알파벳을 선택한 후 그것을 가지고 재귀를 돌려야 했는데
	 * 판자체를 재귀를 이용해 돌려고 했다.
	 	->  왜그랬나? 문제를  보자마자 대충 어떻게 풀어야겠다고 결정짓고 바로 실행에 옮겨버림
	 		종이에 풀 때, 완전히 다 풀지 않고 어느정도 가늠만 잡히면 바로 코드로 옮겨버림 <매번 같은 실수
	 		위 행동 때문에 코드로 다 짜놓고 잘못 푼 케이스가 많음
	 * 
	 */
//	static void findableWord(int row, int col) {
//		if (row >= 5)
//			return;
//
//		if (findWord(row, col)) {
//			return;
//		}
//
//		if (row >= 0) {
//			System.out.print(board[row][col]);
//		}
//
//		for (int i = 0; i < 5; i++) {
//			if (row != -1 && visited[row])
//				break;
//			findableWord(row + 1, col + i);
//		}
//
//		if (row != -1)
//			visited[row] = true;
//
//		System.out.println();
//		return;
//	}

	static boolean findWord(int row, int col, String word) {
		if (row < 0 || col < 0 || row >= 5 || col >= 5)
			return false;
		/*포인트1*/if (!word.startsWith(board[row][col] + ""))
			return false;
		if (word.length() == 1)
			return true;

		for (int i = 0; i < 8; i++) {
			int x = row - dx[i];
			int y = col - dy[i];

	/* 나는 왜 이러한 풀이를 생각해내지 못했나? */
		/*
		 * 가장 큰 문제는, 이미 외워버린 '틀'에 맞게만 풀려고하는 습관이다.
		 * 매개변수를 응용해서 좀 더 쉽게 풀 수 있는 방법을 생각해보아야 한다. 
		 * 위 코드에서 <포인트1>을 보면 현재 선택된 board의 알파벳이 word의 첫 글자와 같은지 확인한다.
		 * <포인트2>에서 word의 앞글자를 제거해줬기 때문이다.
		 * 밑에 '//'로 주석친 부분은 내가 잘못생각한 부분이다.
		 * "다음 칸이 범위 안에 있는지, 첫 글자는 일치하는지 확인할 필요가 없다." 왜냐? 먼저 해줬기 때문.
		 */
//			if (board[x][y] == word.charAt(0)) {
			/* 여기서 board와 word의 앞 알파벳이 같은지 확인하는게 아니다!
			 * "완전탐색"이니까, 미리 걸러내려는 꼼수를 쓰지 말아보자..!
			 * 무식하게 풀어보자. 걸러내지말고 일단 그냥 다 넣어보고
			 * 넣은 곳에서 확인해보자. */
				if (findWord(x, y, /*포인트2*/word.substring(1))) {
					return true;
				}
//			}
		}
		return false;
	}

	static void visitBoard(String w) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (findWord(i, j, w)) {
					System.out.println(w + " YES");
					return;
				}
			}
		}
		System.out.println(w + " NO");
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine()); // 테스트케이스 수

		board = new char[5][5];
		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray();
		}

		int n = Integer.parseInt(br.readLine()); // 알고있는 단어의 수
		words = new String[n];
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}

		for (String w : words) {
			visitBoard(w);
		}

		br.close();
	}
}
