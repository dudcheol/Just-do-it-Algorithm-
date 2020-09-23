package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17069_파이프옮기기2 {
	private static final int GARO = 0;
	private static final int SERO = 1;
	private static final int DAEGAK = 2;
	private static int N;
	private static int[][] map;
	private static int[] dy = { 0, 1, 1 }; // 우,하,우하
	private static int[] dx = { 1, 0, 1 }; // 우,하,우하
	private static int[][] canMove = { { GARO, DAEGAK }, { SERO, DAEGAK }, { GARO, SERO, DAEGAK } }; // 우,하,우하
	private static long answer;
	private static long[][][] cache;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		cache = new long[N][N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cache[i][j][0] = -1;
				cache[i][j][1] = -1;
				cache[i][j][2] = -1;
			}
		}

		answer = dfs(0, 1, GARO);
		System.out.println(answer);
	}

	private static long dfs(int y, int x, int dir) {
		if (y == N - 1 && x == N - 1) {
			return 1;
		}
		
		if(cache[y][x][dir] != -1) {
			return cache[y][x][dir];
		}

		long ret = 0;
		int[] move = canMove[dir];
		loop: for (int i = 0; i < move.length; i++) {

			int ny = y + dy[move[i]];
			int nx = x + dx[move[i]];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1)
				continue;

			if (move[i] == DAEGAK) { // 대각선으로 이동방향 전에 우, 하 모두 비어있어야 함
				for (int d = 0; d < 2; d++) {
					int my = y + dy[d];
					int mx = x + dx[d];
					if (my < 0 || mx < 0 || my >= N || mx >= N || map[my][mx] == 1)
						break loop; // 대각은 항상 마지막에 확인하므로 조건 충족하지 않을 시 return해버려도 됨
				}
			}

			ret += dfs(ny, nx, move[i]);
		}
		return cache[y][x][dir] = ret;
	}

}
