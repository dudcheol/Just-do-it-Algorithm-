package baekjoon.BruteForce;

/*
 * 백준 - 테트로미노
 * 시작 : 10:35
 * 끝 : 12:30 -20분
 * 시간 : 1시간 35분
 * 
 * for문을 사용할 때 범위 항상 예의주시
 * [배운 것]
 * - 이 문제의 경우 dfs로 풀 수 있는 문제였다. ㅜ모양을 제외한 나머지가 dfs로 가능했음
 * - bit배열로도 가능한 문제였음.. 블록모양별로 배열을 만든 후,
 *   paper와 비교해가서 진행할 수 있다.. 힌트 : block[k][y][x] * paper[sy + y][sx + x]
 */
import java.util.*;
import java.io.*;

public class _14500_Tetromino {
	static int[][] paper;
	static int rowIdx, colIdx;
	static int max;

	// 작대기 모양 - 회전가능
	private static void I(int i, int j) {
		if (i <= rowIdx - 3) {
			int sero = paper[i][j] + paper[i + 1][j] + paper[i + 2][j] + paper[i + 3][j];
			max = Math.max(max, sero);
		}

		if (j <= colIdx - 3) {
			int garo = paper[i][j] + paper[i][j + 1] + paper[i][j + 2] + paper[i][j + 3];
			max = Math.max(max, garo);
		}
	}

	// L,T 모양 - 회전가능, 대칭가능
	private static void LandT_sero(int i, int j) {
		if (i <= rowIdx - 2 && j <= colIdx - 1) {
			// 왼쪽세로가 3칸
			int leftSeroBetter = paper[i][j] + paper[i + 1][j] + paper[i + 2][j];
			for (int k = i; k < i + 3; k++) {
				max = Math.max(max, leftSeroBetter + paper[k][j + 1]);
			}

			// 오른쪽 세로 3칸
			int rightSeroBetter = paper[i][j + 1] + paper[i + 1][j + 1] + paper[i + 2][j + 1];
			for (int k = i; k < i + 3; k++) {
				max = Math.max(max, rightSeroBetter + paper[k][j]);
			}
		}
	}

	private static void LandT_garo(int i, int j) {
		if (i <= rowIdx - 1 && j <= colIdx - 2) {
			// 위쪽 가로 3칸
			int upGaroBetter = paper[i][j] + paper[i][j + 1] + paper[i][j + 2];
			for (int k = j; k < j + 3; k++) {
				max = Math.max(max, upGaroBetter + paper[i + 1][k]);
			}

			// 아래쪽 가로 3칸
			int downGaroBetter = paper[i + 1][j] + paper[i + 1][j + 1] + paper[i + 1][j + 2];
			for (int k = j; k < j + 3; k++) {
				max = Math.max(max, downGaroBetter + paper[i][k]);
			}
		}
	}

	private static void Z_sero(int i, int j) {
		if (i <= rowIdx - 2 && j <= colIdx - 1) {
			int mid = paper[i + 1][j] + paper[i + 1][j + 1];
			max = Math.max(max, mid + paper[i][j + 1] + paper[i + 2][j]);
			max = Math.max(max, mid + paper[i][j] + paper[i + 2][j + 1]);
		}
	}

	private static void Z_garo(int i, int j) {
		if (i <= rowIdx - 1 && j <= colIdx - 2) {
			int mid = paper[i][j + 1] + paper[i + 1][j + 1];
			max = Math.max(max, mid + paper[i + 1][j] + paper[i][j + 2]);
			max = Math.max(max, mid + paper[i][j] + paper[i + 1][j + 2]);
		}
	}

	// 네모모양
	private static void N(int i, int j) {
		if (i >= rowIdx)
			return;
		else if (j >= colIdx)
			return;
		max = Math.max(max, paper[i][j] + paper[i + 1][j] + paper[i][j + 1] + paper[i + 1][j + 1]);
	}

	public static void main(String[] args) throws Exception {
		InputStream in;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		rowIdx = N - 1;
		colIdx = M - 1;

		paper = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(input[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				N(i, j);
				I(i, j);
				LandT_garo(i, j);
				LandT_sero(i, j);
				Z_garo(i, j);
				Z_sero(i, j);
			}
		}

		System.out.println(max);
	}
}
