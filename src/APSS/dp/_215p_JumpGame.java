package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 215p - 외발 뛰기
 * 시작~끝: 12:30~13:20
 * 걸린시간: 50분
 * - 맞았다! -
 *	[간단한 해법]
	DP, 메모이제이션을 사용한다.
	
 *	[어떤  방식으로 접근했나?]
	1. 기저를 구한다
	2. 캐시 배열을 초기화 한다 (초기화값으로 뭘 넣을지 고민이 필요함)
	3. 캐시 배열의 내용을 확인하고 정보가 저장되어 있으면 해당 정보를 사용하고 저장되어있지 않다면 직접 계산하여 캐시를 채워넣는다
	
 *	[해법을 찾는 데 결정적인 깨달음]
	cache[y][x] = jump(y, x + board[y][x]) || jump(y + board[y][x], x) ? 1 : 0;
	왼쪽 jump함수와 오른쪽 jump함수 둘 중 하나라도 true이면 cache배열에 1을 넣는 방법을 사용한다.
	이 방법을 사용하기 전에는 
	cache[y][x] = jump(y, x + board[y][x]) ? 1 : 0; ... ㄱ
	cache[y][x] = jump(y + board[y][x], x) ? 1 : 0; ... ㄴ
	이렇게 풀었는데, 이렇게되면 'ㄴ'에서는 true가 나와서 1이 저장되어야 함에도 불구하고 이미 'ㄱ'에서 false가 나와버려서 cache에 0을 저장한다.
	따라서 'ㄴ'에서 값을 넣으려할 때, cache에 저장된 정보가 -1이 아니므로 계산조차 하지 않고 해당하는 좌표를 false라고 처리한다. 
	
 *	[아쉬운점이 있다면?]
	 답을 알고 풀었음에도 50분이 걸렸다. 원인은 "디버깅시간" -> 완전한 틀을 먼저 그리고 코드를 시작하는 것이 매우 중요하다.
 */

import java.util.*;
import java.io.*;

public class _215p_JumpGame {
	static int n;
	static int[][] board;
	static int[][] cache;

	static int jump(int y, int x) {
		// 기저 : 입력값이 범위를 벗어남
		if (y >= n || x >= n) {
			return 0;
		}
		// 기저 : 입력값이 '끝'에 도착
		if (y == n - 1 && x == n - 1) {
			return 1;
		}

		// 메모이제이션 사용
		if (cache[y][x] != -1) // 캐시에 있는 값이 -1 아니면 저장된 정보가 있는 것이므로 그 정보 사용
			return cache[y][x];

		// 캐시에 있는 값이 -1이면 저장된 정보가 없는 것이므로 직접 계산 후 캐시에 저장
		cache[y][x] = (jump(y, x + board[y][x]) == 1 ? true : false || jump(y + board[y][x], x) == 1 ? true : false) ? 1
				: 0;

		return cache[y][x];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < C; tc++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			cache = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				// 캐시 초기화
				Arrays.fill(cache[i], -1);
			}
			System.out.println(jump(0, 0) == 1 ? "YES" : "NO");
		}
	}
}
