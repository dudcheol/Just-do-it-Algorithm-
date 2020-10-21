package algorithm.etc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class 행렬의영역 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int n;
	static int[][] map;
	static int answer;

	private static void solution(int sizeOfMatrix, int[][] matrix) {
		n = sizeOfMatrix;
		map = matrix;
		answer = 0;

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < sizeOfMatrix; i++) {
			for (int j = 0; j < sizeOfMatrix; j++) {
				if (map[i][j] == 1) {
					list.add(dfs(i,j)+1);
				}
			}
		}
		
		Collections.sort(list);

		System.out.println(list.size());
		for (int l : list) {
			System.out.print(l + " ");
		}
	}

	private static int dfs(int i, int j) {
		int ret = 0;
		map[i][j] = 0;
		for (int d = 0; d < 4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 0)
				continue;
			if (map[ny][nx] == 1) {
				ret += dfs(ny, nx) + 1;
			}
		}
		return ret;
	}

	private static class InputData {
		int sizeOfMatrix;
		int[][] matrix;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
			for (int i = 0; i < inputData.sizeOfMatrix; i++) {
				String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
				for (int j = 0; j < inputData.sizeOfMatrix; j++) {
					inputData.matrix[i][j] = Integer.parseInt(buf[j]);
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.sizeOfMatrix, inputData.matrix);
	}
}