package kakaoBlindTest._2018;

/*
 * 10:30 시작 ~ 10:57 끝
 * Integer.toBinaryString : Integer를 2진수로 변환. 뭔가 변환이 필요할땐 Integer. String. 이런거 써보기
 */

public class kakao_blind_2018_1 {
	static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		String[] ans = new String[n];
		for (int i = 0; i < n; i++) {
			ans[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
			answer[i] = "";

			if (ans[i].length() < n) {
				for (int j = 0; j < n - ans[i].length(); j++)
					answer[i] += " ";
			}

			for (char c : ans[i].toCharArray()) {
				if (c == '1') {
					answer[i] += "#";
				} else {
					answer[i] += " ";
				}
			}
			System.out.println(answer[i]);
		}
		return answer;
	}

	public static void main(String[] args) {
		int n = 6;
		int[] arr1 = { 46, 33, 33, 22, 31, 50 };
		int[] arr2 = { 27, 56, 19, 14, 14, 10 };

		for (String s : solution(n, arr1, arr2))
			System.out.println(s);
	}
}
