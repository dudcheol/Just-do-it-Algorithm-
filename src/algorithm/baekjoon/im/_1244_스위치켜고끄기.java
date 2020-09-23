package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1244_스위치켜고끄기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] lights = new int[n + 1];
		int[] onoff = { 1, 0 };

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			lights[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());

			if (gender == 1) { // 남
				for (int j = 0; j <= n; j += number) {
					lights[j] = onoff[lights[j]];
				}
			} else { // 여
				int idx = 1;
				lights[number] = onoff[lights[number]];
				while (true) {
					int left = number - idx;
					int right = number + idx;
					if (left <= 0 || right > n)
						break;
					if (lights[left] == lights[right]) {
						lights[left] = onoff[lights[left]];
						lights[right] = onoff[lights[right]];
					} else
						break;
					
					idx++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(lights[i]).append(' ');
			if (i % 20 == 0)
				sb.append('\n');
		}
		System.out.println(sb);
	}

}
