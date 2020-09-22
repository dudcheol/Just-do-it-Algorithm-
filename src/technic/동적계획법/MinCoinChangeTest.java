package technic.동적계획법;

import java.util.Scanner;

// 동전단위 1,4,6 : 배수의 성질이 만족하지 않는 경우
// 동전 개수에 재한이 없다.
public class MinCoinChangeTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int money = sc.nextInt();
		int[] D = new int[money + 1]; // 각 금액의 최적(최소) 교환동전개수를 저장하는 동적테이블

		// 각 금액별 최소 교환 동전개수를 구함.
		// 1원 시도했을 경우 최적개수 : D[i-1] + 1
		// 4원 시도했을 경우 최적개수 : D[i-4] + 1
		// 6원 시도했을 경우 최적개수 : D[i-6] + 1
		//
		// 위 세 경우의 가장 최적값을 저장
		int min;
		for (int i = 1; i <= money; i++) { // 1원부터 목표 금액까지
			min = Integer.MAX_VALUE;
			if (i >= 1 && D[i - 1] + 1 < min) min = D[i - 1] + 1;
			if (i >= 4 && D[i - 4] + 1 < min) min = D[i - 4] + 1;
			if (i >= 6 && D[i - 6] + 1 < min) min = D[i - 6] + 1;
			
			D[i] = min;
		}
		
		System.out.println(D[money]);
	}

}
