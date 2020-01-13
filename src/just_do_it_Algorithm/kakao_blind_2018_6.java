package just_do_it_Algorithm;

import java.util.*;

/*
 * 프로그래머스 - 코딩테스트 연습 - 2018 KAKAO BLIND RECRUITMENT - [1차] 프렌즈4블록
 * 시작: 17:15
 * 끝:
 * 걸린시간:
 * 
 * [배운 것]
 * - List를 배열로 선언 :List<T>[] l = new ArrayList[size];
 * - 선언된 List배열마다 객체 생성해줘야함 l[i] = new ArrayList<T>();
 * 
 * [고쳐야할 점]
 * - 이차원배열은 머리로 생각하지 말고 꼭 손으로 써보면서 해야함
 * - 반복문에서 ++를 넣어서 그것을 통해 참조할땐 인덱스를 벗어나지는 않는지까지 생각해야함
 */

public class kakao_blind_2018_6 {
	static int dfs(List<Character>[] pan, boolean[][] deleted, int answer) {
		int prevAnswer = answer;
		// 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임
		// 같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.
		// 2x2 블록 찾기
		for (int i = 0; i < pan.length - 1; i++) {
			// 선택된 블록의 위,오른쪽,대각선에 있는 블록을 체크해야하므로
			// 선택된 블록이 있는 배열과 다음 배열의 크기중 더 작은 것까지만 검사할 수 있다
			int size = pan[i].size() > pan[i + 1].size() ? pan[i + 1].size() : pan[i].size();
			for (int j = 0; j < size - 1; j++) {
				if (pan[i].get(j) == pan[i].get(j + 1) && pan[i].get(j) == pan[i + 1].get(j)
						&& pan[i].get(j) == pan[i + 1].get(j + 1)) {
					deleted[i][j] = true;
					deleted[i][j + 1] = true;
					deleted[i + 1][j] = true;
					deleted[i + 1][j + 1] = true;
				}
			}
		}

		// 블록 지우기
		for (int i = 0; i < deleted.length; i++) {
			for (int j = 0; j < deleted[i].length; j++) {
				int k = i, l = j;
				if (deleted[i][j]) {
					while (l < deleted[i].length && deleted[k][l]) {
						pan[i].remove(j);
						answer++;
						l++;
					}
					j = l - 1;
				}
			}
		}

		if (answer > prevAnswer) {
			answer = dfs(pan, new boolean[deleted.length][deleted[0].length], answer);
		}

		return answer;
	}

	// board는 길이 n인 문자열 m개의 배열
	static int solution(int m, int n, String[] board) { // 2 ≦ n, m ≦ 30
		// 블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
		// 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복
		int answer = 0;

		List<Character>[] pan = new ArrayList[n];
		for (int i = 0; i < pan.length; i++) {
			pan[i] = new ArrayList<>();
		}

		// 판 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				pan[i].add(board[m - 1 - j].charAt(i));
			}
		}

		boolean[][] deleted = new boolean[n][m];

		return dfs(pan, deleted, answer); // 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작
	}

	public static void main(String[] args) {
		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" }; // return 14
//		int m = 3;
//		int n = 1;
//		String[] board = { "A", "A","A" }; // return 0
//		int m = 4;
//		int n = 5;
//		String[] board = { "CEBDE", "AWADE", "AZQBF", "LCUBF" }; // return 0
//		int m = 6;
//		int n = 6;
//		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" }; // return 15

		System.out.println(solution(m, n, board));
	}
}
