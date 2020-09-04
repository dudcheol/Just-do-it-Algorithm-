package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _5653_줄기세포배양 {
	static LinkedList<Cell> map;
	static int[] dy = { -1, 1, 0, 0 }; // 상,하,좌,우 번식
	static int[] dx = { 0, 0, -1, 1 }; // 상,하,좌,우 번식

	static class Cell {
		int y;
		int x;
		int power;
		int state; // 0:죽음 1:비활성 2:활성
		int sleepTimer;
		int life;
		boolean bunsik;

		public Cell(int y, int x, int power, int state) {
			super();
			this.y = y;
			this.x = x;
			this.power = power;
			this.state = state;
			this.sleepTimer = power;
			this.life = power;
			this.bunsik = false;
		}

		@Override
		public boolean equals(Object obj) {
			Cell cell = (Cell) obj;
			return this.y == cell.y && this.x == cell.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			// 입력처리
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new LinkedList<Cell>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int power = Integer.parseInt(st.nextToken());
					if (power != 0) {
						map.add(new Cell(i, j, power, 1)); // 초기 상태에서 줄기 세포들은 비활성 상태
					}
				}
			}

			for (int i = 0; i < K; i++) { // K시간 동안 배양
				simulate();
			}

			int answer = 0;
			for (Cell cell : map) {
				if (cell.state != 0) {
					answer++;
				}
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}

	private static void simulate() {
		LinkedList<Cell> tmp = new LinkedList<Cell>();

		// 생명력 수치가 X인 줄기 세포의 경우 X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태
		for (Iterator iterator = map.iterator(); iterator.hasNext();) {
			Cell cell = (Cell) iterator.next();
			if (cell.state == 0)
				continue; // 죽은 세포라면 아무 작업도 하지 않음
			
			if (cell.sleepTimer == 0) { // 비활성시간이 끝나고
				if (!cell.bunsik) { // 아직 번식하지 않았다면 번식함
					// 활성화된 줄기 세포는 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식
					for (int d = 0; d < 4; d++) {
						int ny = cell.y + dy[d];
						int nx = cell.x + dx[d];
						// 번식된 줄기 세포는 비활성 상태
						// 하나의 그리드 셀에는 하나의 줄기 세포만 존재할 수 있기 때문에 번식하는 방향에 이미 줄기 세포가 존재하는 경우 해당 방향으로 추가적으로
						// 번식하지 않는다.
						Cell newCell = new Cell(ny, nx, cell.power, 1);
						if (!map.contains(newCell)) { // 이미 다른 세포가 있다면 번식하지 않음
							if (tmp.contains(newCell)) {// 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우
								Cell existCell = tmp.get(tmp.indexOf(newCell));
								if (newCell.power > existCell.power) {
									existCell = newCell; // 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지하게 된다.
								}
							} else { // 동시에 번식하는게 아니라면 tmp에 넣으면 됨!
								tmp.add(newCell);
							}
						}
					}
					cell.bunsik = true;
				}
				if (cell.life > 0) {
					cell.life--; // x 시간 후 죽어야 하므로 생명을 계속 깎는다
					if (cell.life == 0)
						cell.state = 0;
				}
			} else {
				cell.sleepTimer--;
				if (cell.sleepTimer == 0)
					cell.state = 2;
			}
		}

		if (tmp.size() > 0)
			map.addAll(tmp);
	}
}

/*
5
2 2 10
1 1
0 2
5 5 19
3 2 0 3 0 
0 3 0 0 0 
0 0 0 0 0 
0 0 1 0 0 
0 0 0 0 2
9 10 37
0 0 0 0 0 0 0 0 3 0 
0 0 0 0 0 0 0 0 5 3 
0 0 2 0 0 0 0 4 0 0 
3 0 0 0 0 0 4 0 0 0 
0 0 0 0 0 3 5 0 0 2 
0 0 0 0 0 0 0 0 0 5 
0 0 0 0 0 0 0 0 2 3 
0 0 0 0 0 0 0 0 0 0 
0 0 2 2 0 0 0 0 0 0 
20 18 83
0 0 0 0 0 0 0 0 0 0 0 2 0 0 6 0 0 0 
0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 
0 0 0 6 0 0 0 0 0 0 0 0 0 0 2 0 3 0 
4 0 2 0 0 0 0 0 0 0 0 0 5 0 0 0 0 3 
0 0 0 0 0 5 4 4 6 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 2 0 2 6 0 0 0 0 0 4 0 0 
4 0 0 3 0 0 0 0 0 0 0 3 0 0 0 5 0 0 
0 0 0 0 0 0 0 2 2 0 0 0 0 3 0 0 0 0 
0 0 0 0 5 0 0 0 3 0 3 0 0 4 0 0 0 0 
0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 6 0 2 0 0 0 0 0 3 0 0 0 3 0 
0 5 2 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 6 0 2 0 5 0 0 0 0 0 
5 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 6 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 4 0 0 0 0 0 0 0 0 0 0 2 0 0 0 
0 0 3 4 5 0 0 0 0 0 0 0 0 0 0 6 0 0 
2 0 0 0 0 3 0 0 0 0 0 0 0 0 0 5 0 0 
0 0 0 0 0 0 0 0 0 3 6 2 0 0 0 0 0 0 
49 43 283
0 6 0 0 0 10 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 4 0 8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 4 0 0 
0 5 0 0 0 2 0 0 0 0 0 0 8 0 0 8 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 3 7 0 0 0 0 0 0 9 0 1 0 5 0 0 1 0 0 0 0 0 0 0 0 0 0 0 9 0 7 0 0 0 0 0 0 0 0 1 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 8 0 0 0 0 0 0 0 0 0 3 0 0 0 6 0 0 0 0 6 0 0 0 0 0 0 
7 0 0 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 0 0 0 8 0 0 0 0 0 0 0 0 1 0 0 
0 9 0 0 0 0 0 0 0 0 9 6 0 2 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9 
0 0 0 0 1 0 0 0 0 0 0 3 0 0 0 0 0 0 5 0 10 0 0 0 0 0 0 0 0 0 9 4 0 0 0 0 0 0 9 0 9 0 8 
0 0 0 0 0 0 0 0 0 7 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0 0 3 2 0 1 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 3 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 7 0 0 0 2 0 0 0 0 0 0 8 0 0 0 0 10 0 0 1 7 0 8 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 2 0 0 9 0 0 0 0 0 8 0 0 0 0 0 4 0 6 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 
1 0 0 0 0 0 0 6 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 5 0 0 0 0 0 0 7 0 0 0 
8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 5 8 
0 0 0 10 0 9 0 8 0 0 0 0 0 0 2 9 0 0 0 7 2 7 0 7 0 0 0 0 2 0 4 3 0 0 0 0 0 0 0 0 0 2 0 
1 0 0 0 0 0 0 4 9 1 0 0 0 0 0 0 0 0 0 5 0 0 0 0 6 0 0 5 0 0 0 0 0 0 0 0 0 0 0 3 3 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 1 0 3 1 10 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 1 0 0 0 9 0 0 
0 0 0 0 0 0 0 10 0 0 0 0 0 0 9 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 0 1 3 0 0 
0 0 0 0 0 0 6 0 0 0 1 0 0 2 0 0 0 0 9 0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 0 0 7 7 0 0 
0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 5 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 
0 0 0 0 9 0 8 0 0 0 0 0 0 4 0 0 0 10 8 0 0 0 0 0 0 10 0 0 0 5 0 0 0 0 0 0 0 1 0 0 10 4 7 
0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 4 0 7 0 0 0 0 0 3 0 
0 0 0 0 5 0 3 0 0 0 0 0 0 0 8 1 0 0 7 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 9 0 1 0 0 0 0 10 7 0 0 0 0 0 2 0 0 7 0 0 0 0 0 0 0 7 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 8 2 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 8 0 0 7 0 2 0 0 0 0 
0 8 0 0 0 0 0 0 0 0 3 0 0 1 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 5 0 9 0 0 0 0 0 
0 0 0 0 0 0 0 0 3 5 0 0 1 0 4 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 5 0 0 4 0 0 0 0 10 8 0 0 0 
0 0 0 0 0 0 0 0 4 0 0 7 10 0 10 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 0 8 3 9 6 7 0 0 0 0 2 
0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 0 0 8 7 10 0 0 0 0 0 0 6 0 0 0 5 0 0 0 0 0 0 0 0 0 0 10 0 
7 0 0 0 8 0 0 0 8 9 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 6 0 0 5 0 0 0 0 0 0 0 0 0 0 3 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 6 0 6 0 0 0 0 3 0 0 5 3 0 0 0 0 1 9 0 6 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 7 2 0 0 0 0 0 0 0 0 0 0 5 0 0 0 0 8 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 6 
0 9 0 0 0 0 0 0 0 0 0 3 0 9 2 0 0 0 4 0 2 9 2 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0 
0 0 0 3 0 1 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 7 0 6 0 0 0 0 0 7 0 0 0 0 4 7 10 
1 0 0 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 8 0 0 0 0 0 0 0 0 3 9 2 
5 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0 6 0 0 0 0 0 0 0 0 9 0 8 0 0 0 0 0 0 0 3 0 0 0 0 0 
0 0 0 0 7 0 10 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 0 8 2 3 0 0 
0 0 0 0 0 5 0 0 6 0 0 3 0 0 0 0 0 8 0 0 6 0 0 0 8 0 0 5 0 0 0 0 8 0 0 0 0 0 0 0 5 0 1 
7 0 9 0 7 0 0 9 0 0 0 0 4 0 0 0 0 0 0 8 1 0 4 0 0 0 0 0 0 0 0 0 4 7 0 0 8 0 0 0 0 0 0 
0 0 0 1 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 2 3 1 0 0 4 0 3 10 0 0 0 5 
0 0 4 0 0 0 0 0 0 4 4 0 0 0 8 0 4 0 2 0 8 0 0 0 0 0 0 0 9 0 0 0 0 5 0 0 0 0 0 0 0 0 0 
0 0 8 0 7 0 0 0 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 6 0 0 0 0 1 0 0 0 0 4 3 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 10 3 0 0 0 0 0 3 0 
0 0 2 0 0 0 0 0 8 5 0 0 0 0 0 0 0 0 0 0 0 0 4 8 0 0 0 0 0 1 0 5 0 0 0 0 2 3 9 0 0 0 0 
0 5 8 9 0 0 0 0 0 4 0 0 0 10 0 0 0 1 0 0 0 0 0 10 0 7 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 10 0 5 0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 10 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 9 0 0 0 0 0 0 2 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 2 4 0 
0 3 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 1 0 0 0 
0 0 0 9 0 0 0 0 4 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 0 5 0 0 0 9 2 0 
0 0 0 0 0 2 0 0 0 0 0 0 10 0 0 0 0 0 2 0 0 0 8 0 0 0 0 0 0 10 0 0 0 0 0 0 7 0 0 0 0 0 0 
 */

/*
 * 정답
#1 22
#2 36
#3 90
#4 164
#5 712*/
