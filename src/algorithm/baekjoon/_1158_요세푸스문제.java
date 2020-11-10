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

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		// 어떤 리스트의 K번째를 구하려면?
		// (현재 나의 인덱스 + K) % (리스트 사이즈)
		// 리스트에서 제거되면서 인덱스가 1씩 줄어드므로 -1 해준다
		sb.append('<');
		int target = K-1;
		sb.append(list.remove(target--));
		while (!list.isEmpty()) {
			sb.append(", ");
			target = (target + K) % list.size();
			sb.append(list.remove(target--));
		}
		sb.append('>');
		System.out.print(sb);
		br.close();
	}

}
