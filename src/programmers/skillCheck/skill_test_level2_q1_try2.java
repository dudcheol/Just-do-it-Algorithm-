package programmers.skillCheck;

import java.util.*;

// 압축 50분 
public class skill_test_level2_q1_try2 {

	static int[] solution(String msg) {
		int valueCnt = 26;
		List<Integer> ans = new ArrayList<>();

		// 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
		HashMap<String, Integer> m = new HashMap<>();
		int k = 0;
		for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
			m.put(((char) i) + "", ++k);
		}

		// 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
		while (msg.length() > 0) {
			String match = "";
			int preMatch = 0;
			for (int i = 0; i < msg.length(); i++) {
				match += msg.charAt(i);
				if (m.containsKey(match)) {
					preMatch = m.get(match);
					if(msg.matches(match)) {
						ans.add(m.get(match));
						msg="";
						break;
					}
				} else {
					m.put(match, ++valueCnt);
					ans.add(preMatch);
					msg = msg.substring(i); // i인지
					break;
				}
			}
		}
		
		int[] answer = new int[ans.size()];
		int q=0;
		for(int a : ans) {
			answer[q] = a;
			q++;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] answer = solution("KAKAO");
//		int[] answer = solution("TOBEORNOTTOBEORTOBEORNOT");

		for (int a : answer)
			System.out.print(a + " ");
	}
}
