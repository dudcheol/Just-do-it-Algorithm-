package technic.그래프.프림;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1251_하나로_프림_PQ {

	private static int N;
	private static int[] x;
	private static int[] y;
	private static double E;
	private static boolean[] visited;
	private static double[] minEdge;

	private static class Edge {
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

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

			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1.weight > o2.weight)
					return 1;
				else if (o1.weight < o2.weight)
					return -1;
				return 0;
			});

			// 초기화
			for (int i = 1; i < N; i++) {
				pq.offer(new Edge(0, i, calTax(0, i)));
			}
			double answer = 0;
			minEdge[0] = 0; // 시작정점으로 가는 비용은 0이므로 0으로 초기화

			int cnt = 0;
			while (!pq.isEmpty()) {
				Edge polled = pq.poll();
				int from = polled.from;
				int to = polled.to;
				double weight = polled.weight;

				if (visited[to]) // 이미 방문했던 정점이라면 방문하지 않는다
					continue;

				visited[to] = true;
				answer += weight;
//				if (cnt++ == N - 1)
//					break;

				// 가려고 하는 곳이 처음 방문하는 곳이고, 갔을 때 기존의 비용보다 적은 비용이 든다면 큐에 삽입한다
				for (int next = 0; next < N; next++) {
					if (visited[next])
						continue;

					double nextWeight = calTax(to, next);
					if (minEdge[next] > nextWeight) {
						minEdge[next] = nextWeight;
						pq.offer(new Edge(to, next, nextWeight));
					}
				}
			}

			sb.append('#').append(test_case).append(' ').append(Math.round(answer)).append('\n');
		}

		System.out.print(sb);
	}

	private static double calTax(int from, int to) {
		return E * (Math.pow(x[from] - x[to], 2) + Math.pow(y[from] - y[to], 2));
	}

}
