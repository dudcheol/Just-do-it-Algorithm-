package programmers.skillCheck;

import java.util.Arrays;

public class skill_test_level2_q2_try3 {
	static int solution(int[] arr) {
		int answer = 0;

		Arrays.sort(arr);
		int target = arr[arr.length - 1];

		boolean find = false;
		while (!find) {
			int cnt=0;
			for (int i = 0; i < arr.length; i++) {
				if (target % arr[i] != 0) {
					cnt++;
					break;
				}
			}
			if(cnt==0) find = true;
			else {
				target++;
				find = false;
			}
		}

		return target;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 6, 8, 14 };
		System.out.println(solution(arr));
	}
}
