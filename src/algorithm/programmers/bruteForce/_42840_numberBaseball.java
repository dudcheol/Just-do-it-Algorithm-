package algorithm.programmers.bruteForce;

/*
 * 프로그래머스 - 코딩테스트 연습 - 완전탐색 - 숫자 야구
 * 시작:21:15
 * 끝:22:50
 * 걸린시간:1시간 35분
 * [배운 것]
 * - 정말 111~999까지 전부 다 검사하는 완전탐색이었음
 * - 문제는 정말 끝까지 잘 읽어야 한다
 * [실수한 것]
 * - 문제에서 "각자 서로 다른 1~9까지 3자리 임의의 숫자"라고 한 것의 의미는
 * 	 1. 세 숫자에는 중복이 없다.
 *   2. 세 숫자에 0은 없다.
 * - 문제를 잘못 이해한 것 때문에 시간이 지체된 문제...이런 실수는 정말 피해야한다!!!
 * - 저것 외에도 스트라이크와 볼의 개념을 확실히 하지 않아서 코드 수정도 했었으니.. 꼭 문제를 끝까지 잘 읽도록 한다!!
 */

public class _42840_numberBaseball {
	// baseball의 각 행은 [세 자리의 수, 스트라이크의 수, 볼의 수]
	static int solution(int[][] baseball) {
		int answer = 0;
		int num = 111;
		while (true) {
			if (num > 999)
				break;
			String _num = String.valueOf(num);
			if(_num.contains("0") || _num.charAt(0) == _num.charAt(1)  || _num.charAt(0) == _num.charAt(2) ||
					_num.charAt(1) == _num.charAt(2)) {
				num++;
				continue;
			}
			
			int same = 0;
			for (int i = 0; i < baseball.length; i++) {
				String target = String.valueOf(baseball[i][0]);
				int S = baseball[i][1];
				int B = baseball[i][2];
				int tS = 0;
				int tB = 0;

				// B(볼) 찾기, S(스트라이크) 찾기
				boolean[] visited = new boolean[3]; // B든, S든 사용됐으면 카운트하지 않는다
				
				for (int j = 0; j < 3; j++) {
					if (_num.charAt(j) == target.charAt(j)) {
						tS++;
						visited[j] = true;
						continue;
					}
					for (int k = 0; k < 3; k++) {
						if (_num.charAt(j) == target.charAt(k) && !visited[k]) {
							tB++;
							visited[k] = true;
						}
					}
				}
				
				if (tB == B && tS == S) {
					same++;
				} else {
					break;
				}
			}
			if (same == baseball.length) {
				answer++;
			}
			num++;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] baseball = { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } };

		System.out.println(solution(baseball));
	}
}
