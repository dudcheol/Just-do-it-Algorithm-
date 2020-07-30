package algorithm.baekjoon.BruteForce;

/*
 * 백준 - 완전탐색 - 2231 - 분해합
 * 시작: 13:20
 * 끝: 14:03
 * 시간: 43분
 * 
 * [배운 것]
 * - 자리수 구하기 (int)Math.log10(n)+1
 * - 2의 3제곱 => (int)Math.pow(2,3);
 * 
 * [고쳐야할 점]
 * - 문제풀 때 시간생각하면서 풀자. 늘 실전처럼!
 */

import java.util.*;

public class _2231_Decomposition {
	private static int answer=0;
	private static void findDC(int n, int target) { // n은 현재 수. n의 분해합을 구하다가  target과 같아지면 return
		if(n==target) {
			return;
		}
		int jarisuOfN = (int)Math.log10(n)+1;
		int[] jAry = new int[jarisuOfN];
		
		int k=0; // 배열의 인덱스 = 자리수
		int idx = (int)Math.pow(10, jarisuOfN-1);
		int nam=n;
		for(int i=idx;i>=1;i/=10) {
			int mok = nam/i;
			nam = nam%i;
			jAry[k] = mok;
			k++;
		}
		
		int res=n;
		for(int j : jAry) {
			res+=j;
		}
		
		if(res==target) {
			answer = n;
			return;
		}
		
		findDC(n+1, target);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int target = sc.nextInt();

		findDC(1, target);
		System.out.println(answer);
	}
}
