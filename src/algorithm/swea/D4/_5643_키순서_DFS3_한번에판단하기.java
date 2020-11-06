package algorithm.swea.D4;

import java.io.*;
import java.util.*;

// 1,366 ms
public class _5643_키순서_DFS3_한번에판단하기 {
	
	private static int N,M,adj[][];
	private static int[] gtCnt, ltCnt;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine()); // 학생 수 : 정점 수
			M = Integer.parseInt(br.readLine()); // 관계 수 : 간선 수
			
			adj = new int[N+1][N+1];
			gtCnt = new int[N+1];
			ltCnt = new int[N+1];
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
				dfs(i, i, new boolean[N+1]);
			}
			for (int i = 1; i <= N; i++) {
				if(gtCnt[i] + ltCnt[i] == N-1) answer++;
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	// src보다 큰 학생 따라 탐색하며 src 자신이 누구보다 작은지도 함께 판단.
	private static void dfs(int src, int cur, boolean[] visited) { // src : 맨처음 출발했던 학생 번호
		visited[cur] = true; // src < cur
		for (int i = 1; i <= N; i++) {
			if(adj[cur][i]==1 && !visited[i]) { // src < cur < i
				gtCnt[src]++;
				ltCnt[i]++;
				dfs(src, i, visited);
			}
		}
	}
	
}
