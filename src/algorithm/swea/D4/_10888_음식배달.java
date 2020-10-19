package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _10888_음식배달 {

	private static int N;
	private static int[][] map;
	private static ArrayList<int[]> stores;
	private static ArrayList<int[]> houses;
	private static int storeCnt;
	private static boolean[] selected;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stores = new ArrayList<int[]>();
			houses = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1) {
						stores.add(new int[] { i, j });
					} else if (map[i][j] == 1) {
						houses.add(new int[] { i, j });
					}
				}
			}

			storeCnt = stores.size();
			selected = new boolean[storeCnt];
			answer = Integer.MAX_VALUE;
			dfs(0);

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}

	private static void dfs(int k) {
		if (k == storeCnt) {
			int res = 0;
			for (int i = 0; i < storeCnt; i++) {
				int[] store = stores.get(i);
				if(selected[i]) res += map[store[0]][store[1]];
			}
			for (int[] house : houses) {
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < storeCnt; i++) {
					if (selected[i]) {
						int[] store = stores.get(i);
						min = Math.min(min, Math.abs(house[0] - store[0]) + Math.abs(house[1] - store[1]));
					}
				}
				if (min == Integer.MAX_VALUE)
					return;
				res += min;
			}
			answer = Math.min(answer, res);
			return;
		}

		selected[k] = true;
		dfs(k + 1);

		selected[k] = false;
		dfs(k + 1);
	}
}