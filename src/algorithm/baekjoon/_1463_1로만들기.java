package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1463_1로만들기 {
	static int N;
	static int[] cache;

	public static void main(String[] args) throws Exception {
		// X가 3으로 나누어 떨어지면 3으로 나눔
		// X가 2로 나누어 떨어지면 2로 나눔
		// 1을 뺌
		// 연산을 사용하는 횟수의 최솟값은?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cache = new int[1000001];
		Arrays.fill(cache, -1);

		System.out.println(func(N));
	}

	private static int func(int n) {
		if (n == 1) {
			return 0;
		}

		if (cache[n] != -1) {
			return cache[n];
		}

		int ret1 = Integer.MAX_VALUE;
		int ret2 = Integer.MAX_VALUE;
		int ret3 = Integer.MAX_VALUE;

		if (n % 3 == 0) {
			ret1 = func(n / 3) + 1;
		}

		if (n % 2 == 0) {
			ret2 = func(n / 2) + 1;
		}

		ret3 = func(n - 1) + 1;
		
		return cache[n] = Math.min(ret1, Math.min(ret2, ret3));
	}

}
