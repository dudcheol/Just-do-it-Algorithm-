package algorithm.swea.D4;

import java.io.*;
import java.util.*;

// 2,897ms
public class _5643_키순서_DFS {
	
	private static int N,M,adj[][];
	private static int gtCnt, ltCnt;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine()); // 학생 수 : 정점 수
			M = Integer.parseInt(br.readLine()); // 관계 수 : 간선 수
			
			adj = new int[N+1][N+1];
			StringTokenizer st = null;
			int a,b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				gtCnt = ltCnt = 0;
				gtDFS(i, new boolean[N+1]);
				ltDFS(i, new boolean[N+1]);
				if(gtCnt+ltCnt == N-1)answer++;
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	
	// 자신보다 큰 학생 따라 탐색
	// static 변수 접근보다 parameter로 접근하는 것이 시간이 더 빠르다
	private static void gtDFS(int k, boolean[] visited) { // 현재 기준이 되는 학생번호
		visited[k] = true;
		for (int i = 1; i <= N; i++) {
			if(adj[k][i]==1 && !visited[i]) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}
	}
	
	// 자신보다 작은 학생 따라 탐색
	private static void ltDFS(int k, boolean[] visited) { // 현재 기준이 되는 학생번호
		visited[k] = true;
		for (int i = 1; i <= N; i++) {
			if (adj[i][k]==1 && !visited[i]) {
				ltCnt++;
				ltDFS(i, visited);
			}
		}
	}
}
