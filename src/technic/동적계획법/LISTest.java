package technic.동적계획법;

import java.util.Scanner;

public class LISTest { // 최장 증가 수열 - DP풀이 - O(n^2)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N]; // 자신을 끝으로 하는 LIS 최장길이

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS[i] = 1; // 자신만으로 LIS 구성했을 때의 길이 1

			// 자신 앞에 있는 원소들과 비교
			for (int j = 0; j <= i - 1; j++) {
				if(arr[j] < arr[i]) { // 앞쪽 원소보다 자신이 큰 경우만 뒤에 세울 수 있음
					if(LIS[i] < LIS[j]+1) {
						LIS[i] = LIS[j] + 1;
					}
				}
			}
			
			if(max < LIS[i]) { // 현 원소의 LIS 값과 전체 최대값하고 비교하여 최대값 갱신
				max = LIS[i];
			}
		}
		
		System.out.println(max);
	}

}
