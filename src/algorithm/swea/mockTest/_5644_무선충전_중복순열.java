package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _5644_무선충전_중복순열 {

	static int M, A, moveA[], moveB[], AP[][];
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int ax, ay;
	static int bx, by;

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
		int answer = 0;
		for (int time = 0; time <= M; time++) {

			// A <= 8 이므로
			// 모든 충전 가능한 경우를 생각해도 시간에 무리가 없다
			int max = 0;
			for (int a = 0; a < A; a++) { // 플레이어 A의 충전소
				for (int b = 0; b < A; b++) { // 플레이어 B의 충전소
					int sum = 0;
					int amountA = Math.abs(AP[a][0] - ax) + Math.abs(AP[a][1] - ay) <= AP[a][2] ? AP[a][3] : 0;
					int amountB = Math.abs(AP[b][0] - bx) + Math.abs(AP[b][1] - by) <= AP[b][2] ? AP[b][3] : 0;
					if(a != b) sum = amountA + amountB;
					else 	   sum = Math.max(amountA, amountB);
					
					if(max < sum) max = sum;
				}
			}
			
			answer += max;

			// 사람이동시키기
			if (time >= M)
				continue;
			ax = ax + dx[ moveA[time] ];
			ay = ay + dy[ moveA[time] ];
			bx = bx + dx[ moveB[time] ];
			by = by + dy[ moveB[time] ];
		}

		return answer;

	}

}
