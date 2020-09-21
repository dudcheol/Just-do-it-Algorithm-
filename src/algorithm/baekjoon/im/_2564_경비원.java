package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2564_경비원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] opposite = { 0, 2, 1, 4, 3 };
		int answer = 0;

		int[] clockDir = { 3, 2, 0, 1 }; // 북->동, 남->서, 서->북, 동->남
		int[] clockDirY = { 0, 0, 1, -1 }; // 북-> 오른쪽, 남-> 왼쪽, 서-> 위, 동-> 아래
		int[] clockDirX = { 1, -1, 0, 0 }; // 북-> 오른쪽, 남-> 왼쪽, 서-> 위, 동-> 아래

		int[] clockReverseDir = { 2, 3, 1, 0 }; // 북->서, 남->동, 서->남, 동->북
		int[] clockReverseDirY = { 0, 0, -1, 1 }; // 북-> 왼, 남-> 오른, 서-> 아래, 동-> 위
		int[] clockReverseDirX = { -1, 1, 0, 0 }; // 북-> 왼, 남-> 오른, 서-> 아래, 동-> 위

		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());

		int[][] store = new int[n + 1][3];

		for (int i = 0; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][2] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
			switch (store[i][2]) {
			case 1: // 북 (위)
				store[i][0] = sero;
				break;
			case 2: // 남 (아래)
				store[i][0] = 0;
				break;
			case 3: // 서 (왼)
				store[i][0] = store[i][2];
				store[i][1] = 0;
				break;
			case 4: // 동 (오른)
				store[i][0] = store[i][2];
				store[i][1] = garo;
				break;
			}
		}

		// n번째는 동근
		int dongY = store[n][0];
		int dongX = store[n][1];
		int dongDir = store[n][2] - 1;

		for (int i = 0; i < n; i++) {
			int storeY = store[i][0];
			int storeX = store[i][1];
			int storeDir = store[i][2];
			int dist = 0;

			// 동근이 출발 시뮬레이션
			// 시계방향
			int ny = dongY;
			int nx = dongX;
			int ndir = dongDir;
			int dist1 = 0;
			while (!(ny == storeY && nx == storeX)) {
				ny += clockDirY[ndir];
				nx += clockDirX[ndir];
				if (ny < 0 || nx < 0 || ny > sero || nx > garo) {
					ny -= clockDirY[ndir];
					nx -= clockDirX[ndir];
					ndir = clockDir[ndir];
				} else {
					dist1++;
				}
			}
			// 반시계방향
			ny = dongY;
			nx = dongX;
			ndir = dongDir;
			int dist2 = 0;
			while (!(ny == storeY && nx == storeX)) {
				ny += clockReverseDirY[ndir];
				nx += clockReverseDirX[ndir];
				if (ny < 0 || nx < 0 || ny > sero || nx > garo) {
					ny -= clockReverseDirY[ndir];
					nx -= clockReverseDirX[ndir];
					ndir = clockReverseDir[ndir];
				} else {
					dist2++;
				}
			}

			dist = Math.min(dist1, dist2);
			answer += dist;
		}

		System.out.println(answer);
	}

}
