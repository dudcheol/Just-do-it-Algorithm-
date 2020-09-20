package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2563_색종이 {

	public static void main(String[] args) throws Exception {
		boolean[][] map = new boolean[100][100];// 가로세로 크기 100 정사각형 도화지

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 가로세로 크기 10 정사각형 색종이 도와지와 평행하게 붙임
		for (int k = 0; k < n; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[x + i][y + j] = true;
				}
			}
		}

		// 색종이가 붙은 영역의 크기는?
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j])
					cnt++;
			}
		}

		System.out.println(cnt);
	}

}
