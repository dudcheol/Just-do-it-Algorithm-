package just_do_it_Algorithm;

/*
 * 16:15 시작
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers
 * 네트워크의 개수를 return
 */

public class programmers_courses30_lessons43162 {
	static int answer = 0;

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
		if (c.isCountable == true) {
			answer++;
		}
		int myName = c.name;
		int[] myLink = c.link;
		for (int i = 0; i < myLink.length; i++) {
			if (i == myName || myLink[i] == 0)
				continue; // 자기 자신은 항상 1이므로 제외, 0이면 연결안된 것이므로 제외
			Computer lc = new Computer(myLink[i], computers[i]);
			lc.isNotCountable(); // 동일한 네트워크 이므로 세면 안됨
			dfs(lc, computers);
		}
	}

	static int solution(int n, int[][] computers) {

		// 모든 컴퓨터를 조회
		for (int i = 0; i < computers.length; i++) {
			dfs(new Computer(i, computers[i]), computers);
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		// return 2
		System.out.println(solution(n, computers));
	}
}
