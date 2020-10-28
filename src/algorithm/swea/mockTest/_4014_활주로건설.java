package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4014_활주로건설 {

	private static int N;
	private static int X;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = 0;
			// 가로방향 검사
			loop: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[i][j] == map[i][j + 1]) {

					} else if (map[i][j] > map[i][j + 1]) {
						if (Math.abs(map[i][j] - map[i][j + 1]) > 1 || visited[i][j+1])
							continue loop;
						boolean isOk = true;
						int pcur = map[i][j + 1];
						for (int k = j + 2; k < j + 1 + X; k++) {
							if (k >= N || visited[i][k] || pcur != map[i][k]) {
								isOk = false;
								break;
							}
						}
						if (!isOk)
							continue loop;
						for (int k = j + 1; k < j + 1 + X; k++) {
							visited[i][k] = true;
						}
					} else {
						if (Math.abs(map[i][j] - map[i][j + 1]) > 1 || visited[i][j])
							continue loop;
						boolean isOk = true;
						int pcur = map[i][j];
						for (int k = j - 1; k > j - X; k--) {
							if (k < 0 || visited[i][k] || pcur != map[i][k]) {
								isOk = false;
								break;
							}
						}
						if (!isOk)
							continue loop;
						for (int k = j - 1; k > j - X; k--) {
							visited[i][k] = true;
						}
					}
				}
				cnt++;
			}

			// 세로방향 검사
			visited = new boolean[N][N];
			loop2: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[j][i] == map[j + 1][i]) {

					} else if (map[j][i] > map[j + 1][i]) {
						if (Math.abs(map[j][i] - map[j + 1][i]) > 1 || visited[j+1][i])
							continue loop2;
						boolean isOk = true;
						int pcur = map[j + 1][i];
						for (int k = j + 2; k < j + 1 + X; k++) {
							if (k >= N || visited[k][i] || pcur != map[k][i]) {
								isOk = false;
								break;
							}
						}
						if (!isOk)
							continue loop2;
						for (int k = j + 1; k < j + 1 + X; k++) {
							visited[k][i] = true;
						}
					} else {
						if (Math.abs(map[j][i] - map[j + 1][i]) > 1 || visited[j][i])
							continue loop2;
						boolean isOk = true;
						int pcur = map[j][i];
						for (int k = j - 1; k > j - X; k--) {
							if (k < 0 || visited[k][i] || pcur != map[k][i]) {
								isOk = false;
								break;
							}
						}
						if (!isOk)
							continue loop2;
						for (int k = j - 1; k > j - X; k--) {
							visited[k][i] = true;
						}
					}
				}
				cnt++;
			}

			sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}