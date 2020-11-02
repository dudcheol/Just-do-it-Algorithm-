package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _5644_무선충전 {

	static int M, A, moveA[], moveB[], AP[][];
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int ax, ay;
	static int bx, by;
	private static boolean[] asel;
	private static boolean[] bsel;
	private static int acnt;
	private static int bcnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			moveA = new int[M];
			moveB = new int[M];
			AP = new int[A][4];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 4; j++) {
					AP[i][j] = Integer.parseInt(st.nextToken()); // x,y,충전범위c,처리량p
				}
			}

			sb.append('#').append(test_case).append(' ').append(simulate()).append('\n');
		}

		System.out.print(sb);
	}

	private static int simulate() {

		ax = ay = 1;
		bx = by = 10;
		asel = new boolean[A];
		bsel = new boolean[A];

		int sum = 0;
		for (int time = 0; time <= M; time++) {

			// a가 충전 가능한 것들 찾기
			acnt = 0;
			for (int i = 0; i < A; i++) {
				int dist = Math.abs(ax - AP[i][0]) + Math.abs(ay - AP[i][1]);
				if (dist <= AP[i][2]) {
					asel[i] = true;
					acnt++;
				} else {
					asel[i] = false;
				}
			}

			// b가 충전 가능한 것들 찾기
			bcnt = 0;
			for (int i = 0; i < A; i++) {
				int dist = Math.abs(bx - AP[i][0]) + Math.abs(by - AP[i][1]);
				if (dist <= AP[i][2]) {
					bsel[i] = true;
					bcnt++;
				} else {
					bsel[i] = false;
				}
			}

			int max = 0;
			// 충전 가능한 것들 중 각자가 어느것을 선택하는 것이 좋은지 판단하여 최대값 찾기
			if (acnt == 0 && bcnt == 0) {

			} else if (acnt == 0) {
				for (int i = 0; i < A; i++) {
					if (bsel[i]) {
						max = Math.max(max, AP[i][3]);
					}
				}
			} else if (bcnt == 0) {
				for (int i = 0; i < A; i++) {
					if (asel[i]) {
						max = Math.max(max, AP[i][3]);
					}
				}
			} else {
				for (int i = 0; i < A; i++) {
					if (!asel[i])
						continue;
					for (int j = 0; j < A; j++) {
						if (bsel[j]) {
							if (i == j) {
								max = Math.max(max, AP[i][3]);
							} else {
								max = Math.max(max, AP[i][3] + AP[j][3]);
							}
						}
					}
				}
			}

			sum += max;

			// 사람이동시키기
			if (time >= M)
				continue;
			ax = ax + dx[moveA[time]];
			ay = ay + dy[moveA[time]];
			bx = bx + dx[moveB[time]];
			by = by + dy[moveB[time]];
		}

		return sum;

	}

}
