package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _1600_말이되고픈원숭이 {

	private static int K;
	private static int W;
	private static int H;
	private static int[][] map;
	private static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	private static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	private static int[] hy = { -1, -2, -2, -1, 1, 2, 2, 1 }; // 말 움직임
	private static int[] hx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	private static boolean[][][] visited;

	private static class Monkey {
		int y;
		int x;
		int cnt;
		int move;

		public Monkey(int y, int x, int cnt, int move) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.move = move;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Monkey> q = new LinkedList<>();

		q.offer(new Monkey(0, 0, K, 0));

		while (!q.isEmpty()) {
			Monkey m = q.poll();
			int y = m.y;
			int x = m.x;
			int cnt = m.cnt;
			int move = m.move;

			if (y == H - 1 && x == W - 1) {
				System.out.println(move);
				return;
			}

			if (cnt > 0) {
				for (int d = 0; d < 8; d++) {
					int ny = y + hy[d];
					int nx = x + hx[d];
					if (isOut(ny, nx, cnt - 1))
						continue;
					visited[ny][nx][cnt - 1] = true;
					q.offer(new Monkey(ny, nx, cnt - 1, move + 1));
				}
			}

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (isOut(ny, nx, cnt))
					continue;
				visited[ny][nx][cnt] = true;
				q.offer(new Monkey(ny, nx, cnt, move + 1));
			}
		}

		System.out.println(-1);
	}

	private static boolean isOut(int ny, int nx, int k) {
		return ny < 0 || nx < 0 || ny >= H || nx >= W || visited[ny][nx][k] || map[ny][nx] == 1;
	}

}
