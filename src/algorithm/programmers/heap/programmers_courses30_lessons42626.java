package algorithm.programmers.heap;

import java.util.*;

/*
 * 12:15 start
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return
 * 
 * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 */

public class programmers_courses30_lessons42626 {
	static int solution(int[] scoville, int K) {
		int answer = 0;
		int overCnt = 0;
		int mix = 0;

		PriorityQueue<Integer> pQ = new PriorityQueue<>(scoville.length, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 >= o2)
					return 1;
				else
					return -1;
			}
		});

		for (int sc : scoville) {
			pQ.offer(sc);
		}

		while (!pQ.isEmpty()) {
			int polledQ = pQ.poll();
			if (polledQ < K) {
				if(pQ.isEmpty()) continue;
				answer++;
				mix = polledQ + pQ.poll() * 2;
				pQ.offer(mix);
			} else {
				overCnt++;
			}
		}

		if(overCnt==scoville.length) return 0;
		if(mix < K) return -1;
		return answer==0 ? -1 : answer;
	}

	public static void main(String[] args) {
		int[] scoville = { 1, 2 };
		int K = 7;

		System.out.println(solution(scoville, K));
	}
}
