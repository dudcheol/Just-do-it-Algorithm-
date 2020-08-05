package com.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _2667_Numbering {
	static int n;
	static int[][] map;
	static int number;
	static ArrayList<Integer> list;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };

	static int dfs(int y, int x) {
		int ret = 0;

		map[y][x] = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] != 1)
				continue;

			ret += dfs(ny, nx) + 1;
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		number = 2;

		for (int i = 0; i < n; i++) {
			char[] inputs = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = inputs[j] - '0';
			}
		}

		list = new ArrayList<>();
		// dfs로 순회하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					list.add(dfs(i, j) + 1);
				}
			}
		}

		int size = list.size();
		sb.append(size).append("\n");

		// 리스트 오름차순 정렬
		Collections.sort(list);
		for (int i = 0; i < size; i++) {
			sb.append(list.get(i)).append("\n");
		}

		System.out.print(sb);
	}

}
