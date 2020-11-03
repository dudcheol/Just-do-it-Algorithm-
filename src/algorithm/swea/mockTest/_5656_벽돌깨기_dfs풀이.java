package algorithm.swea.mockTest;

import java.io.*;
import java.util.*;

public class _5656_벽돌깨기_dfs풀이 {

	static int N, W, H, map[][];
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int min;
	private static int boomCnt;
	private static ArrayList<Integer> list;

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
			int total = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) total++;
				}
			}

			// 최대한 많은 블록을 깨서 남은 블록의 갯수 구하기 => 블록의 수가 가장 적게 남은 경우
			dfs(0, total);

			sb.append('#').append(test_case).append(' ').append(min).append('\n');

		}

		System.out.print(sb);

	}

	private static boolean dfs(int k, int remainCnt) {
		if(remainCnt == 0) {
			min = 0;
			return true; // 더이상 부술 벽돌이 없는 경우 dfs를 더 진행할 필요 없다
		}
		
		if (k == N) {
			min = Math.min(min, remainCnt);
			return false;
		}

		int[][] tmp = new int[H][W];
		deepcopy(map, tmp);

		for (int c = 0; c < W; c++) {
			int r = 0;
			while(r<H && map[r][c]==0) ++r;
			if(r==H) continue;
			boomCnt = 0;
			boom(r, c, map[r][c]);
			fillblank();
			if(dfs(k + 1, remainCnt - boomCnt)) return true;
			deepcopy(tmp, map);
		}
		
		return false;
	}
	
	// 기존에 존재하는 배열을 사용
	private static void deepcopy(int[][] origin, int[][] copied) {
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				copied[i][j] = origin[i][j];
	}

	private static void boom(int r, int c, int cnt) {
		boomCnt++;
		map[r][c] = 0; // 왠만하면 여기서 방문 처리
		if(cnt==1) return;
		
		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for (int k = 1; k < cnt; k++) {
				nr += dy[d];
				nc += dx[d];
				if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == 0) continue;
				boom(nr,nc,map[nr][nc]);
			}
		}
	}
	
	// 빈 칸 채우기 더 쉬운 버전
	private static void fillblank(){
		for (int c = 0; c < W; c++) { // 열고정
			list = new ArrayList<>();
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
		list.clear(); // 매번 생성하는 것 보다 메모리 사용량 효율 상승
	}

}
