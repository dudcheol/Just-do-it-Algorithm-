package com.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4963_NumberOfIsland {
	static int w, h;
	static int[][] map;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상부터 시계방향
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static void dfs(int y, int x) {
		for (int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || nx < 0 || ny >= h || nx >= w || map[ny][nx] != 1) {
				continue;
			}

			map[ny][nx] = 0;
			dfs(ny, nx);
		}
		return;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) {
				break;
			}

			map = new int[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb);
	}

}
