package algorithm.jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _1082_화염에서탈출 {
	static final int FIRE = 1;
	static final int JAEWOO = 2;
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static LinkedList<int[]> q;
	static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		visited = new boolean[R][C];
		min = Integer.MAX_VALUE;

		q = new LinkedList<>();

		int[] pos = new int[2];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*') {
					q.addLast(new int[] { FIRE, i, j });
				} else if (map[i][j] == 'S') {
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		q.addLast(new int[] { JAEWOO, pos[0], pos[1] });
		visited[pos[0]][pos[1]] = true; // 현재 재우의 위치 방문처리

		// 비어있는 칸은 '.'로 표시되고, 불은 '*'로, 바위는 'X'로 표시되어있다.
		// 용사의 집은 'D'로 표현되고, 재우가 처음에 서있는 위치는 'S'로 표시된다.

		bfs();
		System.out.println(min == Integer.MAX_VALUE ? "impossible" : min);
	}

	private static void bfs() {
		int timer = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			boolean isMovable = false;
			while (size-- != 0) {
				int[] cur = q.poll();
				int who = cur[0];
				int y = cur[1];
				int x = cur[2];

				for (int d = 0; d < 4; d++) { // 상하좌우 이동
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || nx < 0 || ny >= R || nx >= C)
						continue;

					if (who == JAEWOO) {
						if (visited[ny][nx] || map[ny][nx] == '*' || map[ny][nx] == 'X')
							continue;
						if (map[ny][nx] == 'D') { // 용사의집 도착
							min = timer;
							return;
						}
						visited[ny][nx] = true;
						q.addLast(new int[] { JAEWOO, ny, nx });
						isMovable = true;
					} else {
						if (map[ny][nx] == 'X' || map[ny][nx] == 'D' || map[ny][nx] == '*') // 이미 불이 번진 위치를 또 확인할 필요 없음 
							continue;
						map[ny][nx] = '*';
						q.addLast(new int[] { FIRE, ny, nx });
					}
				}
			} // end of move
			if(!isMovable) return;
			timer++;
		}
	}

}
