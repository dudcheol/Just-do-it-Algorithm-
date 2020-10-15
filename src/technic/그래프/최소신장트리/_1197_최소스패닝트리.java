package technic.그래프.최소신장트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1197_최소스패닝트리 {

	private static int V;
	private static int E;
	private static Edge[] edges;
	private static int[] vertexs;

	private static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		vertexs = new int[V + 1]; // index - 정점 / value - 정점의 부모정점(포함되어 있는 집합이 어디인가?)
		edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		make_set(); // // 각 정점들이 자기 자신을 가리키도록 초기화

		Arrays.sort(edges);// 간선들을 가중치를 기준으로 오름차순 정렬

		int answer = 0;
		int selectedEdgeCnt = 0;
		// 오름차순 정렬한 간선들을 하나씩 선택한다
		for (int i = 0; i < E; i++) {
			// 신장트리 : n개의 정점이 n-1개의 간선으로 모두 연결되어 있는 트리이므로, 선택된 간선의 수가 V-1개라면 종료해야 함
			if (selectedEdgeCnt == V - 1)
				break;

			Edge cur = edges[i];
			int from = cur.from;
			int to = cur.to;
			int weight = cur.weight;

			// 현재 선택한 간선을 연결했을 때, 사이클 여부를 판단
			if (!union(from, to)) { // 사이클이라면 두 정점을 연결하지 않고 다음 간선을 선택한다
				continue;
			}

			// 사이클이 아니라면 두 정점이 union()을 통해 연결되었으므로
			// 현재 간선의 가중치를 더한다
			answer += weight;
			selectedEdgeCnt++;
		}

		System.out.println(answer);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) { // 사이클이 형성됨(a와 b가 같은 집합에 속함) -> 연결하면 안된다
			return false;
		}

		// 사이클 형성안됨 -> bRoot를 aRoot에 포함시킨다(b는 bRoot에 포함되므로 bRoot를 aRoot에 포함시키면 b도 aRoot에
		// 포함되게 됨)
		vertexs[bRoot] = aRoot;
		return true;
	}

	private static int find(int x) { // x가 어느 집합에 속해있는지 찾는다
		if (vertexs[x] == x) {
			return x;
		}
		return vertexs[x] = find(vertexs[x]);
	}

	private static void make_set() {
		for (int i = 1; i <= V; i++) {
			vertexs[i] = i;
		}
	}

}
