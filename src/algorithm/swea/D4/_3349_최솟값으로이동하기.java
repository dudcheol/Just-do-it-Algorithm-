package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _3349_최솟값으로이동하기 {
	static int W, H, N;
	static int[][] cost;
	static ArrayList<Point> checkPoint;
	static int[] dy = { -1, 1, 0, 0, -1, 1 }; // 상,하,좌,우,좌상,우하
	static int[] dx = { 0, 0, -1, 1, -1, 1 }; // 상,하,좌,우,좌상,우하
	static PriorityQueue<Point> pq;

	static class Point implements Comparable<Point> {
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
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			cost = new int[W][H];

			checkPoint = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// 반드시 들러야 하는 곳 입력받기
				checkPoint
						.add(new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1));
			}

			initCost();

			pq = new PriorityQueue<Point>();
			int startY = 0;
			int startX = 0;
			int min = 0;
			for (int i = 0; i < checkPoint.size(); i++) {
				Point cur = checkPoint.get(i);
				int destY = cur.y;
				int destX = cur.x;

				pq.offer(new Point(startY, startX, 0));
				cost[startY][startX] = 0; // 도로간 이동 비용1
				bfs(destY, destX);
				min += cost[destY][destX];
				startY = destY; // 해당 도로에서 일을 마쳤으면 다음번엔 도착점이 출발점이됨
				startX = destX;
				initCost();
			}
			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
		// W개 남북방향 도로 . 서->동 1,2,3,...W 열
		// H개 동서방향 도로 . 남->북 1,2,3,...H 행
		// 북동방향 도로
		// N개 교차로를 순서대로 이동 i번째 이동 -> xi,yi
		// 교차로 한번 이동 1 비용
		// xn,yn 까지 가는 비용 최소비용찾기
		// W, H, N(2 ≤ W, H ≤ 10,000, 1 ≤ N ≤ 1,000)
	}

	private static void initCost() {
		cost = new int[W][H];
		for (int i = 0; i < W; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
	}

	private static void bfs(int destY, int destX) {

		while (!pq.isEmpty()) {
			Point polled = pq.poll();
			int y = polled.y;
			int x = polled.x;
			int curCost = polled.cost;

			if (cost[y][x] < curCost)
				continue;

			if (y == destY && x == destX)
				return;

			for (int d = 0; d < 6; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || nx < 0 || ny >= W || nx >= H)
					continue;

				if (cost[ny][nx] > curCost + 1) {
					cost[ny][nx] = curCost + 1;
					pq.offer(new Point(ny, nx, cost[ny][nx]));
				}
			}
		}

	}

}
