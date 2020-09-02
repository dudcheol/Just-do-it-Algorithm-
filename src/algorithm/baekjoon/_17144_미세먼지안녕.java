package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17144_미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static int[] upAirFresherPos;
	static int[] downAirFresherPos;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] udy = { 0, -1, 0, 1 }; // 오른쪽 -> 위 -> 왼 -> 아래
	static int[] udx = { 1, 0, -1, 0 }; // 오른쪽 -> 위 -> 왼 -> 아래
	static int[] ddy = { 0, 1, 0, -1 }; // 오 -> 아 -> 왼 -> 위
	static int[] ddx = { 1, 0, -1, 0 }; // 오 -> 아 -> 왼 -> 위

	public static void main(String[] args) throws IOException {
		// input 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		upAirFresherPos = new int[2];
		downAirFresherPos = new int[2];
		upAirFresherPos[0] = -1;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					// 공기청정기
					if (upAirFresherPos[0] == -1) {
						upAirFresherPos[0] = i;
						upAirFresherPos[1] = j;
					} else {
						downAirFresherPos[0] = i;
						downAirFresherPos[1] = j;
					}
				}
			}
		}

		for (int i = 0; i < T; i++) {
			simulate();
			upAirFresherSimulate();
			downAirFresherSimulate();
		}
		
		// T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1) {
					answer += map[i][j];
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void upAirFresherSimulate() {
		// 위쪽 공기청정기 반시계방향 순환
		int y = upAirFresherPos[0];
		int x = upAirFresherPos[1];

		// 배열을 벗어날때까지 진행하다가, 벗어나면 방향을 바꾼다
		int pre = 0;
		for (int d = 0; d < 4; d++) {
			while (true) {
				y += udy[d];
				x += udx[d];
				if (y < 0 || x < 0 || y >= R || x >= C || map[y][x] == -1) {
					// 범위를 벗어났으므로 원래자리로 되돌아가게 하고 반복문 종료
					y -= udy[d];
					x -= udx[d];
					break;
				}
				// 미세먼지가 한 칸씩 이동
				int tmp = map[y][x] == -1 ? 0 : map[y][x];
				map[y][x] = pre; // 현재 위치의 미세먼지를 pre로 변경
				pre = tmp; // pre에 현재 위치 미세먼지 저장
			}
		}
	}

	private static void downAirFresherSimulate() {
		// 위쪽 공기청정기 반시계방향 순환
		int y = downAirFresherPos[0];
		int x = downAirFresherPos[1];

		// 배열을 벗어날때까지 진행하다가, 벗어나면 방향을 바꾼다
		int pre = 0;
		for (int d = 0; d < 4; d++) {
			while (true) {
				y += ddy[d];
				x += ddx[d];
				if (y < 0 || x < 0 || y >= R || x >= C || map[y][x] == -1) {
					// 범위를 벗어났으므로 원래자리로 되돌아가게 하고 반복문 종료
					y -= ddy[d];
					x -= ddx[d];
					break;
				}
				// 미세먼지가 한 칸씩 이동
				int tmp = map[y][x] == -1 ? 0 : map[y][x];
				map[y][x] = pre; // 현재 위치의 미세먼지를 pre로 변경
				pre = tmp; // pre에 현재 위치 미세먼지 저장
			}
		}
	}

	private static void simulate() {
		int[][] tmp = deepCopy(map); // 현재 상태 복사

		// 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다
		// map을 확인하여 tmp를 변경시킨다
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) { // 미세먼지가 있다면
					// 4방향 확산
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1) 
							// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
							continue;
						tmp[ny][nx] += (map[i][j] / 5); // 기존미세먼지 없애지않고 더해짐
						cnt++;
					}
					// 확산은 동시에 일어나므로 tmp값이 변경됨에 영향받지 않는 원래 값으로 계산하되, 적용은 tmp에 해야함
					// 이전 행에서 일어난 확산에 영향받아 미세먼지가 유입됐다면 그 영향 또한 기억하고 있어야 하기 때문
					tmp[i][j] -= ((map[i][j] / 5) * cnt);
				}
			}
		}

		map = tmp;
	}

	private static int[][] deepCopy(int[][] origin) {
		int[][] ret = new int[origin.length][origin[0].length];
		for (int i = 0; i < origin.length; i++) {
			ret[i] = origin[i].clone();
		}
		return ret;
	}

}
