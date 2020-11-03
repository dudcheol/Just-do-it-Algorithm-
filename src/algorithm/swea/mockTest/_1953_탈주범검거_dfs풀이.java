package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _1953_탈주범검거_dfs풀이 {

	private static int N, M, R, C, L, map[][], answer;
	private static int[][] visited;
	private static String[] type = { null, "0312", // 1
			"03", // 2
			"12", // 3
			"02", // 4
			"32", // 5
			"31", // 6
			"01" // 7
	};
	private static int[] dr = { -1, 0, 0, 1 };
	private static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 뚜껑세로위치
			C = Integer.parseInt(st.nextToken()); // 뚜껑가로위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간

			map = new int[N][M];
			visited = new int[N][M];
			answer = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}

			dfs(R, C, 1);

//			sb.append('#').append(test_case).append(' ').append(getCount()).append('\n');
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int r, int c, int time) {
		if(visited[r][c] == Integer.MAX_VALUE) answer++;
		visited[r][c] = time;
		if(time==L) return;
		
		String info = type[map[r][c]];
		int dir, nr, nc;
		for (int d = 0; d < info.length(); d++) {
			dir = info.charAt(d) - '0';
			nr = r + dr[dir];
			nc = c + dc[dir];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0
					&& type[map[nr][nc]].contains(Integer.toString(3 - dir)) 
					&& visited[nr][nc] > time
					) {
				dfs(nr, nc, time + 1);
			}
		}
	}

	private static int getCount() { // 지나간 시간동안 지나온 모든 위치개수 카운트
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != Integer.MAX_VALUE)
					++count;
			}
		}
		return count;
	}

}
