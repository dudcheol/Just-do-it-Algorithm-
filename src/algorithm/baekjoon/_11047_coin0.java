package algorithm.baekjoon;

import java.util.*;

/*
 * 백준 - 11047 - 그리디 - 동전0
 * 시작: 18:50
 * 끝: 19:07
 * 시간: 17분
 */
public class _11047_coin0 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] coins = new int[N];
		for(int i=0;i<N;i++) {
			coins[i] = sc.nextInt();
		}
		
		ArrayList<Integer> can = new ArrayList<>();
		for(int c : coins) {
			if(c<=K) {
				can.add(c);
			}
		}
		
//		System.out.println(can);
		int size = can.size();
		int res = K;
		for(int i=size-1;i>=0;i--) {
			int coin = can.get(i);
			while(true) {
				res-=coin;
				if(res>0) {
					answer++;
					continue;
				}else if(res<0) {
					res+=coin;
					break;
				}else {
					answer++;
					System.out.println(answer);
				}
			}
		}
	}
}
