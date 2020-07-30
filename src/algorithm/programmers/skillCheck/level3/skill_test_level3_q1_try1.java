package algorithm.programmers.skillCheck.level3;

/**
 * 전부 시간초과
 */

public class skill_test_level3_q1_try1 {
	static int answer;

	static int solution(int n) {
		answer = 0;

		recursion(n, 0);

		return answer%1000000007;
	}

	static void recursion(int size, int filled) {
		// 기저
		if(filled>size) {
			return;
		} else if(filled==size) {
			answer++;
			return;
		}
		
		recursion(size, filled+1);
		recursion(size, filled+2);
	}

	public static void main(String[] args) {
		int n = 4;

		solution(n);

		System.out.println(answer);
	}
}
