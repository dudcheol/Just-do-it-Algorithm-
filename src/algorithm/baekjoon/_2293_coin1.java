package algorithm.baekjoon;

import java.util.*;

/*
 * 백준 - 2293 - 동전1
 * 시작: 10:30
 * 끝: 15:48
 * 시간: (중간에밥먹고 인강도 들어서 생략)
 * [배운 것]
 * Bottom-Up(DP) 방식 => 원초적(?)인 것부터 계산해가면서 구하고자하는 값에 도달. recursion에 따르는 overhead가 없음.
 * 					   (순환식에서 왼쪽에 있는 값보다 오른쪽에 있는  값이 먼저 계산되는 방식) 
 * Top-Down(memoization) 방식 => 구하고자하는 답부터 계산해야하는 값들만 계산하면서 필요한 값들만 구하면서 문제를 풂.
 */

public class _2293_coin1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] cost = new int[n];
		int[] memo = new int[k + 1];
		int[] hap = new int[k + 1];
		int[] max = new int[n]; // 동전별 가질 수 있는 최대 수
		for (int i = 0; i < n; i++) {
			cost[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (cost[i] > j) {
					memo[j] = 0;
				} else if (cost[i] == j) {
					memo[j] = 1;
				} else {
					int target = j - cost[i];
					memo[j] = hap[target];
				}
				hap[j] += memo[j];
			}
		}

		System.out.println(hap[k]);
	}
}
