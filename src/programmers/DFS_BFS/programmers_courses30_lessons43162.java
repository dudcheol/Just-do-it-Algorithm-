package programmers.DFS_BFS;

/*
 * 16:15 시작 ~ 18:00
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers
 * 네트워크의 개수를 return
 */

public class programmers_courses30_lessons43162 {
	static int answer = 0;
	static int[] visitedC;

	static class Computer {
		int name;
		int[] link;
		boolean isCountable;

		Computer(int name, int[] link) {
			this.name = name;
			this.link = link;
			this.isCountable = true;
		}

		void isNotCountable() {
			this.isCountable = false;
		}
	}

	static void dfs(Computer c, int[][] computers) {
		int myName = c.name;
		int[] myLink = c.link;
		if (c.isCountable == true) {
			answer++;
		}
		for (int i = 0; i < myLink.length; i++) {
			if (myLink[i] == 0 || visitedC[i] == 1)
				continue; // 0이면 연결안된 것이므로 제외, visitedC가 1이면 이미 방문했던 것이므로 제외
			if (i == myName) {
				continue;
			}
			visitedC[i] = 1;// 연결됐는지 확인해본 컴퓨터임을 알림
			Computer lc = new Computer(i, computers[i]);
			lc.isNotCountable(); // 동일한 네트워크 이므로 세면 안됨
			dfs(lc, computers);
		}
	}

	static int solution(int n, int[][] computers) {
		visitedC = new int[n];

		if (n == 1)
			return 1;

		// 모든 컴퓨터를 조회
		for (int i = 0; i < computers.length; i++) {
			if (visitedC[i] == 1)
				continue;
			dfs(new Computer(i, computers[i]), computers);
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 4;
//		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
//		int[][] computers = { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } };
		int[][] computers = { { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, {0, 1, 1, 0 }, { 1, 1, 0, 1 } };
//		int[][] computers = {{1,1},{1,1}};
		// return 2
		System.out.println(solution(n, computers));
	}
}
