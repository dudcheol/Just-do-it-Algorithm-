package algorithm.swea.mockTest;

import java.util.*;
import java.io.*;

public class _2115_벌꿀채취 {
	
	static int N,M,C,map[][],max;
	static boolean[][] p1Visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			p1Visited = new boolean[N][N];
			int[] get = new int[M];
			int idx = 0;
			int sumMax = Integer.MIN_VALUE;
			int p1 = 0, p2 = 0;;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					boolean isOk = true;
					for (int d = 0; d < M; d++) { // 가로로 M길이만큼 true처리
						if(j+d >= N) {
							isOk = false;
							break;
						}
						p1Visited[i][j+d] = true;
						get[idx++] = map[i][j+d];
					}
					
					if(isOk) {
						// true된 애들로 가장 큰 수익 찾기
						max = Integer.MIN_VALUE;
						powerset(0, 0, 0, get);
						p1 = max;
						
						max = Integer.MIN_VALUE;
						p2Pick(); // p2 벌통 고르기
						p2 = max;
						
						sumMax = Math.max(sumMax, p1+p2);
					} else {
						for (int d = 0; d < M; d++) {
							if(j+d >= N) continue;
							p1Visited[i][j+d] = false;
						}
					}
					idx = 0;
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(sumMax).append('\n');

		}
		System.out.print(sb);
		
	}

	private static void p2Pick() {
		int[] get = new int[M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean isOk = true;
				for (int d = 0; d < M; d++) {
					if(j+d >= N || p1Visited[i][j+d]) {
						isOk = false;
						break;
					}
					get[idx++] = map[i][j+d];
				}
				
				if(isOk) { // 조합찾기
					powerset(0, 0, 0, get);
				}
				idx = 0;
			}
		}
	}

	private static void powerset(int k, int m, int res, int[] get) {
		if(k==M) {
			max = Math.max(max, res);
			return;
		}
		
		int add = get[k];
		if(m + add <= C) { // 선택
			powerset(k+1, m+add, res+(int)Math.pow(get[k], 2), get);			
		}
		
		powerset(k+1, m, res, get); // 비선택
	}

}
