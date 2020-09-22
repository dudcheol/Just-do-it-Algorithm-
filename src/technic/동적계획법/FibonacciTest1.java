package technic.동적계획법;

import java.util.Scanner;

public class FibonacciTest1 {
	private static long[] call1, call2, memo;
	private static long totalCnt1, totalCnt2;

	// 비 메모 버전
	public static long fibo(int n) {
		++call1[n];
		++totalCnt1;
		if (n < 2)
			return n;
		return fibo(n - 1) + fibo(n - 2);
	}

	// 메모 버전
	public static long fibo2(int n) {
		++call2[n];
		++totalCnt2;
		if (n >= 2 && memo[n] == 0) { // 2항 이상이며 메모가 안되어 있을 경우는 계산하여 메모하기
			memo[n] = fibo2(n - 1) + fibo2(n - 2);
		}
		return memo[n];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		call1 = new long[N + 1];
		call2 = new long[N + 1];
		memo = new long[N + 1];
		memo[0] = 0;
		memo[1] = 1;

		System.out.println(fibo2(N));
		for (int i = 0; i <= N; i++) {
			System.out.println("fibo2(" + i + ") : " + call2[i]);
		}
		System.out.println("메모버전 수행횟수 : " + totalCnt2);
		
		System.out.println("-------------------------------");

		System.out.println(fibo(N));
		for (int i = 0; i <= N; i++) {
			System.out.println("fibo(" + i + ") : " + call1[i]);
		}
		System.out.println("비 메모버전 수행횟수 : " + totalCnt1);
	}

}
