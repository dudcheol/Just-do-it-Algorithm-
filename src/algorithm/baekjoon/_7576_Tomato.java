package com.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7576_Tomato {
	static int m, n;
	static int[][] box;
	static int[] dy = { 0, 0, -1, 1 }; // 왼오앞뒤
	static int[] dx = { -1, 1, 0, 0 };
	static int day = 0;
	static Queue<int[]> queue;
	static int tomatoTotal;
	static int current;

	static void simulate() {

		while (!queue.isEmpty()) {

			int size = queue.size();

			// 익은 토마토 시뮬레이션
			for (int k = 0; k < size; k++) {
				int[] polled = queue.poll();
				int i = polled[0];
				int j = polled[1];

				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if (ny < 0 || nx < 0 || ny >= n || nx >= m || box[ny][nx] != 0) {
						continue;
					}
					box[ny][nx] = 1;
					queue.add(new int[] { ny, nx });
					current++;
				}
			}

			day++; // 하루가 지남

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		box = new int[n][m];
		day = 0;
		queue = new LinkedList<>();
		current = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					queue.add(new int[] { i, j });
					current++;
				}
				if (box[i][j] != -1) {
					tomatoTotal++;
				}
			}
		}

		simulate();

		// 모든 토마토가 익었는지 확인
		if (current == tomatoTotal)
			System.out.println(day - 1);
		else
			System.out.println(-1);
	}
}
