package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _1158_요세푸스문제 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		int cnt = 0;
		sb.append('<');
		while (!q.isEmpty()) {
			int p = q.poll();
			if (++cnt % K == 0) {
				if (q.size() == 0) {
					sb.append(p).append('>');
				} else {
					sb.append(p).append(',').append(' ');
				}
			} else {
				q.offer(p);
			}
		}
		System.out.print(sb);
		br.close();
	}

}
