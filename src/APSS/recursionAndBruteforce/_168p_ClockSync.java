package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 168p - 시계 맞추기
 * 시작~끝: 18:30~...오래걸림ㅠㅠ
 * - 틀렸다! -
	[오답 원인]
	1. 상수화한 데이터를 잘 못 입력한 것.
	2. BaseCase의 위치를 따로 고려하지 않고 '대충 이럴 것이다...'라는 식으로 or 외운대로 작성한 것
	3. 4^10을 중첩for문으로 생각한 것. 트리를 그려보고 진행하는게 좋아보인다.
	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
	애초에 clock과 연결된 switch를 표현하는 것을 상수화해서 나타낸다는 생각 자체를 못했다.
	for문을 이용해 순회해가면서 연결 여부를 판단하는 것이므로 'for문지향적인 발상'인 것 같다. (내가 코딩을 하기 위해 편한 방향으로 문제를 풀어나간다는 느낌?)
	나도 문제를 풀 때, 여긴 이렇게이렇게 하면 되겠지~ 라는 식으로 계획을 세우지 말고 어떤 방법으로 어떻게 해야겠다 라는 구체적인 풀이방향을 계획해야겠다.
	또, switches의 인덱스가 clock이 되는 것이 눈으로 봤을 땐 좀 헷갈리지만 이해하니까 코딩하는데  도움이됐다.
 */

import java.util.*;
import java.io.*;

public class _168p_ClockSync {
	/* 스위치에 연결된 시계들의 목록을 상수화한다. 헷갈릴 수 있으므로 유의!!!! 이거 잘못 입력해서 엄청 틀림...
	 * 0,1은 넘 헷갈린다 책처럼 string으로해서 x,.로 나타내는게 더 좋을 것 같다....ㄷㄷ */
	// i번째 스위치에 연결된 시계들
	static int[][] switches = { { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } };
	static int[] clock;
	static int answer = Integer.MAX_VALUE;
	static int[] visited;

	// 현재 스위치, 현재까지 누른 횟수
	static void matchAllClock12h(int swchNum, int pressed) {
		// clock 전부 12인지 확인
		int allMatch = -1;
		for (int i = 0; i < clock.length; i++) {
			if (!(clock[i] == 12)) {
				allMatch = 1;
				break;
			}
		}
		
		if (allMatch < 0) {
			answer = Math.min(answer, pressed);
//			return;
		}
		
		/* BaseCase의 위치를 대충 고려하면 안된다!
		 * 윗부분은 swchNum이 9의 작업을 진행하는 것이기 때문에 재귀로 다시 들어가기 전 부분에서 10인지 체크해야 한다.
		 * 위 작업까지 반복해야 할 작업이라고 생각하고, 재귀로 다시 진입하기 전에 BaseCase를 넣는 방향으로 생각해보자! */
		if (swchNum == 10) /* 4^10에서 4를 10중 for문으로 돈다고 생각하면 쉽다 */
			return;

		/* 매번 실수하는 부분.. 주의하자!
		 * 밑의 for문 i에 해당하는 인덱스 값은 매개변수로 주어야 한다! */
//		for (int i = 0; i < switches.length; i++) {
		for (int j = 0; j < 4; j++) { // 모든 스위치는 3번 누를 수 있다
			matchAllClock12h(swchNum + 1, pressed + j);
			pressSwitch(swchNum);
		}
//		}
		return;
	}

	static void pressSwitch(int swch) {
		for (int i = 0; i < clock.length; i++) {
			if (switches[swch][i] == 1) {
				clock[i] += 3;
				if (clock[i] == 15)
					clock[i] = 3;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		clock = new int[16];
		visited = new int[10];

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 16; i++) {
				clock[i] = Integer.parseInt(st.nextToken());
			}

			matchAllClock12h(0, 0);

			// 반복문이므로  answer 출력 후 초기화할 것 유의
			System.out.println(answer >= Integer.MAX_VALUE ? -1 : answer);
			answer=Integer.MAX_VALUE;
		}
	}
}
