package algorithm.baekjoon.samsungA;

/*
 * 백준 - 16637 - 괄호 추가하기
 * 시작~끝: 오랜만에 해서 감찾는 김에  푼 문제라 시간 안잼

 * - 틀렸다! -
 *	[오답 원인]
	시간이 너무 오래걸림..
	오래걸렸음에도 다 못풂
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
 	> 모든 문자열을 조회해가며 계산하는 것은 코드가 너무 복잡해진다.
 	- 왜? 계산할 때마다 피연산자들의 index를 알아야 한다. 그와중에 음수이거나 10의 자리를 넘어가버리면 그에 따른 조건문이 추가된다. ...상당히 복잡...
 	> 그렇다면 어떻게 풀까?
 	- 예~전에 조합 구현할 때 썼던 방법!! DFS자체를 '현재 index의 연산자를 계산한것'과 '현재  index의 연산자를 계산하지 않고 다음에 있는 연산자 계산'을 반복하면!
 	    다음에 있는 연산자가 다시 DFS를 거쳐서 현재 index가 되고! 그것을 계산한것과 계산하지 않은 것이 반복되다보면~~~ 구할 수 있다~~!!!!
 	- 기저에서 return할 때, 꼭 모든 수식이 있을 필요는 없다. 반복문에 진입하면서 계산된 값을 파라미터로 넘겨주면 된다.
 	- 피연산자와 연산자를 나눠서 생각하면 더 간단하게 계산할 수 있다.
 *  [팁]
	substring(a,b) : a포함~b는 포함하지 않음!! 예) "abcdefg".substring(0,4)은 abcd
 */

import java.io.*;

public class _16637_toAddParentheses {
	static int N;
	static String str;
	static int answer = 0;
	static int[] operation;
	static char[] operator;

	static void solve(int idx, int res) { // 계산중인 인덱스, 결과값
		// 기저 - 모든 연산을 마침
		if (idx >= N / 2) {
			answer = Math.max(res, answer);
			return;
		}

		// 현재 idx의 연산자를 수행한다
		int ret = calculator(res, operator[idx], operation[idx + 1]);
		solve(idx + 1, ret);

		// 현재 idx의 연산자를 수행하지 않고 다음 연산자가 있다면 다음 연산자의 연산을 수행한다
		if (idx + 1 < N / 2) {
			ret = calculator(res, operator[idx], calculator(operation[idx + 1], operator[idx + 1], operation[idx + 2]));
			solve(idx + 2, ret);
		}
	}

	static int calculator(int a, char oper, int b) {
		switch (oper) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		operation = new int[N / 2 + 1];
		operator = new char[N / 2];

		int idx1 = 0;
		int idx2 = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*') {
				operator[idx2++] = str.charAt(i);
			} else {
				operation[idx1++] = Integer.parseInt(str.charAt(i) + "");
			}
		}

		answer = Integer.MIN_VALUE;
		solve(0, operation[0]);
		System.out.println(answer + "");
	}
}
