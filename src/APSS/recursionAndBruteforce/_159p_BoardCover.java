package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 159p - 게임판 덮기 
 * 시작 : 14:25 ~ 14:52분(pause) (15분경과) 15:07 다시시작 ... 틀림
 * 
 * 	[오답 원인]
 	1. 계획세우기 단계가 미흡했다.
 	2. 블록을 채워넣는 것에서 현재 인덱스를 기준으로 채울 수 있는 경우를 생각해야 하는데 그러지 못했다.
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
 	1. 재귀호출부가 복잡해지면 더 어려워진다. 따라서 필요한 구현을 함수화하는게 더 낫다는 점을 몰랐다.
 	2. 블록을 놓는 것 까지는 생각했지만 블록을 치우고 다른 블록을 놓는 것까지는 생각을 못했다 -> 재귀에 대한 이해가 부족
 	3. 재귀를 이용한 완전탐색에서 중복제거를 위해선 '순서'를 주는 것이 중요하다. 예를 들어, 가장 왼쪽부터, 가장 위쪽부터 라던지.. 등 
 */

import java.util.*;
import java.io.*;

public class _159p_BoardCover {
	static int H, W;
	static int[][] dx = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { -1, 0 } };
	static int[][] dy = { { 1, 1 }, { 0, 1 }, { 0, 1 }, { 1, 1 } };
	static List<int[]> memo = new ArrayList<int[]>();

//	static int fillBoard(int h, int w) {	/* '보드'자체를 매개변수로 사용해서 매 재귀마다 보드를  순회하여 발견되는 */
	static int fillBoard(char[][] board) { /* 흰 칸을 채워나가는 방식을 쓸 것이므로 현재 위치를 알려주는 매개변수는 필요없다. */
//		if (w > W - 2 || h > H - 2)			/* 여기서  나는 board를 매개변수로 쓰면 메모리 오버플로가 나지 않을까 생각했는데 */
//			return 0;						/* 객체를 생성하는게 아니라 하나의 배열을 참조하는 방식이므로 메모리 오버플로는 X */

		// 흰 칸이 있는 위치 찾기
		int w = -1, h = -1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] == '.') {
					h = i;
					w = j;
					break;
				}
			}
			if (h != -1)
				break;
		}

		if (h == -1) /* 전부 순회해서 '.'를 발견한 적이 없으므로 보드의 모든 칸이 채워진 상태이므로 방법의 수 1이 됨 */
			return 1;

		/* 보드를 채워가다가 흰 칸이 발견되면 해당 위치를 받아서 그 위치에 블록을 놓아본다. */
		int ret = 0;

		for (int i = 0; i < 4; i++) {
			if (putBlock(1, board, w, h, i)) {
				ret += fillBoard(board); /* 블록을 놓았는데 놓아졌다? -> 다음 흰 칸을 찾아서 또 블록을 놓아보자 */
				putBlock(2, board, w, h, i); /* 다음 블록도 놓아보아야 하는데 위에서 이미 놓아버렸으므로 치워주는 작업이 필요하다 */
			}
		}

		return ret;

		/*
		 * 오답: 무식하게 풀어보겠다는 발상은 좋았지만 아래 방법은 오히려 문제를 더 어렵게 만든다. 모든 칸에 4가지 모양의 블럭을 놓을 필요는
		 * 없다. 흰 칸에만 블럭을 놓아보는 시도로 문제를 풀 수 있기 때문이다.
		 */
//		int ret = 0;
//		for (int i = h; i <= H - 2; i++) {
//			for (int j = w; j < W - 2; j++) {
//				for (int k = 0; k < 3; k++) { // 블럭모양별로 넣어보기
//					int cnt = 0;
//					boolean blockOk = false;
//					memo.clear();
//					for (int l = 0; l < 3; l++) {
//						int x = w + dx[k][l];
//						int y = h + dy[k][l];
//						if (board[y][x] == '.') {
//							blockOk = true;
//							memo.add(new int[] { y, x });
//						} else {
//							blockOk = false;
//							break;
//						}
//					}
//					if (blockOk) { // 블럭을 놓을 수 있으면 보드에 블럭을 놓음
//						for (int[] m : memo) {
//							board[m[0]][m[1]] = '*';
//						}
//						ret += fillBoard(i, j + 1);
//						for (int[] m : memo) {
//							board[m[0]][m[1]] = '.';
//						}
//					}
//				}
////				return 0; // 모든 모양을 다 넣어봤는데 맞는게 없다면 더이상 볼 필요 없는 경우이므로 리턴.
//			}
//		}
//		return 0;
	}

	/*
	 * 보드의 특정 위치에 블록(4가지 타입 중 하나)이 놓아지는지를 확인하고 가능하다면 보드에 블록을 채워넣은 후 t/f를 반환하는 함수
	 */
	static boolean putBlock(int flag, char[][] board, int w, int h, int type) {
		int[] x = new int[3], y = new int[3];
		for (int i = 0; i < 2; i++) {
			x[i] = w + dx[type][i];
			y[i] = h + dy[type][i];
			if (x[i] < 0 || y[i] < 0 || x[i] > W - 1 || y[i] > H - 1 || board[y[i]][x[i]] == '#'
					|| board[y[i]][x[i]] == '*') {
				/* 블록을 놓을 자리가 보드를 벗어나거나 검은 칸, 그리고 이미 블록이 놓인 칸이라면 블록을 놓을 수 없으므로 false 리턴 */
				if (flag == 1) /* flag가 2일때는 이미 flag1에서 블록을 채운 이후이기 때문에 별도의 작업이 필요하다 */
					return false;
			}
		}

		x[2] = w;
		y[2] = h;
		for (int i = 0; i < 3; i++) {
			/* flag 1 : 블록 채워넣기 */
			if (flag == 1) {
				board[y[i]][x[i]] = '*';
			} else { /* flag 2 : 채웠던 블록 제거 */
				if (board[y[i]][x[i]] == '*')
					board[y[i]][x[i]] = '.';
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			char[][] board = new char[H][W];
			for (int i = 0; i < H; i++) {
				board[i] = br.readLine().toCharArray();
			}

			/*
			 * 우리가 사용할 수 있는 블록은 3칸으로 이루어져 있다. 이 블록으로 보드의 모든 흰 칸을 채워야 하므로 흰 칸의 갯수는 3의 배수여야
			 * 한다. 만족하지 않을 경우 블록으로 보드를 가득 채울 수 없기 때문에 방법의 수는 0이 된다.
			 */
			int white = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (board[i][j] == '.') {
						white++;
					}
				}
			}
			if (white % 3 != 0) {
				System.out.println(0);
			} else {
				System.out.println(fillBoard(board));
			}
		}
	}
}
