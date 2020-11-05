package algorithm.swea.D4;

import java.util.*;
import java.io.*;

public class _1868_파핑파핑지뢰찾기 {

	private static int N;
	private static Queue<int[]> q;
	private static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 좌상~좌좌
	private static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	private static int[][] bmap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][];
			bmap = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 각 칸별로 지뢰갯수 미리 구해놓기 -- 핵심*****
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*')
						continue;
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if (ny < 0 || nx < 0 || ny >= N || nx >= N)
							continue;
						if (map[ny][nx] == '*') {
							cnt++;
							bmap[ny][nx] = -1;
						}
					}
					bmap[i][j] = cnt;
				}
			}

			// 0인부분 우선 클릭 -- 연쇄적으로 파핑되므로 최소횟수로 모든 칸을 확인하려면 0을 우선으로 클릭해야 함
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (bmap[i][j] == 0 && map[i][j] == '.') {
						bfs(i, j, map);
						cnt++;
					}
				}
			}

			// 클릭되지 않은 나머지 부분 갯수 세기 -- 0이 아니면 1클릭당 1칸만 확인할 수 있으므로 갯수를 세면 됨
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						cnt++;
					}
				}
			}

			sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb);
	}

	private static void bfs(int i, int j, char[][] map) {
		q = new LinkedList<>();

		q.offer(new int[] { i, j });
		map[i][j] = '*';

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];

			for (int d = 0; d < 8; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == '*')
					continue;

				if (bmap[ny][nx] == 0) 
					q.offer(new int[] { ny, nx });
				
				map[ny][nx] = '*';
			}
		}

		q.clear();
	}

}