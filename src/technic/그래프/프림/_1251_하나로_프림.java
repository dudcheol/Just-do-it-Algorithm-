package technic.그래프.프림;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이 문제는 완전그래프를 사용하므로 간선 위주의 "크루스칼"보다는 정점 위주의 "프림"이 훨씬 낫다.
public class _1251_하나로_프림 {

	private static int N; // 섬의 수
	private static double E;
	private static int[] x;
	private static int[] y;
	private static boolean[] visited;
	private static double answer;
	private static double[] minEdge;
	private static double[][] costs;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());

			x = new int[N];
			y = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			// i->j로 가는 비용 초기화
			costs = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					costs[i][j] = costs[j][i] = (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)) * E;
				}
			}

			visited = new boolean[N];
			minEdge = new double[N];

			Arrays.fill(minEdge, Double.MAX_VALUE);

			answer = 0;

			makeMST();

			sb.append('#').append(test_case).append(' ').append(Math.round(answer)).append('\n');
		}

		System.out.print(sb);
	}

	private static void makeMST() {

		minEdge[0] = 0.0; // 시작점 최소간선비용은 0

		for (int c = 0; c < N; c++) { // MST 정점 n개 , 간선 n-1개

			// 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
			double min = Double.MAX_VALUE;
			int minVertex = 0;

			for (int i = 0; i < N; i++) {
				if (visited[i] || min <= minEdge[i]) // minEdge보다 min이 작거나 같으면 볼 필요없음(최소를 찾는 것이므로)
					continue;

//				int _x = x[i];
//				int _y = y[i];
//				// c -> i 환경부담금
//				double tax = (Math.pow(x[c] - _x, 2) + Math.pow(y[c] - _y, 2)) * E;
//
//				if (min > tax) {
//					min = tax;
//					minVertex = i;
//				}
				min = minEdge[i];
				minVertex = i;
			}

			answer += min;
			visited[minVertex] = true;

			// 선택된 최소비용정점 기준으로 신장트리에 포함되지 않은 다른 정점으로의 비용 계산하여 최소값 갱신
			for (int i = 0; i < N; i++) {
				if (!visited[i] && minEdge[i] > costs[minVertex][i]) {
					minEdge[i] = costs[minVertex][i];
				}
			}

		}
	}
}