package kakaoBlindTest._2018;

import java.util.*;

/*
 * 1:30 시작 2:20 끝 50분소요
 * 수학공식이 필요할땐 Math 클래스 라이브러리 쓰기
 * 잘한 것 : 손으로 먼저 써보고 실행에 옮김. 하지만 이전에 풀어본 기억이 나서 빨리 푼 듯 싶음
 * 고쳐야할 것 : 
 * 1. 손으로 써보고 코딩을 시작하긴했지만 완전히 쓰지않고 중간에 바로 코딩해서 마지막에 디버깅해야하는 일이 생김
 * 2. 스택이어서 배열의 끝부터 push해야할때 for문이 배열의 끝부터 돌아야하는 것에 필요한 조건을 잘못쓰는 어이없는 실수는 하지 말자
 */

public class kakao_blind_2018_2 {
	static int solution(String dartResult) {
		int answer = 0;
		char[] dR = dartResult.toCharArray();
		int dSize = dR.length;

		// num 리스트에 숫자 담기
		ArrayList<Integer> numL = new ArrayList<>();
		for (int i = 0; i < dSize; i++) {
			if (dR[i] == 49 && dR[i + 1] == 48) {
				numL.add(10);
				i++;
			} else if (dR[i] >= 48 && dR[i] <= 57) {
				numL.add(Integer.parseInt(Character.toString(dR[i])));
			}
		}
		System.out.println(numL);

		// 스택초기화
		Stack<Character> ds = new Stack<>();
		for (int i = dSize - 1; i >= 0; i--) {
			ds.push(dR[i]);
		}
		System.out.println(ds);

		// * 42, # 35, 숫자48~57
		int position = 0;
		while (!ds.isEmpty()) {
			char pop = ds.pop();
			if (pop >= 48 && pop <= 57) {
				continue;
			}

			if (pop == 'S') {
				position++;
				continue;
			} else if (pop == 'D') {
				numL.set(position, numL.get(position) * numL.get(position));
				position++;
				continue;
			} else if (pop == 'T') {
				numL.set(position, numL.get(position) * numL.get(position) * numL.get(position));
				position++;
				continue;
			} else if (pop == '*') {
				position--;
				if (position == 0)
					numL.set(position, numL.get(position) * 2);
				else {
					numL.set(position, numL.get(position) * 2);
					numL.set(position - 1, numL.get(position - 1) * 2);
				}
				position++;
				continue;
			} else if (pop == '#') {
				position--;
				numL.set(position, numL.get(position) * -1);
				position++;
				continue;
			}
		}

		for (int n : numL) {
			answer += n;
		}

		return answer;
	}

	public static void main(String[] args) {
		String dartResult = "1D2S3T*";

		System.out.println(solution(dartResult));
	}
}
