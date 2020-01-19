package programmers.bruteForce;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 프로그래머스 - 코딩테스트 연습 - 완전탐색 - 소수 찾기
 * 시작시간: 13:30
 * 끝난시간: 16:00
 * 걸린시간: 2:30
 * [배운 것]
 * - 트리형식(상태공간트리)을 이용해서 그려보고 그것대로 구현하는 방법이 꽤 효과적이었다
 * - 소수를 구할 땐 sqtr함수를 써서 제곱근까지만 나눠봐도 된다.
 * - 손코딩할 때 모듈(?)별로 나눠서 손으로 그려보고 코딩으로 옮기는것도 효과적이었다(필요한 기능을 함수처럼 써놓는다던지)
 */
public class programmers_courses30_lessons42839 {
	static String number = "";
	static int answer = 0;
	static ArrayList<Integer> done = new ArrayList<>();

	static boolean isSosu(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	static void dfs(String current, int k, boolean[] use) {
		if (k >= number.length()) {
			return;
		}
		for (int i = 0; i < number.length(); i++) {
			if (use[i])
				continue;
			String s_current = current + number.charAt(i);
			int _current = Integer.parseInt(s_current);
			if (!done.contains(_current)) {
				if (isSosu(_current)) {
					answer++;
					System.out.println(_current);
					done.add(_current);
				}
			}
			boolean[] _use = use.clone();
			_use[i] = true;
			dfs(s_current, k + 1, _use);
		}
	}

	static int solution(String numbers) {
		number = numbers;

		dfs(new String(""), 0, new boolean[numbers.length()]);

		return answer;
	}

	public static void main(String[] args) {
//		String numbers = "17";
//		String numbers = "011";
		String numbers = "0121";
		int n = solution(numbers);
		System.out.println("---------------");
		System.out.println(n);
	}
}
