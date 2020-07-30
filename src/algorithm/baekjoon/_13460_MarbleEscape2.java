package algorithm.baekjoon;

/*
 * 백준 - 13460 - 구슬 탈출2
 * 시작~끝/ 20:18~22:21
 * 시간/ 2시간3분
 * - 맞았다! -
 *	[간단한 해법]
	완전탐색을 이용해서 풀었다.
 *	[어떤  방식으로 접근했나?]
	1. 목표는 '10회 시도 안에 빨간 구슬을 구멍을 통해 빼내는 것'이기 때문에, basecase를 10으로 두고 빨간구슬을 왼,오른,위,아래쪽으로 움직였다
	2. 생각해보니 파란구슬도 같이 움직이겠네?하는 생각이 들어서 빨강을 먼저 움직이게 한 다음에 파랑도 움직였다
	3. 그런데 2번의 경우 #..BR#로 되어있는데 왼쪽으로 움직이면 #B..R#이 되어버린다. 원하는 모양은 #BR..#이다. 따라서 움직이는 행동을 각각 2번씩 했다.
	4. 매 시도마다 R은 없고 B는 있는지 확인했다. => 맞으면 true이므로 시도t를 answer에 넣음
 *	[해법을 찾는 데 결정적인 깨달음]
	무식하게 풀기!!! 모든 경우를 구한다는 생각을 하니 풀 수 있었다.
 *	[다른 해결 방법이 있다면?]
	이 문제는 BFS로 풀 수 있는 문제다.
	
 *	[생각해내지못한것]
	DFS를 통해 경우의 수를 만들어 주는 경우 10개의 케이스를 만들 때, 왼,왼,왼,왼,왼,왼,왼,왼,왼,왼 같이 한 쪽 기울임이 연속적으로 나오면
	구슬은 변하는 것 없이 계속 똑같은 위치에 있게된다. 따라서 DFS 메소드 solve의 매개변수로 이전에 어떤 움직임을 했나를 만들어주어서 다음번 방향을 정할 때 
	이전 값과 같은 값이 들어가지 않도록 해주어야 한다.
	-> 적용 전 메모리 150152KB, 시간 740ms
	-> 적용 후 메모리  29964KB, 시간 420ms (개선됨)
 */
import java.util.*;
import java.io.*;

public class _13460_MarbleEscape2 {
	static int answer = 9999;
	static int N, M;
	static char[][] board;

	// 시도횟수, 현재 빨간구슬 위치, 현재 파란구슬 위치, 바로 전에 시도했던 액션
	static void moveMarble(int t, int[] red, int[] blue, int prevAction) { /* [생각해내지못한것] prevAction */
		boolean redOut = true;
		boolean blueOut = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					redOut = false;
					red[0] = i;
					red[1] = j;
				} else if (board[i][j] == 'B') {
					blueOut = false;
					blue[0] = i;
					blue[1] = j;
				}
			}
		}

		if (redOut && !blueOut) { // 빨간공 나가고, 파란공은 안나간 상태면 성공
			answer = Math.min(answer, t);
			return;
		} else if (blueOut) { // 파란공이 나가버리면 실패
			return;
		}

		if (t == 10) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 서로 막고있을 경우 대비해서 액션 두번씩해주기
			if (prevAction == i)
				continue;
			int[] nextRed;
			int[] nextBlue;
			nextRed = doAction(i, red);
			nextBlue = doAction(i, blue);
			nextRed = doAction(i, nextRed);
			nextBlue = doAction(i, nextBlue);

			moveMarble(t + 1, nextRed, nextBlue, i);
			undoAction(i, red, blue);
		}
		return;
	}

	static void undoAction(int action, int[] red, int[] blue) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R' || board[i][j] == 'B') {
					board[i][j] = '.';
				}
			}
		}
		board[red[0]][red[1]] = 'R';
		board[blue[0]][blue[1]] = 'B';
	}

	static int[] doAction(int action, int[] marble) {
		if (board[marble[0]][marble[1]] == 'O')
			return marble;
		char id = board[marble[0]][marble[1]];
		board[marble[0]][marble[1]] = '.';
		int[] result = new int[2];
		switch (action) {
		case 0:// left
			for (int i = marble[1]; i >= 0; i--) {
				if (board[marble[0]][i] == 'O') {
					result[0] = marble[0];
					result[1] = i;
					break;
				} else if (board[marble[0]][i] != '.') {
					board[marble[0]][i + 1] = id;
					result[0] = marble[0];
					result[1] = i + 1;
					break;
				}
			}
			break;
		case 1:
			for (int i = marble[1]; i < M; i++) {
				if (board[marble[0]][i] == 'O') {
					result[0] = marble[0];
					result[1] = i;
					break;
				} else if (board[marble[0]][i] != '.') {
					board[marble[0]][i - 1] = id;
					result[0] = marble[0];
					result[1] = i - 1;
					break;
				}
			}
			break;
		case 2:
			for (int i = marble[0]; i >= 0; i--) {
				if (board[i][marble[1]] == 'O') {
					result[0] = i;
					result[1] = marble[1];
					break;
				} else if (board[i][marble[1]] != '.') {
					board[i + 1][marble[1]] = id;
					result[0] = i + 1;
					result[1] = marble[1];
					break;
				}
			}
			break;
		case 3:
			for (int i = marble[0]; i < N; i++) {
				if (board[i][marble[1]] == 'O') {
					result[0] = i;
					result[1] = marble[1];
					break;
				} else if (board[i][marble[1]] != '.') {
					board[i - 1][marble[1]] = id;
					result[0] = i - 1;
					result[1] = marble[1];
					break;
				}
			}
			break;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		moveMarble(0, new int[2], new int[2], -1);
		System.out.println(answer == 9999 ? -1 : answer);
	}
}
