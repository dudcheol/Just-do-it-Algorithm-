package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _5656_벽돌깨기 {

	static int N, W, H, map[][];
//	static boolean[][] visited;
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

	private static boolean dfs(int k) {
		int result = getRemain();// 남은 블록의 수 세기
		
		if(result == 0) {
			min = 0;
			return true; // 더이상 부술 벽돌이 없는 경우 dfs를 더 진행할 필요 없다
		}
		
		if (k == N) {
			min = Math.min(min, result);
			return false;
		}

		int[][] tmp = new int[H][W];
		deepcopy(map, tmp);

		for (int i = 0; i < W; i++) {
			bfs(i);
			if(dfs(k + 1)) return true;
			deepcopy(tmp, map);
		}
		return false;
	}

	private static int getRemain() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 계속해서 배열을 생성함 -> 메모리, 시간 낭비
	private static int[][] deepcopy(int[][] origin) {
		int[][] ret = new int[H][W];
		for (int i = 0; i < H; i++) {
			ret[i] = origin[i].clone();
		}
		return ret;
	}
	
	// 기존에 존재하는 배열을 사용
	private static void deepcopy(int[][] origin, int[][] copied) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copied[i][j] = origin[i][j];
			}
		}
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
		if (map[y][x] > 1)
			q.offer(new int[] { y, x, map[y][x] - 1 });
		map[y][x] = 0;

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int ny = p[0];
			int nx = p[1];
			int range = p[2];

//			visited[ny][nx] = true; // map[ny][nx]를 0으로 변경하므로 이것 자체가 visited역할을 함. 따라서 visited는 없어도됨.

			if (range == 0)
				continue; // range == 0이면 파급효과가 없으므로 생략해도됨

			for (int d = 0; d < 4; d++) {
				ny = p[0];
				nx = p[1];

				for (int r = 0; r < range; r++) {
					ny += dy[d];
					nx += dx[d];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == 0)
						continue;

					if (map[ny][nx] > 1)
						q.offer(new int[] { ny, nx, map[ny][nx] - 1 });

					map[ny][nx] = 0;
				}
			}
		}

		// 빈 칸 메꾸기
		fillblank();
	}

	// 빈 칸 채우기 개선
	private static void fillblank(){
		// 모든열의 아래부터 빈칸 찾기
		for (int c = 0; c < W; c++) { // 열고정
			int r = H - 1;
			while (r > 0) {
				if (map[r][c] == 0) {
					int nr = r - 1; // 직전행
					while (nr > 0 && map[nr][c] == 0)
						--nr; // 처음만나는 벽돌 찾기
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				--r;
			}
		}
	}
	
	// 빈 칸 채우기 더 쉬운 버전
	private static void fillblank2(){
		for (int c = 0; c < W; c++) { // 열고정
			ArrayList<Integer> list = new ArrayList<>();
			int r;
			for (r = H-1; r >= 0; r--) { // 0이 아닌 벽돌 발견 시 리스트에 담음
				if(map[r][c] > 0) {
					list.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			r = H;
			for(int b : list) { // 맨 밑부터 리스트에 담긴 벽돌을 집어넣음
				map[--r][c] = b;
			}
		}
	}

}
