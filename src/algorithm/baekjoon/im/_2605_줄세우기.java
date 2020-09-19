package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class _2605_줄세우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] p = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < p.length; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> order = new ArrayList<Integer>();
		int num = 2;
		order.add(1);
		for (int i = 1; i < p.length; i++) {
			int cur = p[i];
			order.add(cur, num++);
		}

		for (int i = p.length-1; i >= 0; i--) {
			sb.append(order.get(i)).append(' ');
		}
		System.out.println(sb);
	}

}
