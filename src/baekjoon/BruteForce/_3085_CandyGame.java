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
 * - String.toCharArray 하면, char는 string의 크기에 맞게 생성됨 앞에 공백이 필요하다면 " "+string한 것을 toCharArray해야함
 * - 배열의 크기 때문에 자꾸 헷갈리니까 그냥 변수화해서 사용하자 그게 헷갈리지 않는다.
 * 
 * [고쳐야할 것]
 * - 이 문제는 DFS로 푸는 문제가 아니다... 상당히 잘못생각했다.
 * - 코드로 옮기기 전에 문제를 어떻게 풀어야할 지 알아야 한다... 처음에 잘못푸니까  시간이 엄청 날라간다
 * - 완전탐색이라고 재귀/백트래킹 이라는 생각을 버린다!!
 * 
 *** 반복문 설정할 때 대충하는 습관 버려야 한다.. 심각하다,, 자꾸 이 실수때문에 시간을 너무 많이 잡아먹는다 ***
 */
import java.util.*;

public class _3085_CandyGame {
	static int answer = 1;
	static char[][] board;

	static void exchangeCol(int row, int col) {
		// 2차원 배열에 경우 array.clone() 함수를 써도, 깊은복사가 되지 않습니다.
		char[][] b = board;

		char temp = b[row][col];
		// 오른쪽 교환(열끼리 교환)
		b[row][col] = b[row][col + 1];
		b[row][col + 1] = temp;
		candyCount(b);
		b[row][col + 1] = b[row][col];
		b[row][col] = temp;
	}

	static void exchangeRow(int row, int col) {
		char[][] b = board;

		char temp = b[row][col];
		// 아래쪽 교환(행끼리 교환)
		b[row][col] = b[row + 1][col];
		b[row + 1][col] = temp;
		candyCount(b);
		b[row + 1][col] = b[row][col];
		b[row][col] = temp;
	}

	static void candyCount(char[][] b) {
		// 연속된 것들 세기
		for (int i = 1; i < b.length; i++) {
			char currentC = b[i][1];
			char currentR = b[1][i];
			int cntC = 1;
			int cntR = 1;
			for (int j = 2; j < b[i].length; j++) {
				char nextC = b[i][j];
				if (currentC == nextC) {
					cntC++;
					answer = Math.max(answer, cntC);
				} else {
					currentC = b[i][j];
					cntC = 1;
				}

				char nextR = b[j][i];
				if (currentR == nextR) {
					cntR++;
					answer = Math.max(answer, cntR);
				} else {
					currentR = b[j][i];
					cntR = 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		String[] input = new String[size + 1];
		for (int i = 1; i <= size; i++) {
			input[i] = sc.next();
			input[i] = " " + input[i]; // 실수 ㄴㄴ!
		}

		board = new char[size + 1][size + 1];

		for (int i = 1; i <= size; i++) {
			board[i] = input[i].toCharArray();
		}

		// 여기서 엄청난 실수를 저질렀다!!!
		// 밑에것처럼 해버리면 4x4 밖에 안구해진다..
//		for (int i = 1; i < size; i++) {
//			for (int j = 1; j < size; j++) {
//				exchangeCol(i, j);
//				exchangeRow(i, j);
//			}
//		}
		
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j < size; j++) {
				exchangeCol(i, j);
			}
		}
		
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j < size; j++) {
				exchangeRow(j, i);
			}
		}

		System.out.println(answer);
	}
}
