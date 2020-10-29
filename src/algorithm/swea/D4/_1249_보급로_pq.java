package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1249_보급로_pq {

	private static int N;
	private static int[][] map;
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}

			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
				return Integer.compare(o1[2], o2[2]);
			});
			pq.offer(new int[] { 0, 0, 0 });
			visited[0][0] = true;

			while (!pq.isEmpty()) {
				int[] p = pq.poll();
				int y = p[0];
				int x = p[1];
				int time = p[2];
				
				if(y==N-1 && x==N-1) {
					answer = time;
					break;
				}

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;
					visited[ny][nx] = true;
					pq.offer(new int[] { ny, nx, time + map[ny][nx] });
				}
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);

	}

}

/*
#1 2
#2 2
#3 8
#4 57
#5 151
#6 257
#7 18
#8 160
#9 414
#10 395
*/
