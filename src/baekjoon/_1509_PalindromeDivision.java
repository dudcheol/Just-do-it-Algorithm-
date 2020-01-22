package baekjoon;

/*
 * 백준 - 1509 - 팰린드롬 분할
 * 시작:12:30
 * 끝:2:30(아직못품)
 * 시간:
 * [배운것]
 * - DP문제는 배열을 1부터 시작하는게 헷갈리지않고 좋다
 */

import java.util.*;

public class _1509_PalindromeDivision {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ex = sc.nextLine();
		System.out.println(ex);

		int answer = 0;
		int size = ex.length();
		boolean[] visited = new boolean[size];
//		System.out.println(size);
//		System.out.println(ex.charAt(size / 2));
//		System.out.println(ex.substring(0, 0));

		// 1쌍인 애들 경우의 수 1개
		answer++;

		// 2쌍
		ArrayList<Integer> p2 = new ArrayList<>();
		for (int i = 0; i < size - 1; i++) {
			if (ex.charAt(i) == ex.charAt(i + 1)) {
				p2.add(i);
				answer++;
				visited[i] = true;
				visited[i+1] = true;
				System.out.println(ex.substring(i, i+2));
			}
		}

		// 3쌍
		ArrayList<Integer> p3 = new ArrayList<>();
		for (int i = 1; i < size - 1; i++) {
			if (ex.charAt(i - 1) == ex.charAt(i + 1)) {
				p3.add(i - 1);
				answer++;
				visited[i-1] = true;
				visited[i] = true;
				visited[i+1] = true;
				System.out.println(ex.substring(i-1, i+2));
			}
		}

		// 3쌍을 가지고 앞으로의 팰린드롬을 예상해볼 수 있다
		for (int k = 5; k <= size; k++) {
			int answerC = answer;
			if (k % 2 == 1) {
				// 홀수인 경우 p3 이용
				for (int i = 0; i < p3.size(); i++) {
					int idx = p3.get(i);
					int fIdx = idx - 1;
					int eIdx = fIdx + k - 1;
					if (fIdx < 0 || eIdx >= size-1)
						break;
					if (ex.charAt(fIdx) == ex.charAt(eIdx)) {
						p3.set(i, fIdx);
						visited[fIdx] = true;
						visited[eIdx] = true;
						System.out.println(ex.substring(fIdx, eIdx + 1));
					}
				}
			}
			if(answerC==answer) {
				break;
			}
		}
		
		for(boolean v : visited) {
			if(!v) answer++;
		}
		System.out.println(answer);
	}
}
