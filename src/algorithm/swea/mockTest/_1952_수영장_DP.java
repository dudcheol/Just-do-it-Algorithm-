package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1952_수영장_DP {

	static int moneys[], days[];

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

			days = new int[13];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 12; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}

			sb.append('#').append(test_case).append(' ').append(plan()).append('\n');
		}
		
		System.out.print(sb);
	}

	private static int plan() {
		int D[] = new int[13]; // -3했을 때 참조가 가능하도록 크기가 1개 크게 설정 0~12

		for (int i = 1; i <= 12; i++) {
			// 1일권
			D[i] = D[i - 1] + (days[i] * moneys[0]);

			// 1달권
			if (days[i] > 0) // 이 조건은 쓰지 않아도 결과는 같다. 왜? D[i]에 이미 이전달의 최소비용이 저장되어있기 때문에
				D[i] = Math.min(D[i], D[i - 1] + moneys[1]);

			// 3달권
			if (i - 3 >= 0)
				D[i] = Math.min(D[i], D[i - 3] + moneys[2]);
		}

		return Math.min(D[12], moneys[3]); // 12월까지의 최소비용과 1년권을 비교
	}

}
