package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1261_알고스팟 {
	static int N;
	static int M;
	static char[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] brokens;

	static class Point implements Comparable<Point> {
		int y;
		int x;
		int broken;

		public Point(int y, int x, int broken) {
			this.y = y;
			this.x = x;
			this.broken = broken;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.broken, o.broken);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		brokens = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(brokens[i], Integer.MAX_VALUE);
		}

		bfs();
		System.out.println(brokens[N - 1][M - 1]);
	}

	private static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.offer(new Point(0, 0, map[0][0] - '0'));
		brokens[0][0] = map[0][0] - '0';

		while (!pq.isEmpty()) {

			Point cur = pq.poll();
			int y = cur.y;
			int x = cur.x;
			int cost = cur.broken;

			if (brokens[y][x] < cost)
				continue;

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (brokens[ny][nx] > brokens[y][x] + (map[ny][nx] - '0')) {
					brokens[ny][nx] = brokens[y][x] + (map[ny][nx] - '0');
					pq.offer(new Point(ny, nx, brokens[ny][nx]));
				}
			}

		}

	}

}
