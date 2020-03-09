package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 218p - 와일드카드 (완전탐색 + DP ver)
 * - 교재참고! -
 *	[간단한 해법]
	- 문자열을 잘라가며 그것을 파라미터로 재귀를 진행한다. '*'를 기준으로 패턴을 나누는 것이 핵심!
	- 문자의 길이가 최대 100이므로 w,s는 각각 최대 101개 밖에 없다. 따라서 최대 경우는 101x101번의  호출이다. 여기서 발생할 수 있는 부분문제를 메모이제이션한다.
 *	[어떤  방식으로 접근했나?]
	제한해둔 시간 (1시간30분)을 초과해서 교재를 참고했다
 *	[해법을 찾는 데 결정적인 깨달음]
	- '*'을 기준으로 패턴을 나누는 것 자체를 생각하지 못했었다. 또, 재귀호출의 응용방법이 배웠던 부분에서만 생각이 가능했는데 이런식으로도 가능하다는 것을 깨달았다.
 *	[다른 해결 방법이 있다면?]
	DP를 이용하면 더욱 효율적인 프로그램이 완성된다
 */

import java.util.*;
import java.io.*;

public class _218p_WildCard {
	static String W, S;
	static int strlen;
	static String[] strs;
	static List<String> answer;
	static int[][] cache = new int[101][101];

	static int findMatch(int w, int s) {
		/*
		 * '*'이후에 몇개의 문자가 등장할 지 알 수 없으므로 '*'을 기준으로 진행한다. '*'을 발견하면 발견 전의 문자열을 잘라내어 나머지
		 * 문자열을 가지고 재귀를 진행한다.
		 */

		// 메모이제이션
		if (cache[w][s] != -1)
			return cache[w][s];

		while (w < W.length() && s < S.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			w++;
			s++;
		}

		// 와일드카드의 문자(w) 끝에 도달했을때, 주어진 문자열(s)의 끝에도 도달한 것이라면 문자가 일치하는 것이므로 true
		// 아니라면, w와 문자 수가 다른것이므로 false
		if (w == W.length())
			return s == S.length() ? 1 : 0;

		// '*'를 만나서 반복문이 종료된 경우 : '*'에 몇 글자를 대응해야 할 지를 '재귀호출'을 통해 확인한다
		if (W.charAt(w) == '*') {
			for (int skip = 0; s + skip <= S.length(); skip++) {
				if (findMatch(w + 1, s + skip) == 1) {
					return cache[w][s] = 1;
				}
			}
		}
		return cache[w][s] = 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			W = br.readLine();
			strlen = Integer.parseInt(br.readLine());
			strs = new String[strlen];
			for (int i = 0; i < strlen; i++) {
				strs[i] = br.readLine();
			}

			// 매치되는 파일명 찾기
			answer = new ArrayList<>();
			
			for (int i = 0; i < strlen; i++) {
				for (int[] row : cache) {
					Arrays.fill(row, -1);
				}
				S = strs[i];
				if (findMatch(0, 0) == 1)
					answer.add(strs[i]);
			}

			Collections.sort(answer);

			for (int i = 0; i < answer.size(); i++) {
				System.out.println(answer.get(i));
			}
		}
	}
}
