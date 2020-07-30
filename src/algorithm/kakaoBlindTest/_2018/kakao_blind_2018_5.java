package algorithm.kakaoBlindTest._2018;

import java.util.*;

/*
 * 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 뉴스 클러스터링
 * 시작 : 15:15
 * 끝 : 16:16
 * 걸린시간 : 1시간 1분
 * 
 * [잘한 점]
 * - 손으로 먼저 풀어봄. 굳이 슈도코드를 쓸 필요가 없을 것 같다는 판단을 하고 큰 그림만 그려놓고 코딩으로 옮김
 * - 어떤 알고리즘을 쓰는게 좋을지 고민해봄
 * - 라이브러리에서 제공하는 메소드를 활용함
 * - 문제에서 제안하는 상황들을 주석으로 먼저 써놓고 내가 짠 코드 위에 붙여넣기하는식으로 하니까 놓치지 않았음
 * - 문제를 끝까지 다 읽어보고 내가 놓치면 안되는게 뭐가있는지 생각하면서 풀었음
 * 
 * [고쳐야할 점]
 * - 무분별한 '확신'이 있음. 반복문에서 사용하는 변수, 반복하는 위치 등을 계속 의심해봐야함.
 * - 처음 짤 때 대충하고 나중에 디버깅하면 되겠지~ 라는 생각에 안일하게 코딩함 => 경험상 이러면 디버깅하느라 시간을 많이씀
 * - 사소한 실수를 줄여야함.
 */

public class kakao_blind_2018_5 {
	static int solution(String str1, String str2) {
		// J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의
		// 집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1
		// 문자열의 길이는 2 이상, 1,000 이하
		int answer = 0;
		// 대문자와 소문자의 차이는 무시한다. AB와 Ab, ab는 같은 원소
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		ArrayList<String> words1 = new ArrayList<>(); // 글자쌍
		ArrayList<String> words2 = new ArrayList<>(); // 글자쌍

		// 글자쌍 만들기
		for (int i = 0; i < str1.length() - 1; i++) {
			words1.add(str1.substring(i, i + 2));
		}
		for (int i = 0; i < str2.length() - 1; i++) {
			words2.add(str2.substring(i, i + 2));
		}
		
		System.out.println(words1);
		System.out.println(words2);

		// 이때 영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다.
		for (int i = 0; i < words1.size(); i++) {
			char[] c = words1.get(i).toCharArray();
			if (!(c[0] >= 97 && c[0] <= 122) || !(c[1] >= 97 && c[1] <= 122)) {
				words1.remove(i);
				i--;
			}
		}
		for (int i = 0; i < words2.size(); i++) {
			char[] c = words2.get(i).toCharArray();
			if (!(c[0] >= 97 && c[0] <= 122) || !(c[1] >= 97 && c[1] <= 122)) {
				words2.remove(i);
				i--;
			}
		}
		
		System.out.println(words1);
		System.out.println(words2);

		// 교집합 만들기
		ArrayList<String> kyo = new ArrayList<>();
		boolean[] already = new boolean[words2.size()];
		for (String word1 : words1) {
			for (int i = 0; i < words2.size(); i++) {
				if (already[i])
					continue; // 이미 만난 글자쌍은 교집합에 넣지 않음
				if (word1.equals(words2.get(i))) {
					kyo.add(word1);
					already[i] = true; // 이미 만남처리된 글자쌍을 표시함
					break;
				}
			}
		}
		
		System.out.println("kyo:"+kyo);
		// 합집합 만들기 A+B-(A교집합B)
		ArrayList<String> hap = new ArrayList<>();
		hap.addAll(words1);
		hap.addAll(words2);
		for(String k : kyo) {
			for(int i=0;i<hap.size();i++) {
				if(k.equals(hap.get(i))) {
					hap.remove(i);
					break;
				}
			}
		}
		
		System.out.println("hap:"+hap);

		// 집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1
		if (hap.size() == 0)
			answer = 1 * 65536;
		else
			answer = (int) ((double) kyo.size() / (double) hap.size() * 65536);
		
		return answer;
	}

	public static void main(String[] args) {
//		String str1 = "FRANCE";
//		String str2 = "french";
//		String str1 = "handshake";
//		String str2 = "shake hands";
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
//		String str1 = "E=M*C^2";
//		String str2 = "e=m*c^2";

		System.out.println(solution(str1, str2));
	}
}
