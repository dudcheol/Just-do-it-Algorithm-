package technic.그래프.프림;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _1251_하나로_크루스칼 {

	private static int N;
	private static int[] x;
	private static int[] y;
	private static double E;
	private static int[] V;
	private static double answer;

	private static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight > o.weight) {
				return 1;
			} else if (this.weight < o.weight) {
				return -1;
			} else
				return 0;
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

			kruscal();

			sb.append('#').append(test_case).append(' ').append(Math.round(answer)).append('\n');
		}

		System.out.print(sb);

	}

	private static void kruscal() {
		makeSet();

		// 존재하는 간선들 만들기
		ArrayList<Edge> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				list.add(new Edge(i, j, E * (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2))));
			}
		}

		// 간선 정렬하기 (가중치가 작은 것들 순으로)
		Collections.sort(list);

		// 모든 간선을 확인하면서 신장트리를 완성할 N-1개의 간선 고르기
		int cnt = 0;
		answer = 0;
		for (Edge edge : list) {
			if (cnt == N - 1)
				break;

			int from = edge.from;
			int to = edge.to;

			if (!union(from, to))
				continue;

			cnt++;
			answer += edge.weight;
		}
	}

	/** from과 to를 연결 */
	private static boolean union(int from, int to) {
		int rootOfFrom = find(from);
		int rootOfTo = find(to);

		if (rootOfFrom == rootOfTo) // 이미 같은 집합에 속해있음 -> 연결할 경우 사이클형성
			return false;
		V[rootOfTo] = rootOfFrom; // to를 from에 합침
		return true;
	}

	private static int find(int x) {
		if (V[x] == x)
			return x;
		return V[x] = find(V[x]);
	}

	private static void makeSet() {
		V = new int[N];
		for (int i = 0; i < N; i++) {
			V[i] = i;
		}
	}

}
