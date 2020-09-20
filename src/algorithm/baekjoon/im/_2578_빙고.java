package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2578_빙고 {
	static int[][] pan;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		pan = new int[5][5];

		// 25개 판 빙고판 5x5
		// 1~25 자연수 한칸에 하나씩 씀
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				cnt++;
				int call = Integer.parseInt(st.nextToken());
				// 사회자가 부르는 수 차례로 지워나감

				if(simulate(call)) {
					System.out.println(cnt);
					return;
				}
			}
		}
	}

	private static boolean simulate(int call) {
		// 지워가다가 가로,세로,대각선 위의 5개의 모든 수가 지워지는 경우 줄에 선을 그음 (빙고)
		// 빙고가 3개면 빙고!라고 외침 < 먼저 외치는 사람이 승자

		// 사회자가 부른 숫자 찾기
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (pan[i][j] == call) {
					pan[i][j] = -1;
					break;
				}
			}
		}

		int bingo = 0;

		// 가로 빙고 찾기
		loop: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				if (pan[i][j] != -1)
					continue loop;

			if (++bingo == 3)
				return true;
		}

		// 세로 빙고 찾기
		loop: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				if (pan[j][i] != -1)
					continue loop;

			if (++bingo == 3)
				return true;
		}

		// 우하 대각선 빙고 찾기
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (pan[i][i] != -1) {
				break;
			}
			cnt++;
		}
		if (cnt == 5) {
			if (++bingo == 3) {
				return true;
			}
		}

		// 좌하 대각선 빙고 찾기
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (pan[i][4 - i] != -1) {
				break;
			}
			cnt++;
		}
		if (cnt == 5) {
			if (++bingo == 3) {
				return true;
			}
		}
		
		return false;
	}

}
