package baekjoon.DivideAndConquer;

/*
 * 백준 - 1074 - Z
 * 시작~끝: 11:30~
 * 시간:
 * 
 * 1차시도 12:25 메모리초과
 * 2차시도 12:32 시간초과
 * 
 */

import java.util.*;
import java.io.*;

public class _1074_Z {
	static int N, r, c;
	static int cnt = 0;
	/* 메모리 초과 */
//	static boolean[][] board;
	static boolean alreadyFind;

	static void visitZ(int n, int x, int y) {
		if (alreadyFind)
			return;
		if (n == 1) {
			if (x == r && y == c) {
				System.out.println(cnt);
				alreadyFind = true;
				return;
			}
			cnt++;
			if (x == r && y + 1 == c) {
				System.out.println(cnt);
				alreadyFind = true;
				return;
			}
			cnt++;
			if (x + 1 == r && y == c + 1) {
				System.out.println(cnt);
				alreadyFind = true;
				return;
			}
			cnt++;
			if (x + 1 == r && y + 1 == c) {
				System.out.println(cnt);
				alreadyFind = true;
				return;
			}
			cnt++;
		} else {
			if(!alreadyFind) visitZ(n - 1, x, x);
			if(!alreadyFind) visitZ(n - 1, x, y/2);
			if(!alreadyFind) visitZ(n - 1, y/2, x);
			if(!alreadyFind) visitZ(n - 1, y/2, y/2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);
		/* 메모리 초과 */
//		board = new boolean[size][size];
//		board[r][c] = true;

		visitZ(N, 0, size);
	}
}
