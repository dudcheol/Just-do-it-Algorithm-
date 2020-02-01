package APSS.recursionAndBruteforce;

/*
 * 알고리즘 문제해결 전략 - 150p - 보글게임
 * 
 * 시작: 20:20
 * 끝:
 * 시간:
 */
import java.util.*;
import java.io.*;

public class _150p_BoggleGame {
	static String[] words;
	static boolean[] visited;
	static char[][] board;

	static void findableWord(int row, int col) {
		if (row >= 5)
			return;

		if (findWord()) {
			return;
		}

		if (row >= 0) {
			System.out.print(board[row][col]);
		}

		for (int i = 0; i < 5; i++) {
			if(row!=-1&&visited[row]) break;
			findableWord(row + 1, col + i);
		}
		visited[row] = true;

		System.out.println();
		return;
	}

	static boolean findWord() {
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine()); // 테스트케이스 수

		board = new char[5][5];
		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray();
		}

		int n = Integer.parseInt(br.readLine()); // 알고있는 단어의 수
		words = new String[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}

		findableWord(-1, 0);

		System.out.println();
		br.close();
	}
}
