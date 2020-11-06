package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _1874_스택수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int idx = 0;
		Stack<Integer> stack = new Stack<Integer>();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			stack.push(i);
			sb.append('+').append('\n');
			
			while(idx < N && !stack.isEmpty() && stack.peek() >= arr[idx]) {
				stack.pop();
				sb.append('-').append('\n');
				idx++;
			}
		}

		if(!stack.isEmpty()) System.out.println("NO");
		else System.out.print(sb);
	}

}
