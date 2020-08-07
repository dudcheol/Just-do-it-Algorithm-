package com.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _17135_CatleDefense {
	static int n, m, d;
	static int map[][];
	static int[] pos;
	static int answer;

	static void selectPosition(int k, int idx) {
		if (k == 3) { // 3명의 궁수 배치
			// 배치된 궁수의 위치를 기준으로 시뮬레이션 시작
			simulate();
			return;
		}

		for (int i = idx; i < m; i++) {
			pos[k] = i;
			selectPosition(k + 1, i + 1);
		}
	}

	static void simulate() {
		// deepCopy
		int[][] tmp = deepCopyMap();

		int killCnt = 0;

		boolean[] founded = new boolean[3]; // 궁수별 적 죽임 표시

		while (true) {
			// 적이 없으면 종료
			if (isFinish(tmp))
				break;

			// 사거리가 i 일때 적 찾기
			for (int i = 1; i <= d; i++) {

				// 궁수별로 구하기
				for (int p = 0; p < 3; p++) {

					// 이미 적을 발견한 궁수라면 건너뛴다
					if (founded[p])
						continue;

					// 가장 왼쪽에 있는 적부터 죽이므로 0부터 시작
					// 사거리 안에 들면서 가장 처음 발견되는 적을 찾는다
					find: for (int c = 0; c < m; c++) {
						for (int j = n - 1; j >= n - i; j--) {
							int y = n - j;
							int x = Math.abs(c - pos[p]);
							int range = y + x;

							if (j < 0 || c < 0 || j >= n || c >= m)
								continue;

							if (range == i && tmp[j][c] != 0) {
								// p번째 궁수가 적을 발견했다고 표시한다
								founded[p] = true;
								// 적을 죽였으면 카운트하고 해당 궁수는 더이상 시뮬레이션하지 않는다
								// 그리고 3으로 바꿈
								if (tmp[j][c] == 3)
									break find;
								tmp[j][c] = 3;
								killCnt++;
								break find;
							}
						}
					}
				}
			}

			// 적 아래로 한 칸 이동시킴. 이동시키다가 3만나면 0으로 변경
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < m; j++) {
					if (tmp[i][j] == 3)
						tmp[i][j] = 0;
				}
				tmp[i + 1] = tmp[i].clone();
			}

			// 맨 위는 0으로 초기화
			Arrays.fill(tmp[0], 0);
			Arrays.fill(founded, false);
		}
		answer = Math.max(answer, killCnt);
	}

	static int[][] deepCopyMap() {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			tmp[i] = map[i].clone();
		}
		return tmp;
	}

	static boolean isFinish(int[][] tmp) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmp[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 행
		m = Integer.parseInt(st.nextToken()); // 열
		d = Integer.parseInt(st.nextToken()); // 궁수공격거리제한

		map = new int[n][m];
		pos = new int[3];
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		selectPosition(0, 0);
		System.out.println(answer);
	}
}
