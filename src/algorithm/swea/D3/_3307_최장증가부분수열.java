package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3307_최장증가부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long[] arr = new long[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}

			int max = 1;
			int[] DP = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				DP[i] = 1;
				for (int j = 1; j <= i - 1; j++) {
					if (arr[i] > arr[j]) {
						DP[i] = Math.max(DP[i], DP[j] + 1);
					}
				}
				max = Math.max(max, DP[i]);
			}

			sb.append('#').append(test_case).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

}
