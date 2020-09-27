package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _9205_맥주마시면서걸어가기 {
	static int N;
	static Point[] points;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int test_case = 0; test_case < t; test_case++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			Point[] points = new Point[N + 2];
			adjList = new ArrayList[N + 2];
			visited = new boolean[N + 2];

			for (int k = 0; k < N + 2; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				points[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				adjList[k] = new ArrayList<>();
			}
			// 50m 에 한 병 씩 마심
			// 20개가 최대임
			// 중간에 편의점 들릴 수 있음

			// 정점을 검사하면서, 연결가능한 것들을 연결
			for (int i = 0; i < N + 1; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					int dist = Math.abs(points[i].x - points[j].x) + Math.abs(points[i].y - points[j].y);
					if (dist <= 1000) {
						adjList[i].add(j);
						adjList[j].add(i);
					}
				}
			}

			sb.append(bfs() ? "happy" : "sad").append('\n');

		}
		System.out.println(sb);
	}

	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);

		while (!q.isEmpty()) {
			int polled = q.poll();

			if (polled == N + 1) {
				return true;
			}

			ArrayList<Integer> conn = adjList[polled];
			for (int i = 0; i < conn.size(); i++) {
				int target = conn.get(i);
				if (!visited[target]) {
					q.offer(target);
					visited[target] = true;
				}
			}
		}

		return false;
	}

}
