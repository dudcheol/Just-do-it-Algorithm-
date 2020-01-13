package just_do_it_Algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 추석 트래픽
 * 
 * 1차 시작: 23 : 20
 * 1차 종료: 1 : 55 -> 정확성 86.4
 * 1차 걸린시간: 1시간 35분
 * 
 * [배운 것]
 * - String을 Date형식으로 바꿀땐 SimpleDateFormat을 사용.
 *  String oldstring = "2011-01-18 00:00:00.0";
	Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring);
 *  반대의경우,
 *  String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
	System.out.println(newstring); // 2011-01-18
 *
 *  정 모르겠을 때 참고
 *  https://programmers.co.kr/learn/questions/8738
 */

public class kakao_blind_2018_7 {
	static int solution(String[] lines) {
		// 초당 최대 처리량은 요청의 응답 완료 여부에 관계없이 임의 시간부터 1초(=1,000밀리초)간 처리하는 요청의 최대 개수
		// 배열은 N(1 ≦ N ≦ 2,000)개의 로그 문자열, 응답완료시간 S와 처리시간 T가 공백으로 구분
		// 응답완료시간 S는 작년 추석인 2016년 9월 15일만 포함하여 고정 길이 2016-09-15 hh:mm:ss.sss 형식으로 되어 있다.
		// 처리시간 T는 0.1s, 0.312s, 2s 와 같이 최대 소수점 셋째 자리까지 기록하며 뒤에는 초 단위를 의미하는 s로 끝난다.
		// 처리시간은 시작시간과 끝시간을 포함 10초~20초면 10,11,12 ... ,20 이어서 처리시간은 11초
		// 0.001 ≦ T ≦ 3.000
		// lines 배열은 응답완료시간 S를 기준으로 오름차순 정렬
		int answer = 0; // 초당 최대 처리량

		// date 로 파싱
		long[] S = new long[lines.length];
		double[] T = new double[lines.length];
		for (int i = 0; i < lines.length; i++) {
			String[] s = lines[i].split(" ");
			try {
				String t = s[0] + " " + s[1];
				S[i] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(t).getTime();
				T[i] = Double.parseDouble(s[2].substring(0, s[2].indexOf("s")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// 시작시간 구하기 = 응답완료시간 - 처리시간
		long[] start = new long[S.length];
		for (int i = 0; i < S.length; i++) {
			long t = (long) (T[i] * 1000 - 1);
			start[i] = S[i] - t;
		}

		// 각 트래픽의 시작시간과 응답시간이 들어있는 배열 만들기
		long[] trafficSE = new long[lines.length * 2];
		for (int i = 0, k = 0; i < trafficSE.length; i += 2, k++) {
			trafficSE[i] = start[k];
			trafficSE[i + 1] = S[k];
		}

		int burst = 0;
		int max = 0;
		for (int i = 0; i < trafficSE.length; i++) {
			boolean[] visited = new boolean[lines.length]; // 이미 카운트된 트래픽인지 확인
			// i번째 시간대부터 1초동안 실행되는 트래픽 갯수 세기
			for (int j = 0; j < trafficSE.length; j++) {
					int k = j / 2;
				if (trafficSE[j] >= trafficSE[i] && trafficSE[j] <= trafficSE[i] + 999) {
					if (visited[k])
						continue;
					burst++;
					visited[k] = true;
				}
			}
			if (burst > max) {
				max = burst;
			}
			burst = 0;
		}

		answer = max;
		return answer;
	}

	public static void main(String[] args) {
//		String[] lines = { "2016-09-15 23:59:59.999 0.001s" };
//		String[] lines = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" };
//		String[] lines = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" };
//		String[] lines = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
//				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
//				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
//				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
		String[] lines = { "2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s" };

		System.out.println(solution(lines));
	}
}
