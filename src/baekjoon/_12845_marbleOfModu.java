package baekjoon;

/*
 * 백준 - 그리디 - 12845 - 모두의마블
 * 시작:?
 * 끝:?
 * 시간:15분
 * [배운 것]
 * - Arrays.sort(t,c)에서 int[]은 comparator를 사용할 수 없다. Integer[]를 사용해야 한다.
 */

import java.util.*;

public class _12845_marbleOfModu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cards = new int[n];
		for (int i = 0; i < n; i++) {
			cards[i] = sc.nextInt();
		}
		
		// find max
		int maxIdx = 0;
		for(int i=1;i<n;i++) {
			if(cards[maxIdx] < cards[i]) {
				maxIdx = i;
			}
		}
		
		int answer=0;
		for(int i=0;i<n;i++) {
			if (i==maxIdx) continue;
			answer += (cards[maxIdx]+cards[i]);
		}

		System.out.println(answer);
	}
}
