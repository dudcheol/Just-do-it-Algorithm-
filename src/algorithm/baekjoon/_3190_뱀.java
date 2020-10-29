package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _3190_뱀 {

	private static final int APPLE = 5;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object obj) {
			return this.y == ((Point) obj).y && this.x == ((Point) obj).x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K, map[][], L, time[], cmd[];
		int[] dy = { -1, 0, 1, 0 }; // 상우하좌
		int[] dx = { 0, 1, 0, -1 };

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
		}
		L = Integer.parseInt(br.readLine());
		time = new int[L];
		cmd = new int[L];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			cmd[i] = st.nextToken().charAt(0) == 'D' ? 1 : 3;
		}

		LinkedList<Point> snake = new LinkedList<Point>();

		int timer = 0;
		int head = 1;
		int cmdIdx = 0;
		snake.offerFirst(new Point(0, 0));
		while (true) {
			timer++;
			
			Point peek = snake.peekFirst();
			int ny = peek.y + dy[head];
			int nx = peek.x + dx[head];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || snake.contains(new Point(ny, nx)))
				break;

			snake.offerFirst(new Point(ny, nx));

			if (map[ny][nx] == APPLE)
				map[ny][nx] = 0;
			else
				snake.pollLast();

			if (cmdIdx < L && time[cmdIdx] == timer) {
				head = (head + cmd[cmdIdx]) % 4;
				cmdIdx++;
			}
		}

		System.out.println(timer);
	}

}
