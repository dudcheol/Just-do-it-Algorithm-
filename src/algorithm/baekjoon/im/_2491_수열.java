package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2491_수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int pre = nums[0];
		int biggerCnt = 1;
		int smallerCnt = 1;
		int bigMAX = 1;
		int smallMAX = 1;
		for (int i = 1; i < N; i++) {
			if (nums[i] >= pre) {
				biggerCnt++;
			} else {
				biggerCnt = 1;
			}

			if (nums[i] <= pre) {
				smallerCnt++;
			} else {
				smallerCnt = 1;
			}
			
			bigMAX = Math.max(bigMAX, biggerCnt);
			smallMAX = Math.max(smallMAX, smallerCnt);
			pre = nums[i];
		}

		System.out.println(Math.max(bigMAX, smallMAX));
	}

}
