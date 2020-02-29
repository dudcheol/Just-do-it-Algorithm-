package programmers.skillCheck;

import java.util.*;

public class skill_test_level2_q2_try1 {
	static int solution(int[] scoville, int K) {
		int answer = 0;

		PriorityQueue<Integer> pQ = new PriorityQueue<>(scoville.length, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
		});

		for (int s : scoville) {
			pQ.add(s);
		}

		while (!(pQ.size() < 2)) {
			int mixed = pQ.poll() + pQ.poll() * 2;
			pQ.add(mixed);

			answer++;
			if (pQ.peek() > K) {
				return answer;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 500;

		System.out.println(solution(scoville, K));
	}
}

// 정확성100/효율성0
//List<Integer> l = new ArrayList<Integer>();
//
//for (int i = 0; i < scoville.length; i++) {
//	l.add(scoville[i]);
//}
//
//boolean success = false;
//
//Collections.sort(l);
//while (!success) {
//	if (l.size() < 2)
//		return -1;
//
//	// 음식 섞기
//	int mixed = l.get(0) + (l.get(1) * 2);
//	// 이미 섞은 음식 리스트에서 제거
//	l.remove(0);
//	l.remove(0);
//	// 섞고 난 음식 추가
//	l.add(0,mixed);
//	
//	Collections.sort(l);
//
//	// 첫번째 음식의 스코빌지수가 K이상인지 확인
//	success = true;
//	if (l.get(0) < K)
//		success = false;
//	else
//		success = true;
//	answer++;
//}
