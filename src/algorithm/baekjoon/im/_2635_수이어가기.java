package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _2635_수이어가기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> max = new ArrayList<Integer>();

		for (int i = 1; i <= n; i++) {
			int prepre = n;
			int pre = i;

			int cur = 0;
			list.add(pre);
			while ((cur = prepre - pre) >= 0) {
				list.add(cur);
				prepre = pre;
				pre = cur;
			}

			if (max.size() < list.size()) {
				max.clear();
				max.addAll(list);
			}

			list.clear();
		}

		sb.append(max.size()+1).append('\n').append(n).append(' ');
		for (int m : max) {
			sb.append(m).append(' ');
		}
		System.out.println(sb);
	}

}
