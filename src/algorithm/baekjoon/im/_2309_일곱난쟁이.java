package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2309_일곱난쟁이 {
	static int[] selected;
	static int[] n;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = new int[9];
		selected = new int[7];

		int sum = 0;
		for (int i = 0; i < n.length; i++) {
			n[i] = Integer.parseInt(br.readLine());
			sum += n[i];
		}

		// 9개 중 7개 순서상관없이 선택
		comb(0, 0);
		System.out.println(sb);
	}

	private static void comb(int k, int idx) {
		if (k == 7) {
			int sum = 0;
			for (int i = 0; i < selected.length; i++) {
				sum += n[selected[i]];
			}

			if (sum == 100 && sb.length() == 0) {
				int[] ret = new int[7];
				for (int i = 0; i < ret.length; i++) {
					ret[i] = n[selected[i]];
				}
				Arrays.sort(ret);
				for (int i = 0; i < ret.length; i++) {
					sb.append(ret[i]).append('\n');
				}
			}
			return;
		}

		for (int i = idx; i < 9; i++) {
			selected[k] = i;
			comb(k + 1, i + 1);
		}

	}

}
