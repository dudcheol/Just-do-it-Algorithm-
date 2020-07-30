package algorithm.kakaoBlindTest._2018;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 프렌즈4블록
 * 시작: 17:15
 * 끝: ??
 * 걸린시간: 오래걸림...
 * 답 본 다음 풀었음
 * 
 * [배운 것]
 * - List를 배열로 선언 :List<T>[] l = new ArrayList[size];
 * - 선언된 List배열마다 객체 생성해줘야함 l[i] = new ArrayList<T>();
 * 
 * [고쳐야할 점]
 * - 이차원배열은 머리로 생각하지 말고 꼭 손으로 써보면서 해야함
 * - 반복문에서 ++를 넣어서 그것을 통해 참조할땐 인덱스를 벗어나지는 않는지까지 생각해야함
 * - 중첩반복문 실수하지않게 조심. 어떤게 안에 있어야하는지 등 고민해보면서 해야함. 자주바뀔수록 안에있음
 * - 일부러 어렵게 푸는 경향이 있는 것 같음. 배열이 주어지면 배열을 최대한 쓰려고 해보자.
 * - 시나리오가 있다면 귀찮더라도 출력해보면서 진행하는게 좋을 것 같다.
 */

public class kakao_blind_2018_6 {
	// board는 길이 n인 문자열 m개의 배열
	static int solution(int m, int n, String[] board) { // 2 ≦ n, m ≦ 30
		// 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복
		int answer = 0;

		char[][] pan = new char[m][n];
		for (int i = 0; i < pan.length; i++) {
			pan[i] = board[i].toCharArray();
		}

		while (true) {
			int prevAnswer = answer;
			boolean[][] visited = new boolean[m][n];

			// 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임
			for (int r = 0; r < m - 1; r++) {
				for (int c = 0; c < n - 1; c++) {
					// 2x2찾기
					if (pan[r][c] == ' ')
						continue;
					if (pan[r][c] == pan[r + 1][c] && pan[r][c] == pan[r][c + 1] && pan[r][c] == pan[r + 1][c + 1]) {
						visited[r][c] = true;
						visited[r + 1][c] = true;
						visited[r][c + 1] = true;
						visited[r + 1][c + 1] = true;
					}
				}
			}

			// 같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					if (visited[r][c]) {
						pan[r][c] = ' ';
						answer++;
					}
				}
			}

			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					System.out.print(pan[r][c]);
				}
				System.out.println();
			}
			System.out.println();

			// 블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
			for (int r = m - 1; r >= 0; r--) {
				for (int c = 0; c < n; c++) {
					if (pan[r][c] == ' ') {
						for (int i = r - 1; i >= 0; i--) {
							if (pan[i][c] == ' ')
								continue;
							pan[r][c] = pan[i][c];
							pan[i][c] = ' ';
							break;
						}
					}
				}
			}

			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					System.out.print(pan[r][c]);
				}
				System.out.println();
			}
			System.out.println("-----------------");

			if (answer == prevAnswer) {
				break;
			}
		}

		return answer; // 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작
	}

	public static void main(String[] args) {
//		int m = 4;
//		int n = 5;
//		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" }; // return 14
//		int m = 3;
//		int n = 1;
//		String[] board = { "A", "A","A" }; // return 0
//		int m = 4;
//		int n = 5;
//		String[] board = { "CEBDE", "AWADE", "AZQBF", "LCUBF" }; // return 0
		int m = 6;
		int n = 6;
		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" }; // return 15

		System.out.println(solution(m, n, board));
	}
}
