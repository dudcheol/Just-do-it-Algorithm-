package algorithm.swea.D4;

import java.io.*;
import java.util.*;

// 4004ms
public class _5643_키순서_BFS {
	
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
				gtBFS(i);
				ltBFS(i);
				if(gtCnt+ltCnt == N-1)answer++;
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	// 자신보다 큰 학생 따라 탐색
	private static void gtBFS(int start) { // 탐색의 출발 학생 번호
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int k = q.poll();
			for (int i = 1; i <= N; i++) {
				if(adj[k][i]==1 && !visited[i]) { // k -> i 나보다 큰 놈
					q.offer(i);
					visited[i] = true;
					gtCnt++; // 자신보다 큰 애들의 수만 센다
				}
			}
		}
	}
	
	// 자신보다 작은 학생 따라 탐색
	private static void ltBFS(int start) { // 탐색의 출발 학생 번호
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int k = q.poll();
			for (int i = 1; i <= N; i++) {
				if(adj[i][k]==1 && !visited[i]) { // i -> k 나보다 작은 놈
					q.offer(i);
					visited[i] = true;
					ltCnt++; // 자신보다 작은 애들의 수만 센다
				}
			}
		}
	}
}
