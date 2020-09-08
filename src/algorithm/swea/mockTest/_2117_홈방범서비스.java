package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2117_홈방범서비스 {
	static int N, M; // 도시크기, 하나의 집이 지불할 수 있는 비용 // 5 ≤ N ≤ 20
	static int[][] map;
	static ArrayList<int[]> houses;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static Queue<int[]> q;
	static boolean[][] visited;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			houses = new ArrayList<>();
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						houses.add(new int[] { i, j });
					}
				}
			}

			// 마름모모양 => 이하 거리로 판단
			// 마름모모양으로 계속 퍼뜨려서 마름모크기, 이익 기록한다
			// dfs로 계속 해보면서 이전 마름모크기보다 큰데 이익이 같으면 리턴?
			// 집이 있는 위치 기준으로 퍼트려본다

			// 각 집의 위치별로 bfs 실행?
			q = new LinkedList<int[]>();
//			for (int i = 0; i < houses.size(); i++) {
//				int[] target;
//				q.offer(target = houses.get(i));
//				visited[target[0]][target[1]] = true;
//				bfs();
//				q.clear();
//				visited = new boolean[N][N];
//			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					q.offer(new int[] {i, j});
					visited[i][j] = map[i][j] == 1;
					bfs(map[i][j]);
					q.clear();
					visited = new boolean[N][N];
				}
			}

			sb.append('#').append(test_case).append(' ').append(max == Integer.MIN_VALUE ? 1 : max).append('\n');
		}
		System.out.print(sb);
		// 마름모모양 영역
		// K가 커질수록 운영 비용 커짐
		// 운영비용 == 서비스 영역의 면적 == K * K + (K - 1) * (K - 1)
		// K >= 1 정수

		// 배열을 벗어나도 운영 비용 변경되지 않음

		// 홈 방범 서비스 받는 집은 M의 비용을 지불함
		// 손해보지 않는 "최대한 많은 집에 방범 서비스 제공"

	}

	private static void bfs(int cnt) {

		int K = 1; // 서비스 영역의 면적
		int houseCnt = cnt; // 집이 있는 칸에서 시작
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- != 0) {
				int[] polled = q.poll();
				int y = polled[0];
				int x = polled[1];

				// 큐 퍼뜨려 나가면서 집 확인하기
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;

					if (map[ny][nx] == 1)
						houseCnt++;

					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
				}

			} // end of size while
			// 서비스 전용면적 계산하기
			K++;
			if (calc(K, houseCnt) >= 0) { // 손해를 보지 않음
				max = Math.max(max, houseCnt);
			}
//			else { // 손해를 봄
//				return; // 더이상 계산하지 않는다.
//			}
		} // end of q empty
	} // end of bfs

	private static int calc(int K, int houseCnt) {
		return (houseCnt * M) - (K * K + (K - 1) * (K - 1));
	}

}
