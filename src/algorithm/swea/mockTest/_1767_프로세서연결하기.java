package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1767_프로세서연결하기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] corePos;
	static int coreCnt;
//	static int[] selectCore;
//	static boolean[] isSelected;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static boolean[][] tmpMap;
	static int maxConnectCore;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			visited = new boolean[N][N];
			corePos = new int[12][2]; // Core의 개수는 최소 1개 이상 12개 이하이다.
			coreCnt = 0;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						corePos[idx][0] = i;
						corePos[idx][1] = j;
						coreCnt++;
						idx++;
					}
				}
			}
//			selectCore = new int[coreCnt];
//			isSelected = new boolean[coreCnt];
			answer = Integer.MAX_VALUE;
			maxConnectCore = Integer.MIN_VALUE;
			tmpMap = new boolean[N][N];

			// 전선은 겹치면 안됨
			// 전선은 직선으로만 설치 가능
			// 가장자리 전선은 이미 연결된 것으로 간주됨
			// 7 ≤ N ≤ 12

//			choose(0);
			connect(0,0,0);

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

//	// 먼저 연결할 코어 순서를 정함
//	private static void choose(int k) {
//		if (k == coreCnt) {
//			// 나열된 코어 연결 순서의 인덱스
//			connect(0, 0, 0);
//			return;
//		}
//
//		for (int i = 0; i < coreCnt; i++) {
//			if (isSelected[i])
//				continue;
//			isSelected[i] = true;
//			selectCore[k] = i;
//			choose(k + 1);
//			isSelected[i] = false;
//		}
//	}

	// 선택된 코어에서부터 전선 연결 시뮬레이션 시작
	private static void connect(int coreIdx, int connectCnt, int lineCnt) {
		// 기저
		if (coreIdx == coreCnt) {
			if (maxConnectCore < connectCnt) {
				maxConnectCore = connectCnt;
				answer = lineCnt;
			} else if (maxConnectCore == connectCnt) {
				answer = Math.min(answer, lineCnt);
			}
			return;
		}

		// maxConnectCore가 측정이 된 시점에서
		// 현재까지 연결된 전선에 앞으로 쭉 연결해도 maxConnectCore를 넘지못할 것이라면
		// 현재 코어에서부터는 더 볼 필요가 없어진다
		if (maxConnectCore != Integer.MIN_VALUE) {
			int expectedCore = coreCnt - coreIdx - 1; // 앞으로 더 연결할 수 있는 코어
			if (maxConnectCore - 1 > connectCnt + expectedCore) {
				return;
			}
		}

		// 4방향으로 전선 놔보기
		for (int d = 0; d < 4; d++) {
			// 첫번째 코어일땐 하나의 프로세서를 새롭게 만들어서 경우의 수 확인
//			if (coreIdx == 0) {
//				tmpMap = new boolean[N][N];
//			}
			// 전선 놓기
			int[] cur = corePos[coreIdx];
			int y = cur[0];
			int x = cur[1];
			boolean isConnected = false;
			int cnt = 0; // 전선의 길이
			while (true) {
				y += dy[d];
				x += dx[d];
				if (y < 0 || x < 0 || y >= N || x >= N) {
					// 전선 연결하려는 곳이 끝까지 도달했다면
					isConnected = true;
					break;
				}
				if (map[y][x] == 1 || tmpMap[y][x]) {
					// 이미 다른 코어가 있거나 이미 다른 전선이 설치된 경로라면
					// 연결해온 흔적을 지우고 false 처리
					while (true) {
						y -= dy[d];
						x -= dx[d];
						if (y == cur[0] && x == cur[1])
							break;
						tmpMap[y][x] = false;
					}
					isConnected = false;
					break;
				}
				tmpMap[y][x] = true;
				cnt++;
			}

			// 현재 방향으로 전선연결 시도해봤으면 다음 코어로 이동
			if (isConnected) {
				connect(coreIdx + 1, connectCnt + 1, lineCnt + cnt);
				// 다른방향으로 연결해보기 위해 방문처리 취소
				while (true) {
					y -= dy[d];
					x -= dx[d];
					if (y == cur[0] && x == cur[1])
						break;
					tmpMap[y][x] = false;
				}
			} else {
				connect(coreIdx + 1, connectCnt, lineCnt);
			}
		}
	}

//	private static int[][] deepCopy(int[][] origin) {
//		int[][] tmp = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			tmp[i] = origin[i].clone();
//		}
//		return tmp;
//	}
}
