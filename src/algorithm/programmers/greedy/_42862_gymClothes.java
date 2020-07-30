package algorithm.programmers.greedy;

/*
 * 프로그래머스 - 코딩테스트 연습 - 탐욕법(Greedy) - 체육복
 * 시작: 18:30
 * 끝: 19:00
 * 시간: 30분
 * 
 * [주의할 것]
 * 배열의 시작 인덱스를 1부터 시작하게 하려고 배열 선언시 크기를 1만큼 크게 설정했을 때
 * for문 사용 시 배열.length 사용 주의할 것. 
 */

public class _42862_gymClothes {
	static int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;

		int[] student = new int[n + 1]; // 인덱스 1부터 시작
		int stdSize = student.length;

		for (int i = 1; i < stdSize; i++) {
			student[i] = 1;
		}
		for (int i = 0; i < lost.length; i++) {
			student[lost[i]] -= 1;
		}
		for (int i = 0; i < reserve.length; i++) {
			student[reserve[i]] += 1;
		}

		for (int i = 1; i < stdSize; i++) {
			if (student[i] == 0) {
				if (i == 1) {
					if (student[i + 1] >= 2) {
						student[i] += 1;
						student[i + 1] -= 1;
					}
					continue;
				}
				if (i == stdSize) {
					if (student[i - 1] >= 2) {
						student[i] += 1;
						student[i - 1] -= 1;
					}
					continue;
				}
				if (student[i - 1] >= 2) {
					student[i] += 1;
					student[i - 1] -= 1;
					continue;
				}
				if (student[i + 1] >= 2) {
					student[i] += 1;
					student[i + 1] -= 1;
					continue;
				}
			}
		}

		for (int i = 1; i < stdSize; i++) {
			if (student[i] >= 1) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };

		System.out.println(solution(n, lost, reserve));

	}
}
