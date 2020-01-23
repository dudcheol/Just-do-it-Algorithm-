package programmers.greedy;

/*
 * 프로그래머스 - 코딩테스트 연습 - 탐욕법(Greedy) - 조이스틱
 * 시작:
 * 끝:
 * 시간:
 * [배운 것]
 * - 값을 넣어주는 위치 하나하나에 이유가 있어야 한다.
 * - 중간중간에 고칠때 막 써넣으면 오히려 시간을  더 잡아먹을 수 있으니 분명히 하고 고치자!
 */

import java.util.*;

public class _42860_joystick {
	static int solution(String name) {
		int answer = 0;
		int exp = name.length() - 1;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			answer += ('Z' - c + 1) > c - 'A' ? c - 'A' : ('Z' - c + 1);
			if (c == 'A') {
				int nextIdx = i + 1;
				int countA = 0;
				while (nextIdx < name.length() && name.charAt(nextIdx) == 'A') {
					countA++;
					nextIdx++;
				}
				int tmp = (i - 1) * 2 + (name.length() - 1 - i - countA);
				if (exp > tmp)
					exp = tmp;
			}
		}

		answer += exp;
		return answer;
	}

	public static void main(String[] args) {
//		String name = "JEROEN";
		String name = "AABAAAAAAABBB";
//		String name = "AAB";

		System.out.println(solution(name));
	}
}
