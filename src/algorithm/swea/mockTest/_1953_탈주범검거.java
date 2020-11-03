package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _1953_탈주범검거 {

	private static int N, M, R, C, L, map[][], answer;
	private static int[][] dy = { {}, // 0
			{ -1, 1, 0, 0 }, // 1 상하좌우
			{ -1, 1 }, // 2 상하
			{ 0, 0 }, // 3 좌우
			{ -1, 0 }, // 4 상우
			{ 1, 0 }, // 5 하우
			{ 1, 0 }, // 6 하좌
			{ -1, 0 } // 7 상좌
	};
	private static int[][] dx = { {}, // 0
			{ 0, 0, -1, 1 }, // 1 상하좌우
			{ 0, 0 }, // 2 상하
			{ -1, 1 }, // 3 좌우
			{ 0, 1 }, // 4 상우
			{ 0, 1 }, // 5 하우
			{ 0, -1 }, // 6 하좌
			{ 0, -1 } // 7 상좌
	};
	private static int[][][] connect = { // 파이프별 가르키는 방향에 따라 연결될 수 있는 다른 파이프 기록
			{},
			{{1,2,5,6},{1,2,4,7},{1,3,4,5},{1,3,6,7}}, // 1번 파이프가 0(상)방향을 가리킬때, 이동할 곳에 1,2,5,6번 파이프가 올 수 있음
			{{1,2,5,6},{1,2,4,7}},
			{{1,3,4,5},{1,3,6,7}},
			{{1,2,5,6},{1,3,6,7}},
			{{1,2,4,7},{1,3,6,7}},
			{{1,2,4,7},{1,3,4,5}},
			{{1,2,5,6},{1,3,4,5}}
	};
	private static boolean[][] visited;

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
			answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	private static void bfs() {
		visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		visited[R][C] = true;
		q.offer(new int[] { R, C });
		answer++;

		int time = 1;
		while (!q.isEmpty()) {
			if(time==L) break;
			int size = q.size();
			while (size-- > 0) {
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				int dir = map[y][x];

				for (int d = 0; d < dy[dir].length; d++) {
					int ny = y + dy[dir][d];
					int nx = x + dx[dir][d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)
						continue;
					// 다음에 있는 파이프가 연결될 수 있는 곳인지 확인 
					if(!isConnect(y, x, ny, nx, d)) continue;
					visited[ny][nx] = true;
					answer++;
					q.offer(new int[] { ny, nx });
				}
			}
			time++;
		}
	}

	private static boolean isConnect(int y, int x, int ny, int nx, int d) {
		int curPipe = map[y][x];
		int nextPipe = map[ny][nx];
		for (int i = 0; i < connect[curPipe][d].length; i++) 
			if(connect[curPipe][d][i] == nextPipe) return true;
		return false;
	}

}
