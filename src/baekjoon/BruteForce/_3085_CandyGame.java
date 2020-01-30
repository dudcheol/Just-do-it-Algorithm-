package baekjoon.BruteForce;

/*
 * 3085 - 사탕게임
 * 시작: 09:25
 * 끝1: 10:55 -10분
 * 끝2: 12:07
 * 시간1: 1시간 20분 - 시간초과!
 * 시간2: 2시간 42분 - 메모리초과!
 * 
 * [배운 것]
 * - 2차원 배열에 경우 array.clone() 함수를 써도, 깊은복사가 되지 않습니다.
 */
import java.util.*;

public class _3085_CandyGame {
	static int size; // board의 크기
	static int[] col;
	static int answer = 0;
	static char[][] board;

	static void getCandy(int row) {
		if (row != 0) {
			exchangeCol(row, col[row]);
			exchangeRow(row, col[row]);
		}

		// row=현재위치인데, row까지는 약속된 행동을 모두 했으므로
		// row = size-1이면 size-1까지는 약속된 행동을 모두 한것과 같다.
		// 따라서 row==size-1이면 재귀를 return 한다.
		if (row == size - 1) { // 이런 실수 너무 많이한다 ㅠㅠ
			return;
		}

		for (int i = 1; i < col.length - 1; i++) {
			// col[row]는 row행에 있는 몇 열에 위치해있는 지 알려줌
			col[row + 1] = i;
			getCandy(row + 1);
		}
	}

	static void exchangeCol(int row, int col) {
		// 2차원 배열에 경우 array.clone() 함수를 써도, 깊은복사가 되지 않습니다.
		char[][] b = deepCopy(board);

		char temp = b[row][col];
		// 오른쪽 교환(열끼리 교환)
		b[row][col] = b[row][col + 1];
		b[row][col + 1] = temp;

		candyCount(b);
	}

	static void exchangeRow(int row, int col) {
		char[][] b = deepCopy(board);

		char temp = b[row][col];
		// 아래쪽 교환(행끼리 교환)
		b[row][col] = b[row + 1][col];
		b[row + 1][col] = temp;

		candyCount(b);
	}

	static void candyCount(char[][] b) {
		// 연속된 것들 세기
		for (int i = 1; i < b.length; i++) {
			char currentC = b[i][1];
			char currentR = b[1][i];
			int cntC = 1;
			int cntR = 1;
			for (int j = 2; j < b[i].length; j++) {
				if (currentC == b[i][j]) {
					cntC++;
					answer = Math.max(answer, cntC);
				} else {
					currentC = b[i][j];
					cntC = 1;
				}

				if (currentR == b[j][i]) {
					cntR++;
					answer = Math.max(answer, cntR);
				} else {
					currentR = b[j][i];
					cntR = 1;
				}
			}
		}

//		// 열만봤을때 연속된 것들 세기
//		for (int i = 1; i < b.length; i++) {
//			char current = b[1][i];
//			int cnt = 1;
//			for (int j = 2; j < b[i].length; j++) {
//				if (current == b[j][i]) {
//					cnt++;
//					answer = Math.max(answer, cnt);
//				} else {
//					current = b[j][i];
//					cnt = 1;
//				}
//			}
//		}
	}

	static char[][] deepCopy(char[][] target) {
		char[][] copied = new char[target.length][target.length];
		for (int i = 1; i < target.length; i++) {
			for (int j = 1; j < target.length; j++) {
				copied[i][j] = target[i][j];
			}
		}
		return copied;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		String[] input = new String[size + 1];
		for (int i = 1; i <= size; i++) {
			input[i] = sc.next();
			input[i] = " " + input[i]; // 실수 ㄴㄴ!
		}

		board = new char[size + 1][size + 1];

		for (int i = 1; i <= size; i++) {
			board[i] = input[i].toCharArray();
		}
		col = new int[size + 1];

		getCandy(0);

		System.out.println(answer);
	}
}
