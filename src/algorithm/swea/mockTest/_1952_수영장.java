package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1952_수영장 {

	static int moneys[], days[], min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			moneys = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				moneys[i] = Integer.parseInt(st.nextToken());
			}

			days = new int[12];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}

			min = moneys[3]; // 1년 사용권 금액을 기본 최솟값으로 설정

			// 시간복잡도는 3^12이다.
			// 2^20(약 100만) > 3^12 이므로 완전탐색풀이가 충분히 가능하다
			dfs(0, 0);

			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int month, int sum) {
		if (month >= 12) {
			min = Math.min(min, sum);
			return;
		}

		int today = days[month];

		if (today == 0) {
			dfs(month + 1, sum);
		} else {
			// 1일권 사용
			dfs(month + 1, sum + (today * moneys[0]));

			// 1달권 사용
			dfs(month + 1, sum + moneys[1]);

			// 3달권 사용
			dfs(month + 3, sum + moneys[2]);
		}
	}

}
