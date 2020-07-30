package algorithm.kakaoBlindTest._2018;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 추석 트래픽
 * 
 * 1차 시작: 23 : 20
 * 1차 종료: 1 : 55 -> 정확성 86.4
 * 1차 걸린시간: 1시간 35분
 * 
 * 2차 시작: 13:45
 * 2차 종료: 16:50 -> 정확성 86.4 그대로임
 * 2차 걸린시간: 2시간 55분
 * 
 * [배운 것]
 * - String을 Date형식으로 바꿀땐 SimpleDateFormat을 사용.
 *  String oldstring = "2011-01-18 00:00:00.0";
	Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring);
 *  반대의경우,
 *  String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
	System.out.println(newstring); // 2011-01-18
 * - Calendar 객체를 사용해서 시간단위 계산이 가능하다
 *  Calendar.getInstance로 캘린더를 생성하고, setTime을 통해 임의 날짜를 정할 수 있다
 *	
 * [실수한 것들]
 * - 시간정보를 Date객체에 넣어 getTime으로 값을 얻어 long형으로 밀리세컨드까지 얻어낸 것은 좋았으나,
 *  시작시간을 구하기 위해 처리시간을 빼는 과정에서 시간단위 뺄셈이 아니라 10진수 뺄셈이 적용된 문제가 있었다.
 * - 새로만든 객체에 객체를 복사해서 넣고 싶을때 주의해야할 것 "참조복사"와 "값 복사"
 *  캘린더 객체 A에 있는 정보를 캘린더 객체 B에 넣고싶을 때, B = A로 해버리면 둘은 연결되어 있어서 나중에 B를 고치면 A가 바뀌고, A를 고치면  B가 바뀌는 일이 생긴다.
 *  (즉, 참조복사(얕은복사)가 일어난다는 뜻)
 *  따라서, B = A.clone() 같은 이미 지원하는 복제메소드를 사용하거나, for문 등을 통해 A의 값을 일일이 조회해서 B에 넣는 작업을 해서 값 복사(깊은 복사)를 해야한다.
 *  실수하지 않도록 주의
 *
 */

public class kakao_blind_2018_7 {
	static int solution(String[] lines) {
		// 배열은 N(1 ≦ N ≦ 2,000)개의 로그 문자열, 응답완료시간 S와 처리시간 T가 공백으로 구분
		// lines 배열은 응답완료시간 S를 기준으로 오름차순 정렬
		int answer = 0; // 초당 최대 처리량
		int size = lines.length;

		// date 로 파싱
		Calendar[] C = new Calendar[size];
//		Date[] dT = new Date[size];
//		long[] S = new long[size];
		double[] T = new double[size];
		for (int i = 0; i < size; i++) {
			C[i] = Calendar.getInstance();
			String[] s = lines[i].split(" ");
			try {
				String t = s[0] + " " + s[1];
				// 응답완료시간 S는 작년 추석인 2016년 9월 15일만 포함하여 고정 길이 2016-09-15 hh:mm:ss.sss 형식으로 되어 있다.
				C[i].setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(t));
//				S[i] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(t).getTime();
				// 처리시간 T는 0.1s, 0.312s, 2s 와 같이 최대 소수점 셋째 자리까지 기록하며 뒤에는 초 단위를 의미하는 s로 끝난다.
				T[i] = Double.parseDouble(s[2].substring(0, s[2].indexOf("s")));
//				dT[i] = new SimpleDateFormat("s.SSS").parse(Double.toString(T[i]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// 시작시간 구하기 = 응답완료시간 - 처리시간
		long[] start = new long[size];
		Calendar[] startC = new Calendar[size];
		for (int i = 0; i < size; i++) {
			startC[i] = (Calendar) C[i].clone();
			double t = T[i];
			if (t > 3000)
				t = 3000;
			if (t < 1)
				t = 1;
			startC[i].add(Calendar.MILLISECOND, -(int) (t * 1000) + 1);
			// 처리시간은 시작시간과 끝시간을 포함 10초~20초면 10,11,12 ... ,20 이어서 처리시간은 11초
//			long t = (long) (T[i] * 1000);
			// 0.001 ≦ T ≦ 3.000
//			if (t > 3000)
//				t = 3000;
//			if (t < 1)
//				t = 1;

//			start[i] = S[i] - t + 1; // 처리시간은 시작시간과 끝시간을 포함 10초~20초면 10,11,12 ... ,20 이어서 처리시간은 11초
			// 2016-09-14 23:59:57.700 = 1473865197701
		}

		// 각 트래픽의 시작시간과 응답시간이 들어있는 배열 만들기
//		long[] trafficSE = new long[lines.length * 2];
		Calendar[] trafficSE = new Calendar[size * 2];
		for (int i = 0, k = 0; i < trafficSE.length; i += 2, k++) {
			trafficSE[i] = (Calendar) startC[k].clone();
			trafficSE[i + 1] = (Calendar) C[k].clone();
//			trafficSE[i] = start[k];
//			trafficSE[i + 1] = S[k];
		}

		int burst = 0;
		int max = 0;
		for (int i = 0; i < trafficSE.length; i++) {
			boolean[] visited = new boolean[lines.length]; // 이미 카운트된 트래픽인지 확인
			// i번째 시간대부터 1초동안 실행되는 트래픽 갯수 세기
			for (int j = 0; j < trafficSE.length; j++) {
				int k = j / 2;
				// 초당 최대 처리량은 요청의 응답 완료 여부에 관계없이 임의 시간부터 1초(=1,000밀리초)간 처리하는 요청의 최대 개수
				Calendar after1s = (Calendar) trafficSE[i].clone();
				after1s.add(Calendar.MILLISECOND, 999);
				if (trafficSE[i].compareTo(trafficSE[j]) < 0)
					continue;
				if (trafficSE[j].compareTo(trafficSE[i]) >= 0 && trafficSE[j].compareTo(after1s) <= 0) {
//				if (trafficSE[j] >= trafficSE[i] && trafficSE[j] <= trafficSE[i] + 999) {
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
		String[] lines = { "2016-09-15 23:59:59.999 0.001s" };
//		String[] lines = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" };
//		String[] lines = { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" };
//		String[] lines = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
//				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
//				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
//				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
//		String[] lines = { "2016-09-14 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s" };

		System.out.println(solution(lines));
	}
}
