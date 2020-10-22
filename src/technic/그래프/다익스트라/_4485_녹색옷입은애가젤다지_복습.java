package technic.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _4485_녹색옷입은애가젤다지_복습 {

	private static int N;
	private static int[][] map;
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[][] dist;
	private static int test_case;

	private static class Point implements Comparable<Point> {
		int y;
		int x;
		int cost;

		public Point(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			dist = new int[N][N];
//			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}

			dist[0][0] = map[0][0]; // 처음위치에서 드는 비용
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.offer(new Point(0, 0, map[0][0]));

			while (!pq.isEmpty()) {
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(dist[i][j]+" ");
//					}
//					System.out.println();
//				}
				System.out.println("------------");
				Point polled = pq.poll();
				int y = polled.y;
				int x = polled.x;
				int cost = polled.cost;

				if (y == N - 1 && x == N - 1)
					break;

				if (dist[y][x] < cost) {
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N)
						continue;

					if (dist[ny][nx] > cost + map[ny][nx]) {
						dist[ny][nx] = cost + map[ny][nx];
						pq.offer(new Point(ny, nx, dist[ny][nx]));
					}
				}
			}

			sb.append("Problem ").append(++test_case).append(": ").append(dist[N - 1][N - 1]).append('\n');
		}

		System.out.print(sb);
	}

}
