package baekjoon.samsungA;

/*
 * 백준 - 16637 - 괄호 추가하기
 * 시작~끝: 오랜만에 해서 감찾는 김에  푼 문제라 시간 안잼

 * - 틀렸다! -
 *	[오답 원인]
	시간이 너무 오래걸림..
	오래걸렸음에도 다 못풂
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?] 
 
 *  [팁]
	substring(a,b) : a포함~b는 포함하지 않음!! 예) "abcdefg".substring(0,4)은 abcd
 */

import java.util.*;
import java.io.*;

public class _16637_toAddParentheses {
	static int N;
	static String str;
	static boolean[] visited;
	static int answer = 0;

	static void solve(int k, String cal, int idx) {
		// 기저
		if (cal.length() < 5) {
			answer = calculator(cal);
			return;
		}
		// 기저
		int basis;
		if (cal.length() % 3 > 0)
			basis = cal.length() / 3;
		else
			basis = cal.length() / 3 - 1;
		if (basis == k) {
			answer = Math.max(calculator(cal), answer);
			return;
		}

		String target = "";
		int iIdx = 0;

		for (int i = 0; i < cal.length()-2; i += iIdx) {
			boolean founded = false;
			int startIdx = i;
			int endIdx = 0;
			for (int j = i; j < cal.length(); j++) {
				// 두번째로 나오는 연산자 찾기
				char c = cal.charAt(j);
				if ((c == '+' || c == '-' || c == '*') && !founded) {
					founded = true;
					continue;
				} else if ((c == '+' || c == '-' || c == '*') && founded) {
					endIdx = j;
					break;
				}
			}
			if(endIdx==0) endIdx = cal.length();
			target = cal.substring(startIdx, endIdx);
			int _target = calculator(target);
			String make = cal.substring(0, i) + _target + cal.substring(endIdx, cal.length());
			solve(k + 1, make, i);
			target = "";
			iIdx = endIdx - 1;
		}

	}

	/**
	 * 수의 단위가 10의 자리가 되버리면 너무 복잡해진다..
	 * 왜 배열로만 할 생각을 했을까? list를 써도되는걸.. 그리고 이런식의 문제는 스택/큐를 사용하는게 더 낫다!!
	 */
	static int calculator(String s) {
		s+='/';
		String cnum = "";
		int num = 0;
		char current = '.';
		boolean founded = false;
		int minus = 1;
		for (char c : s.toCharArray()) {
			if (!(c == '+' || c == '-' || c == '*') && !founded) { // 첫 숫자 찾기
				cnum += c;
			} else if ((c == '+' || c == '-' || c == '*') && !founded) {
				founded = true;
				current = c;
				num = Integer.parseInt(cnum);
				cnum = "";
			} else if (!(c == '+' || c == '*' || c == '/')) {
				if (c == '-') {
					minus = -1;
				} else {
					cnum += c;
				}
			} else {
				switch (current) {
				case '+':
					num = num + Integer.parseInt(cnum) * minus;
					break;
				case '-':
					num = num - Integer.parseInt(cnum) * minus;
					break;
				case '*':
					num = num * (Integer.parseInt(cnum) * minus);
					break;
				}
				current = c;
				cnum = "";
				minus = 1;
			}
		}
		return num;
	}

	public static void main(String[] args) throws IOException {
//		System.out.println(calculator("8*3"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		str = br.readLine();

		solve(0, str, 0);
		System.out.println(answer + "");
	}
}
