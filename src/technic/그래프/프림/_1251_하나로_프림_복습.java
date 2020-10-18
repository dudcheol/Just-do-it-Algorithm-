package technic.그래프.프림;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1251_하나로_프림_복습 {

	private static int N;
	private static int[] x;
	private static int[] y;
	private static double E;
	private static boolean[] visited;
	private static double[] minEdge;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			x = new int[N];
			y = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			visited = new boolean[N];
			minEdge = new double[N]; // 신장트리는 (정점의수 -1)개의 간선을 갖는다.
			Arrays.fill(minEdge, Double.MAX_VALUE); // minEdge의 index로 가는 최소 비용이 담겨져야 하므로 최댓값으로 초기화

			// 현재 정점c에 대하여, 다음으로 갈 수 있는 환경부담금이 가장 적게 드는 경로 선택
			double answer = 0;
			minEdge[0] = 0.0; // 시작정점으로 가는 비용은 0이므로 0으로 초기화
			for (int c = 0; c < N; c++) {
				// 환경부담금이 가장 적게 드는 경로 찾기
				double min = Double.MAX_VALUE;
				int target = 0;
				for (int next = 0; next < N; next++) {
					if (!visited[next] && min > minEdge[next]) {
						min = minEdge[next];
						target = next;
					}
				}

				// 선택된 정점을 방문처리하고, 그 정점을 기준으로 갈 수 있는 다른 정점으로까지의 비용을 업데이트한다
				visited[target] = true;
				answer += min;

				for (int next = 0; next < N; next++) {
					if (visited[next])
						continue;
					// 선택된 정점에서 다음으로 갈 정점들의 간선 비용 업데이트
					// 이때, 기존에 구했던 경로의 비용이 더 적게 든다면 업데이트하지 않음
					double cost = E * (Math.pow(x[target] - x[next], 2) + Math.pow(y[target] - y[next], 2));
					if (minEdge[next] > cost) {
						minEdge[next] = cost;
					}
				}
			}

			sb.append('#').append(test_case).append(' ').append(Math.round(answer)).append('\n');
		}

		System.out.print(sb);
	}

}

/*
#1 10000
#2 180000
#3 1125000
#4 1953913
#5 27365366
#6 337122
#7 711268755613
#8 280157
#9 521568761
#10 34
#11 375890356686
#12 68427157
#13 21404
#14 16620885
#15 4776395492
#16 54860981981
#17 24236206202
#18 132410
#19 12876964085
#20 7016649393
*/
