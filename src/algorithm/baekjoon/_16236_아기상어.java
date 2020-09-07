package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _16236_아기상어 {
	static int N;
	static int[][] map;
	static int sharkY;
	static int sharkX;
	static int sharkSize;
	static PriorityQueue<Point> pq;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int eatCnt;
	static boolean[][] visited;
	static int answer;

	static class Point implements Comparable<Point> {
		int y;
		int x;
		int distance;

		public Point(int y, int x, int distance) {
			this.y = y;
			this.x = x;
			this.distance = distance;
		}

		@Override
		public int compareTo(Point o) {
			// shark와 가까운 순서로
			// 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다
			int timeRes = Integer.compare(this.distance, o.distance);
			if (timeRes == 0) {
				int res = Integer.compare(this.y, o.y);
				if (res == 0) {
					return Integer.compare(this.x, o.x);
				}
				return res;
			}
			return timeRes;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		eatCnt = 0;
		pq = new PriorityQueue<Point>();
		visited = new boolean[N][N];
		answer = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) { // 아기상어 위치
					sharkY = i;
					sharkX = j;
					sharkSize = 2;
					map[i][j] = 0; // 빈 칸으로 초기화
				}
			}
		}

		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
//		int timer = 0;
		visited[sharkY][sharkX] = true;
		pq.add(new Point(sharkY, sharkX, 0));

		while (!pq.isEmpty()) {
			Point polled = pq.poll();
			int y = polled.y;
			int x = polled.x;
			int time = polled.distance;

//			if (!checkEatable()) {// 더이상 먹을 수 있는 물고기가 없다면
//				return;
//			}

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] > sharkSize)
					continue;
				visited[ny][nx] = true;
				pq.add(new Point(ny, nx, time + 1));
			}

			if (pq.peek() != null) {
				Point peeked = pq.peek();
				int py = peeked.y;
				int px = peeked.x;
				int pTime = peeked.distance;

				if (map[py][px] != 0 && map[py][px] < sharkSize) { // 먹을 수 있음
					eatCnt++;// 먹고 먹은 횟수를 늘린다
					if (eatCnt == sharkSize) {// 먹은 횟수와 현재 상어의 크기가 같으면 상어크기를 1 증가시킨다
						sharkSize++;
						eatCnt = 0;// 먹은 횟수를 0으로 초기화 한다
					}
					map[py][px] = 0;// 먹었으므로 맵에서 0 으로 변경
					
					pq.clear();// 먹었으므로 q를 비우고 그 위치부터 다시 시작한다
					pq.add(new Point(py, px, 0));
					visited = new boolean[N][N];// visited도 초기화한다
					visited[py][px] = true; // 방문처리
					answer += pTime;
				}
			}
		}
	}

//	private static boolean checkEatable() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (map[i][j] != 0 && map[i][j] < sharkSize) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
}
