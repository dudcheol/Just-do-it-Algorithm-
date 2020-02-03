package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 159p - 게임판 덮기 
 * 시작 : 14:25 ~ 14:52분(pause) (15분경과) 15:07 다시시작 ... 틀림
 * 
 */

import java.util.*;
import java.io.*;

public class _159p_BoardCover {
	static int H, W;
	static char[][] board;
	static int[][] dx = { { 0, 0, 1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 1, 1, 0 } };
	static int[][] dy = { { 0, 1, 1 }, { 0, 0, 1 }, { 0, 0, 1 }, { 0, 1, 1 } };
	static List<int[]> memo = new ArrayList<int[]>();

	static int fillBoard(int h, int w) {
		if (w > W - 2 || h > H - 2)
			return 0;

		// 해당 행에 흰 칸이 있는지 조회
		int finish = -1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] == '.') {
					finish = j;
					break;
				}
			}
		}

		if (finish == -1)
			return 1;

		int ret = 0;
		for (int i = h; i <= H - 2; i++) {
			for (int j = w; j < W - 2; j++) {
				for (int k = 0; k < 3; k++) { // 블럭모양별로 넣어보기
					int cnt = 0;
					boolean blockOk = false;
					memo.clear();
					for (int l = 0; l < 3; l++) {
						int x = w + dx[k][l];
						int y = h + dy[k][l];
						if (board[y][x] == '.') {
							blockOk = true;
							memo.add(new int[] { y, x });
						} else {
							blockOk = false;
							break;
						}
					}
					if (blockOk) { // 블럭을 놓을 수 있으면 보드에 블럭을 놓음
						for (int[] m : memo) {
							board[m[0]][m[1]] = '*';
						}
						ret += fillBoard(i, j + 1);
						for (int[] m : memo) {
							board[m[0]][m[1]] = '.';
						}
					}
				}
//				return 0; // 모든 모양을 다 넣어봤는데 맞는게 없다면 더이상 볼 필요 없는 경우이므로 리턴.
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new char[H][W];
			for (int i = 0; i < H; i++) {
				board[i] = br.readLine().toCharArray();
			}

			System.out.println(fillBoard(0, 0));
		}
	}
}
