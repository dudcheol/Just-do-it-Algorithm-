package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2589_보물섬 {
	
	private static int R;
	private static int C;
	private static char[][] map;
	private static boolean[][] visited;
	private static int max;
	private static int[] dy = { -1, 1, 0, 0 }; // 상하좌우로 이동
	private static int[] dx = { 0, 0, -1, 1 }; // 상하좌우로 이동

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로크기 입력받음
		C = Integer.parseInt(st.nextToken()); // 가로크기 입력받음
		map = new char[R][]; // 세로크기를 이용하여 map 초기화
		for (int i = 0; i < R; i++) { // 세로크기만큼 반복문 돌기
			map[i] = br.readLine().toCharArray(); // readLine한 것을 charAry로 변환하여 map에 저장
		}

		// 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
		max = 1; // 적어도 두 곳에 나뉘어 묻혀있으므로, 바로 옆에 보물이 묻혀있는 경우만 있을 수 있으므로 max는 1로 시작함
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'L') { // 땅을 발견하면 현재 위치를 시작으로 bfs 진행
					visited = new boolean[R][C]; // visited 배열 초기화
					bfs(i, j); // 현재 위치 인덱스를 전달하는 bfs 메소드 실행
				}
			}
		}
		System.out.println(max);
	}
	
	private static void bfs(int i, int j) { // 시작 인덱스는 i,j
		Queue<int[]> q = new LinkedList<int[]>(); // 큐 생성

		visited[i][j] = true; // 시작점 방문처리
		q.offer(new int[] { i, j, 0 }); // 시작점 큐에 삽입

		while (!q.isEmpty()) { // 큐가 빌 때까지 반복문 진행
			int[] polled = q.poll(); // 큐에 있는 원소를 빼냄 (현재 위치 좌표)
			int y = polled[0]; // 현재 위치 x 좌표
			int x = polled[1]; // 현재 위치 y 좌표
			int dist = polled[2]; // 시작점에서 현재 위치까지의 거리 dist

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; // 상하좌우 순서로 이동한 새로운 좌표 ny
				int nx = x + dx[d]; // 상하좌우 순서로 이동한 새로운 좌표 nx
				if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[ny][nx] || map[ny][nx] == 'W') {
					// 현재 위치에서 이동할 ny, nx 좌표가 map을 벗어나거나, 이미 방문했던 땅이거나, 바다인 경우
					max = Math.max(max, dist); // 기존 max값과 현재 위치까지의 거리를 비교하여 큰 값으로 max를 업데이트
					continue; // 이동불가하므로 다른 방향으로 이동하는 경우를 보기 위해 continue
				}
				// 이동가능하다면
				visited[ny][nx] = true; // 이동할 좌표 방문처리
				q.offer(new int[] { ny, nx, dist + 1 }); // 큐에 이동할 좌표 삽입
			}
		}
	}

}
