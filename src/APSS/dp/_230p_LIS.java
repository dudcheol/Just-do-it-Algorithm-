package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 230p - 최대 증가 부분 수열
 * 시작~끝: 09:40~ 1) 10:45
 * 걸린시간: 1) 1시간5분 - 오답(문제를 잘못이해함)
 * - 맞았다! -
 *	[간단한 해법]
	
 *	[어떤  방식으로 접근했나?]
	
 *	[해법을 찾는 데 결정적인 깨달음]
	
 *	[다른 해결 방법이 있다면?]
	
 * - 틀렸다! -
 *	[오답 원인]
	
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
	
 */

import java.util.*;
import java.io.*;

public class _230p_LIS {
	static int n;
	static int[] nums;
	static boolean[] cache;

	static int lis(int idx, int cnt) {
		if (idx == n - 1) {
			return cnt;
		}

		if (nums[idx] >= nums[idx + 1]) {
			return lis(idx + 1, 1);
		}
		
		if(cache[idx]) return 1;

		int ret = 0;
		for (int i = idx; i < n - 1; i++) {
			cache[idx] = true;
			ret = Math.max(ret, lis(idx + 1, cnt + 1));
		}

		return ret;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			n = Integer.parseInt(br.readLine());
			nums = new int[n];
			cache = new boolean[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			int answer = lis(0, 1);
			System.out.println(answer);
		}
	}
}
