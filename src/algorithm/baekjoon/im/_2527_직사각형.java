package algorithm.baekjoon.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2527_직사각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());

			// 직사각형2의 각 꼭짓점
			int[][] point = { { x2, y2 }, { p2, y2 }, { p2, q2 }, { x2, q2 } };

			// 직사각형2의 각 꼭짓점이 직사각형1의 내부에 존재하는지 확인
			boolean isD = true;
			for (int d = 0; d < 4; d++) {
				int target1 = point[d][0];
				int target2 = point[d][1];

				if ((x <= target1 && target1 <= p) && (y <= target2 && target2 <= q)) {
					isD = false;
					break;
				}
			}
			if (isD) {
				sb.append('d');
				sb.append('\n');
				continue;
			}

			boolean isA = true;
			for (int d = 0; d < 4; d++) {
				int target1 = point[d][0];
				int target2 = point[d][1];

				if (x == target1 || target1 == p || y == target2 || target2 == q) {
					isA = false;
					break;
				}
			}
			if (isA) {
				sb.append('a');
				sb.append('\n');
				continue;
			}

			int isB = 0;
			for (int d = 0; d < 4; d++) {
				int target1 = point[d][0];
				int target2 = point[d][1];

				if ((x == target1 || target1 == p) && (y == target2 || target2 == q)) {
					isB++;
					break;
				}
			}
			if (isB == 1) {
				sb.append('c');
			} else if(isB > 1) {
				sb.append('b');
			}	
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
