package baekjoon.BruteForce;

/*
 * 백준 - 모든 순열 (재귀로 풀기)
 * 시작 : 14:35
 * 끝 : 16:05
 * 시간 : 1시간 30분...너무 오래걸렸음
 * 
 * [고쳐야할 것]
 * - 어렵게 풀려고 하는 경향이 있다. 컴퓨터는 무식한 작업을 순식간에 해낼 수 있으니...
 *   무식하게 할 수 있는 부분은 무식하게 진행하자
 * - 쉽다고 생각되는 문제도 꼭 손으로 풀어보고 해야한다 !!
 */

import java.util.*;
import java.io.*;

public class _10974_AllPermutation {
	static boolean[] visited;
	static int[] num;
	static int N;

	static void recursion(int k) {
		if (k > N)
			return;

		if (k == N) {
			for (int i = 1; i <= N; i++) {
				System.out.print(num[i] + " ");
			}
			System.out.println();
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			num[k + 1] = i;
			recursion(k + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		visited = new boolean[N + 1];
		num = new int[N + 1];

		recursion(0);
	}
}
