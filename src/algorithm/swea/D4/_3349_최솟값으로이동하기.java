package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _3349_최솟값으로이동하기 {
	static int W, H, N;
	static ArrayList<Point> checkPoint;

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
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

			checkPoint = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// 반드시 들러야 하는 곳 입력받기
				checkPoint.add(new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
			}

			Point start = checkPoint.get(0);
			int startY = start.y;
			int startX = start.x;
			int min = 0;
			for (int i = 1; i < checkPoint.size(); i++) {
				Point next = checkPoint.get(i);
				int nextY = next.y;
				int nextX = next.x;

				// nextY, nextX 점을 기준으로 십자로 나눴을 때
				// 2,4사분면에 있는 점들은 시작점 - 도착점 했을 때 절댓값이 큰 x나 y좌표 중 하나
				// 1,3사분면에 있는 점들은 거리를 구하면 됨

				int res = 0;
				if ((startY > nextY && startX < nextX) || (startY < nextY && startX > nextX)) {// 1,3사분면인지 확인
					res = Math.abs(startY - nextY) + Math.abs(startX - nextX);
				} else { // 2,4분면 or 경계선
					res = Math.max(Math.abs(startY - nextY), Math.abs(startX - nextX));
				}
				min += res;
				startX = nextX;
				startY = nextY;
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
}
