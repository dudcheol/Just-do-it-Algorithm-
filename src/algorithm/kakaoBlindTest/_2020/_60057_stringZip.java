package algorithm.kakaoBlindTest._2020;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2020 KAKAO BLIND RECRUITMENT - 문자열 압축
 * 
 * [배운  것]
 * - 중첩 for문 다룰 때, 인덱스가 너무 헷갈리면 손으로 풀어보는 것이 도움이 된다.
 * - 중첩 for문 짤 때, 큰 틀에서 반복하고자 하는 것이 무엇인지 먼저 정하고 작성한다. 그리고 그안에서 돌아가게끔 짠다 
 * - string.startWith('a')  string문자열이 특정 문자열(a)로 시작하는지 확인하는 메서드
 * 
 * [고쳐야할 것]
 * - 제한사항은 반드시 확인한다
 * - 입출력 예는 반드시 읽어본다. 이번문제는 어려울 수 있었으나 입출력 예4번에서 "문자열은 제일 앞부터 정해진 길이만큼 잘라야 합니다."라는 조건이 있기 때문에
 * 쉬울 수 있었다는 것을 감안하면 꼭 읽어봐야 한다. 문제를 대충 읽는 경향이 있다. 조심!!!!!
 */

import java.util.*;

public class _60057_stringZip {
	public static int solution(String s) {
		int answer = 9999;
		int sSize = s.length();

		// 문자열 자르기
		int div = 1; // div만큼으로 나누기
		List<String>[] sl = new ArrayList[sSize / 2];
		for (int i = 0; i < sSize / 2; i++) {
			sl[i] = new ArrayList<>();
			int idx = 0;
			int cutCnt = sSize % div == 0 ? sSize / div : sSize / div + 1;

			for (int j = 0; j < cutCnt; j++) {
				String str = "";
				for (int k = 0; k < div; k++) {
					if (idx >= sSize)
						break;
					str += s.charAt(idx);
					idx++;
				}
				sl[i].add(str);
			}
			div++;
		}

		int slSize = sl.length;
		for (int i = 0; i < slSize; i++) {
			int divIdx = i + 1; // divIdx 크기로 분할됨
			String res = "";
			int cnt = 0;
			for (int j = 0; j < sl[i].size(); j++) {
				if (j < sl[i].size() - 1 && sl[i].get(j).equals(sl[i].get(j + 1))) {
					cnt++;
				} else {
					if (cnt >= 1) {
						res += (cnt + 1 + "" + sl[i].get(j));
					} else {
						res += "" + sl[i].get(j);
					}
					cnt = 0;
				}
			}
			System.out.println(res);
			answer = Math.min(res.length(), answer);
		}

		return answer == 9999 ? 1 : answer;
	}

	public static void main(String[] args) {
		System.out.println();
//		String s = "aabbaccc";
//		String s = "xababcdcdababcdcd";
		String s = "ab";
		System.out.println(solution(s));
	}
}
