package algorithm.baekjoon;

import java.util.*;

/*
 * 백준 - 그리디 - 11399 - ATM
 * 시작:19:20
 * 끝:19:37
 * 시간:17분
 */

public class _11399_ATM {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] p = new int[n];
		
		for(int i=0;i<n;i++) {
			p[i] = sc.nextInt();
		}
		
		Arrays.sort(p);
		
		int hap=0;
		int answer=0;
		for(int i=0;i<n;i++) {
			hap += p[i];
			answer += hap;
		}
		
		System.out.println(answer);
	}
}
