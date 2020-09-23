package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4613_러시아국기같은깃발 {

	private final static int WHITE = 0;
	private final static int BLUE = 1;
	private final static int RED = 2;
	private static int[][] memo;
	private static char[][] map;
	private static int m;
	private static int n;
	private static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		char[] colors = { 'W', 'B', 'R' };

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][];
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
			}

			memo = new int[n][3]; // [줄][색상] // 해당 줄에서 어떤 색상일 때 새롭게 칠해야하는 갯수 저장

			for (int i = 0; i < n; i++) {
				for (int color = 0; color < 3; color++) {
					// 각 색깔별로 memo 채우기
					int cnt = 0;
					for (int j = 0; j < m; j++) {
						if (map[i][j] != colors[color]) {
							cnt++;
						}
					}
					memo[i][color] = cnt;
				}
			}

			min = Integer.MAX_VALUE;

			func(1, WHITE, memo[0][0]); // 0 번째는 무조건 흰색이므로 1부터 시작

			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static void func(int k, int pre, int cnt) {
//		if (cnt >= min) {
//			return;
//		}

		if (k == n) { // 마지막줄은 무조건 빨강
			min = Math.min(min, cnt);
			return;
		}

		if (pre == WHITE) { // 전이 흰색이면 흰,파 가능
			if (k < n - 2) {
				func(k + 1, WHITE, cnt + memo[k][0]);
			}
			if (k < n - 1) {
				func(k + 1, BLUE, cnt + memo[k][1]);
			}
		}

		else if (pre == BLUE) { // 전이 파랑이면 파,빨 가능
			if (k < n - 1) {
				func(k + 1, BLUE, cnt + memo[k][1]);
			}
			if (2 <= k && k < n) {
				func(k + 1, RED, cnt + memo[k][2]);
			}
		}

		else if (pre == RED) { // 전이 빨강이면 빨만 가능
			if (2 <= k && k < n) {
				func(k + 1, RED, cnt + memo[k][2]);
			}
		}
	}

}
