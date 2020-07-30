package algorithm.APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 226p - 삼각형 위의 최대  경로
 * 걸린시간: 완전탐색구현 30분도 안걸림, dp적용 실패ㅠㅠ
 * - 틀렸다! -
 *	[오답 원인]
	어떤 값을 Memoization해야 하는지에 대한 방향은 잘 잡았으나 초기에 재귀를 만들 때 설정한 입력값들에 얽매여 다른 입력값을 사용할 생각을 하지 못함

 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?]
 	dp를 사용할 땐 "입력정보"가 어떤 역할을 하는지에 대한 이해가 중요하다고 생각됨
 	-> 지금까지의 선택과 상관없을 경우, 각 부분문제의 최적이 되는 것만 골라서 풀기만 하면 전체 문제의 해도 구할 수 있음
	재귀의 성질을 생각해보자. 트리의 맨 아래에 있는 노드들부터 계산해가면서 최대가 되는 값을 캐시에 저장하면 결국 최상단 노드에는 최대값이 저장된다
 */

import java.util.*;
import java.io.*;

public class _226p_TrianglePath {
	static int n;
	static int[][] tri;
	static int answer;
	static int[][] cache;

	static int findPath(int k, int y, int x) {
		if (k == n-1)
			return tri[y][x];

		// 메모이제이션
		if (cache[y][x] != -1)
			return cache[y][x];

		return cache[y][x] = tri[y][x] + Math.max(findPath(k + 1, y + 1, x), findPath(k + 1, y + 1, x + 1));
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			answer = Integer.MIN_VALUE;
			n = Integer.parseInt(br.readLine());
			cache = new int[n][n];
			tri = new int[n][n];
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(cache[j], -1);
				for (int i = 0; i <= j; i++)
					tri[j][i] = Integer.parseInt(st.nextToken());
			}

			System.out.println(findPath(0, 0, 0));
		}
	}
}
