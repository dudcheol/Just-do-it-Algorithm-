package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2292_벌집 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 1;
		int max = 1;
		while (N > max) {
			max += (cnt * 6);
			cnt++;
		}
		System.out.println(cnt);
	}

}
