package com.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2468_SafeArea {
	static int n;
	static int[][] map;
	static int maxHeight;
	static int answer;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] safeArea;

	static void dfs(int y, int x) {
		// 방문표시
		safeArea[y][x] = false;

		// 현재 위치에서 인접한 곳 방문하여 false로 바꾸자
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= n || nx >= n || !safeArea[ny][nx]) {
				continue;
			}

			dfs(ny, nx);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		safeArea = new boolean[n][n];
		maxHeight = Integer.MIN_VALUE;
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}

		// 아무 지역도 물에 잠기지 않을 수도 있다.
		for (int h = 0; h < maxHeight; h++) {
			// h : 비가 차오른 높이
			// 건물이 h 보다 높으면 true, 작거나 같으면 false로 safeArea에 표시
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					safeArea[i][j] = map[i][j] > h;
				}
			}

			// safeArea로 안전영역의 수 구하기
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (safeArea[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			answer = Math.max(answer, cnt);
		}

		System.out.println(answer);
	}

}
