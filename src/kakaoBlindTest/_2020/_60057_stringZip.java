package kakaoBlindTest._2020;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2020 KAKAO BLIND RECRUITMENT - 문자열 압축
 * 
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

		return answer;
	}

	public static void main(String[] args) {
		System.out.println();
//		String s = "aabbaccc";
		String s = "xababcdcdababcdcd";
		System.out.println(solution(s));
	}
}
