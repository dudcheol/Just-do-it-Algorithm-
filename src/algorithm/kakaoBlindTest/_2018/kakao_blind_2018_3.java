package algorithm.kakaoBlindTest._2018;

import java.util.*;

/* 시작: 3:40
 * 종료: 5:10
 * 걸린시간: 1:30 성공! < 너무 오래걸림
 * 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 캐시
 * 
 * 너무 어렵게 생각했다
 * 큐를 사용해도 됐고 리스트를 사용해도 됐다.
 * lr[]을 사용함으로써 더 복잡해졌다. 사실 이 문제는 그냥 리스트나 큐를 만들어서 풀어도 됐다.
 * 내가 생각해낸 풀이에서 더 쉽게 할 수 있는 방법이 무엇인지도 생각해보면서 푸는게 좋을 것 같다.
 * 
 * toUpperCase, toLowerCase - 문자열을 모두 대문자, 소문자로 변환
 * equalsIgnoreCase - 대소 구분없이 문자열 비교
 * Queue사용시, LinkedList로 변환해서 removeFirstOccurrence를 사용하면 특정 요소만 찾아서 제거가 가능하다. << ㄹㅇ꿀팁!! 링크드리스트!!
 * Queue사용시, offer과 poll 말고도 remove가 있다는 것을 알자..ㅠㅠ
 * Queue사용시, contains메소드로 포함되어있는지 찾을 수 있음
 */

public class kakao_blind_2018_3 {
	static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		int q = 0;
		ArrayList<String> c = new ArrayList<>();
		int[] lr = new int[cacheSize];

		for (String city : cities) {
			boolean founded = false;
			
			// 캐시가 전부 채워져있지 않은 상태
			if (c.size() < cacheSize) {
				for (int i = 0; i < c.size(); i++) {
					if (c.get(i).equalsIgnoreCase(city)) {
						for (int j = 0; j < c.size(); j++) {
							if (j == i)
								continue;
							if (lr[j] > lr[i]) {
								lr[j]--;
							}
						}
						lr[i] = c.size() - 1;
						answer++;
						founded = true;
						break;
					}
				}
				if (!founded) {
					c.add(city);
					lr[q] = q;
					answer += 5;
					q++;
				}
				continue;
			}

			// 캐시 안에 있다면
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).equalsIgnoreCase(city)) {
					for (int j = 0; j < lr.length; j++) {
						if (j == i)
							continue;
						if (lr[j] > lr[i]) {
							lr[j]--;
						}
					}
					lr[i] = cacheSize-1;
					answer++;
					founded = true;
					break;
				}
			}

			// 캐시 안에 없다면
			if (!founded) {
				for (int j = 0; j < lr.length; j++) {
					if (lr[j] == 0) {
						c.set(j, city);
						lr[j] = cacheSize;
						for (int k = 0; k < lr.length; k++) {
							lr[k]--;
						}
						break;
					}
				}
				answer += 5;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int cacheSize = 4;
//		String[] cities = { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };
		String[] cities = { "Jeju", "Pangyo", "NewYork", "newyork" };
//		String[] cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" };
//		String[] cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris",
//				"Jeju", "NewYork", "Rome" };

		System.out.println(solution(cacheSize, cities));
	}
}
