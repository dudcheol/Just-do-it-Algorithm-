package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4613_러시아국기같은깃발 {

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

			int[][] D = new int[n][2];

			int init = 0;
			init += memo[0][0];
			for (int i = 1; i < n - 1; i++) {
				init += memo[i][1];
			}
			init += memo[n - 1][2];
			
			D[0][0] = init; // 흰색을 늘림
			D[0][1] = init; // 빨강을 늘림

			min = init;
			for (int i = 1; i < n - 2; i++) {
				D[i][0] = D[i - 1][0] + memo[i][0] - memo[i][1];
				D[i][1] = D[i - 1][1] + memo[n - 1 - i][2] - memo[n - 1 - i][1];
				
				min = Math.min(min, D[i][0]);
				min = Math.min(min, D[i][1]);
			}

			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

}
