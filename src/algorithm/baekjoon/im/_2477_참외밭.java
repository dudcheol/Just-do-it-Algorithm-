package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2477_참외밭 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());

		int[] sizes = new int[6];
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());
			sizes[i] = size;
		}
		
		int max1 = Math.max(sizes[0],Math.max(sizes[2], sizes[4]));
		int max2 = Math.max(sizes[1],Math.max(sizes[3], sizes[5]));
		int maxMul = max1 * max2;

		int mul1 = sizes[0] * sizes[1];
		int mul2 = sizes[3] * sizes[4];
		
		int res = 0;
		if(mul1 == maxMul || mul2 == maxMul) {
			res = Math.abs(mul1 - mul2);
		} else {
			res = Math.abs(mul1 + mul2);
		}
		
		System.out.println(res * k);
	}
}