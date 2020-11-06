package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5643_키순서_플로이드워셜_교수님풀이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int N,M,adj[][],answer;
		
		for (int test_case = 1; test_case <= TC; test_case++) {

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj = new int[N + 1][N + 1];
			answer = 0;

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a인 학생이 b인 학생보다 키가 작다
				adj[a][b] = 1;
			}

			for (int k = 1; k <= N; k++) { // 경유지
				for (int i = 1; i <= N; i++) { // 출발지
					if(i==k) continue;
					
					for (int j = 1; j <= N; j++) { // 도착지
						if(k==j || i==j || adj[i][j]==1) continue;
						// i<k && k<j ==> i<j
						if(adj[i][k]==1 && adj[k][j]==1) adj[i][j] = 1;
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[i][0] += adj[i][j];
					adj[0][j] += adj[i][j];
				}
			}
			
			for (int k = 1; k <= N; k++) {
				if(adj[k][0] + adj[0][k] == N-1) answer++;
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}
}