package programmers.heap;

import java.util.*;

/*
 * 5:30 시작!
 * 라면 공장에서는 하루에 밀가루를 1톤씩 사용합니다. 원래 밀가루를 공급받던 공장의 고장으로 앞으로 k일 이후에야 밀가루를 공급받을 수 있기 때문에 해외 공장에서 밀가루를 수입해야 합니다.
 * 해외 공장에서는 향후 밀가루를 공급할 수 있는 날짜와 수량을 알려주었고, 라면 공장에서는 운송비를 줄이기 위해 최소한의 횟수로 밀가루를 공급받고 싶습니다.
 * 현재 공장에 남아있는 밀가루 수량 stock, 밀가루 공급 일정(dates)과 해당 시점에 공급 가능한 밀가루 수량(supplies), 원래 공장으로부터 공급받을 수 있는 시점 k
 * 밀가루가 떨어지지 않고 공장을 운영하기 위해서 최소한 몇 번 해외 공장으로부터 밀가루를 공급받아야 하는지
 * dates[i]에는 i번째 공급 가능일
 * supplies[i]에는 dates[i] 날짜에 공급 가능한 밀가루 수량
 */

public class programmers_courses30_lessons42629 {
	static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2)
					return -1;
				else
					return 1;
			}
		});

		// 시뮬레이션으로 풂
		// 하루하루가 지날 때마다 그날 공급받을 수 있는 것을 우선순위 큐에 넣음
		int i = 0;
		int day = 0;
		while (!(day > k-1)) {
			if (dates[i] == day) { // 공급받을 수 있는 날이됨
				pQ.offer(supplies[i]);
				if (i < dates.length - 1)
					i++;
			}
			if (stock == 0) {
				int pollQ = pQ.poll();
				stock += pollQ;
				answer++;
			}

			// 하루가 지남
			stock--; // 밀가루 -1
			day++;
		}

		return answer;
	}

	public static void main(String[] args) {
		int stock = 29;
		int[] dates = { 4, 5, 15 };
		int[] supplies = { 5, 30, 20 };
		int k = 30;
		// result = 2

		System.out.println(solution(stock, dates, supplies, k));
	}
}
