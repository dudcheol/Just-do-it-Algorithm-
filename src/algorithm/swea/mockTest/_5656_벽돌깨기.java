package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _5656_벽돌깨기 {

	static int N, W, H, map[][];
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 최대한 많은 블록을 깨서 남은 블록의 갯수 구하기 => 블록의 수가 가장 적게 남은 경우
			dfs(0);

			sb.append('#').append(test_case).append(' ').append(min).append('\n');

		}

		System.out.print(sb);

	}

	private static void dfs(int k) {
		if (k == N) {
			// 남은 블록의 수 세기
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j] != 0) {
						cnt++;
					}
				}
			}
			min = Math.min(min, cnt);
			return;
		}
		
		int[][] tmp = deepcopy(map);

		for (int i = 0; i < W; i++) {
			visited=new boolean[H][W];
			bfs(i);
			dfs(k+1);
			map = deepcopy(tmp);
		}
	}

	private static int[][] deepcopy(int[][] origin) {
		int[][] ret = new int[H][W];
		for (int i = 0; i < H; i++) {
			ret[i] = origin[i].clone();
		}
		return ret;
	}

	private static void bfs(int x) {
		// k열의 0이 아닌 위치 찾기
		int y = -1;
		for (int i = 0; i < H; i++) {
			if (map[i][x] != 0) {
				y = i;
				break;
			}
		}

		if (y == -1)
			return; // 시도하지 않기

		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { y, x, map[y][x] - 1 });

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int ny = p[0];
			int nx = p[1];
			int range = p[2];
			
			map[ny][nx] = 0;
			visited[ny][nx] = true;

			for (int d = 0; d < 4; d++) {
				ny = p[0];
				nx = p[1];
				
				for (int r = 0; r < range; r++) {
					ny += dy[d];
					nx += dx[d];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == 0 || visited[ny][nx])
						continue;
					q.offer(new int[] { ny, nx, map[ny][nx] - 1 });
				}
			}
		}
		
		// 빈 칸 메꾸기
		fillblank();
	}

	private static void fillblank() {
		// 모든열의 아래부터 빈칸 찾기
		for (int i = 0; i < W; i++) {
			loop: for (int j = H-1; j >= 0; j--) {
				if(map[j][i] == 0) {
					// 0이 아닌 블럭을 끝까지 찾기
					int nj = j;
					while(true) {
						nj += dy[0];
						if(nj < 0) continue loop;
						if(map[nj][i] != 0) break;
					}
					map[j][i] = map[nj][i];
					map[nj][i] = 0;
				}
			}
		}
	}

}


/*
1
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1 

 */
