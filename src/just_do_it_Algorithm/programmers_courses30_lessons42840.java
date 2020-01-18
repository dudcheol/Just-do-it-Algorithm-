package just_do_it_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 * 프로그래머스 - 코딩테스트 연습 - 완전탐색 - 모의고사 
 * 시작 : 11:35
 * 끝 : 
 * 걸린시간 : 
 */

//1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
//2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
//3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
public class programmers_courses30_lessons42840 {
	static int[] solution(int[] answers) {
		int[] one = { 1, 2, 3, 4 };
		int[] two = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] three = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		HashMap<Integer, Integer> map = new HashMap<>();

		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);

		for (int i = 0; i < answers.length; i++) {
			// 배열 조회 idx 설정
			int idx1 = i >= one.length ? i % one.length : i;
			int idx2 = i >= two.length ? i % two.length : i;
			int idx3 = i >= three.length ? i % three.length : i;

			if (answers[i] == one[idx1]) {
				map.put(1, map.get(1) + 1);
			}
			if (answers[i] == two[idx2]) {
				map.put(2, map.get(2) + 1);
			}
			if (answers[i] == three[idx3]) {
				map.put(3, map.get(3) + 1);
			}
		}
		
//		for(int i=1;i<=3;i++) {
//			if(map.get(i)==0) map.remove(i);
//		}
//
//		if(map.isEmpty()) return new int[0];
		
		// 최대값 찾기
		int max = map.get(1);
		for (int i = 2; i <= map.size(); i++) {
			if (map.get(i) > max) {
				max = map.get(i);
			}
		}

		// 최대값과 동일한 값 찾기
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = 1; i <= map.size(); i++) {
			if (max == map.get(i)) {
				l.add(i);
			}
		}

		Collections.sort(l);
		int[] answer = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			answer[i] = l.get(i);
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] answers = { 4,5,4,5,3 };
//		int[] answers = { 1, 3, 2, 4, 2 };
//		int[] answers = { 0 };

		for (int s : solution(answers)) {
			System.out.println(s);
		}
	}
}
