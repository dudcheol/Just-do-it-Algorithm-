package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5643_키순서_플로이드워셜_개선 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int N,M,map[][],answer;
		
		for (int test_case = 1; test_case <= TC; test_case++) {

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			answer = 0;

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a인 학생이 b인 학생보다 키가 작다
				map[a][b] = 1;
			}

			for (int k = 1; k <= N; k++) { // 경유지
				for (int i = 1; i <= N; i++) { // 출발지
					if(map[i][k] == 0) continue; // 출발지->경유지로 가는 경로가 없다면 continue
					
					for (int j = 1; j <= N; j++) { // 도착지
//						if (map[i][k] == 1 && map[k][j] == 1) { // - 1117ms
						if (map[k][j] == 1) { // - 876ms
							map[i][j] = 1;
						}
					}
				}
			}

			loop: for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j || map[i][j] == 1 || map[j][i] == 1)
						continue;
					else
						continue loop;
				}
				answer++;
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}
}