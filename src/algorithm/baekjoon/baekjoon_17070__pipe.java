package algorithm.baekjoon;

import java.util.Scanner;

public class baekjoon_17070__pipe {
	static int N = 0;
	static int[][] house;
	static int answer = 0;

	static void dfs(int state, int r1, int c1, int r2, int c2) {
		if (r2 >= N || c2 >= N) {
			return;
		}
		switch (state) {
		case 1:
			if (house[r2][c2] == 1)
				return;
			dfs(1, r1, c1 + 1, r2, c2 + 1);
			dfs(3, r1, c1 + 1, r2 + 1, c2 + 1);
			break;
		case 2:
			if (house[r2][c2] == 1)
				return;
			dfs(2, r1 + 1, c1, r2 + 1, c2);
			dfs(3, r1 + 1, c1, r2 + 1, c2 + 1);
			break;
		case 3:
			if (house[r2][c2] == 1 || house[r2 - 1][c2] == 1 || house[r2][c2 - 1] == 1)
				return;
			dfs(1, r1 + 1, c1 + 1, r2, c2 + 1);
			dfs(2, r1 + 1, c1 + 1, r2 + 1, c2);
			dfs(3, r1 + 1, c1 + 1, r2 + 1, c2 + 1);
			break;
		}
		if (r2 == N - 1 && c2 == N - 1) {
			answer++;
			return;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N은 0부터 시작하므로 주의할 것
		N = sc.nextInt();

		house = new int[N][N];

		// 입력받음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				house[i][j] = sc.nextInt();
			}
		}

		/*
		 * state 1 : 가로 / 2 : 세로 / 3 : 대각선
		 */
		dfs(1, 0, 0, 0, 1); // 배열은 0부터 시작

		System.out.println(answer);
	}
}
