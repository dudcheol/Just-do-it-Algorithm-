package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5607_조합 {
	static long[] factorial;
	static final long P = 1234567891;

	static long nCr(int n, int r, long p) {
		if (r == 0)
			return 1L;

		return (
				factorial[n] * power(factorial[r], p-2, p)
				% p * power(factorial[n-r], p-2, p)
				% p
				) % p;
	}

	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p; // ex) 3^7 > 7 3 1		3^7 --> 3^1 * 3^2 * 3^4

		while (y > 0) {
			if (y % 2 == 1) {
				res = (res * x) % p;
			}
			y = y >> 1; // y = y/2
			x = (x * x) % p;
		}

		return res;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		factorial = new long[1000001];
		factorial[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			factorial[i] = factorial[i - 1] * i % P;
		}

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			sb.append('#').append(test_case).append(' ').append(nCr(n, r, P)).append('\n');
		}
		
		System.out.print(sb);
	}

}
