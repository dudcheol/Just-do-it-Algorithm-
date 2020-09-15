package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _16918_봄버맨 {
	static int R, C, N;
	static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int time = 0;
		char[][] tmp = new char[R][];
		N--; // 초기상태 1초 후
		while (N-- != 0) {
			switch (time++ % 2) {
			case 0:
				// 폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치
				for (int i = 0; i < R; i++) {
					tmp[i] = map[i].clone();
				}
				
				for (int i = 0; i < R; i++) {
					Arrays.fill(map[i], 'O');
				}
				break;
			case 1:
				// 3초 전에 설치된 폭탄 모두 폭발
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (tmp[i][j] == 'O') {
							map[i][j] = '.';
							for (int d = 0; d < 4; d++) {
								int ny = i + dy[d];
								int nx = j + dx[d];
								if (ny < 0 || nx < 0 || ny >= R || nx >= C)
									continue;
								map[ny][nx] = '.';
							}
						}
					}
				}
				break;
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
		
	}

}
