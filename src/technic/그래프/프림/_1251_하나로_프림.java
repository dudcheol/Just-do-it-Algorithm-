package technic.그래프.프림;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이 문제는 완전그래프를 사용하므로 간선 위주의 "크루스칼"보다는 정점 위주의 "프림"이 훨씬 낫다.
public class _1251_하나로_프림 {

	private static int N;
	private static long[][] adjMatrix;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());

			int[] x = new int[N];
			int[] y = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			adjMatrix = new long[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i], x[j], y[i], y[j]);
				}
			}// 인접행렬 완성 

			double E = Double.parseDouble(br.readLine());

			sb.append('#').append(test_case).append(' ').append(Math.round(E * makeMST())).append('\n');
		}
	}

	private static long makeMST() {
		boolean[] visited = new boolean[N];
		
		long result = 0;
		int cnt = 0;
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		
		
		return 0;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

}

/*
 * #1 10000 #2 180000 #3 1125000 #4 1953913 #5 27365366 #6 337122 #7
 * 711268755613 #8 280157 #9 521568761 #10 34 #11 375890356686 #12 68427157 #13
 * 21404 #14 16620885 #15 4776395492 #16 54860981981 #17 24236206202 #18 132410
 * #19 12876964085 #20 7016649393
 */
