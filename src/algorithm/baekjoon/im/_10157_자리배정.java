package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10157_자리배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(br.readLine());

		int[][] map = new int[c][r];

		int[] dy = { 0, 1, 0, -1 };// 우하좌상
		int[] dx = { 1, 0, -1, 0 };// 우하좌상

		int ny = 0;
		int nx = 0;
		int number = 1;
		int dir = 0;
		
		if(num > r*c) {
			System.out.println(0);
			return;
		}
		
		while (true) {
			map[ny][nx] = number;
			if (number == num) {
				System.out.println((ny + 1) + " " + (nx + 1));
				return;
			}

			ny = ny + dy[dir];
			nx = nx + dx[dir];
			if (ny < 0 || nx < 0 || ny >= c || nx >= r || map[ny][nx] != 0) {
				ny = ny - dy[dir];
				nx = nx - dx[dir];
				dir = (dir + 1) % 4;
			} else {
				number++;
			}
		}
	}

}
