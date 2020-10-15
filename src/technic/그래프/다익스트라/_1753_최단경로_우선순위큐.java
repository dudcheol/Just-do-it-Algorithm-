package technic.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1753_최단경로_우선순위큐 {
	static int V, E, K;
	static ArrayList<Node>[] nodes;
	static PriorityQueue<Node> pq;
	static int[] dist;

	static class Node implements Comparable<Node> {
		int num;
		int weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken()); // (1≤V≤20,000, 1≤E≤300,000)
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		nodes = new ArrayList[V + 1];
		pq = new PriorityQueue<>();
		dist = new int[V + 1]; // 시작점에서 V까지 최단거리 기록
		Arrays.fill(dist, Integer.MAX_VALUE); // 최단거리를 업데이트하기 위해 최댓값으로 초기화

		// nodes 배열에 리스트 객체 생성
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		// nodes의 인덱스가 u 이고 그 리스트 안에 추가되는 정보가 v, w
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken())]
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		bfs();

		for (int i = 1; i < dist.length; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
		}
		System.out.println(sb);

	}

	private static void bfs() {

		pq.offer(new Node(K, 0));

		dist[K] = 0; // 자기자신로의 가중치는 0

		while (!pq.isEmpty()) {
			Node polled = pq.poll();
			int num = polled.num;
			int weight = polled.weight;

			if (dist[num] < weight) // 지금까지 기록된 시작점부터 num까지 최단거리 가중치가 현재 가중치보다 작으면 현재값은 건너뜀
				continue;

			for (int i = 0; i < nodes[num].size(); i++) {
				// num에서 갈 수 있는 모든 경로를 확인한다
				Node cur = nodes[num].get(i);
				int cnum = cur.num;
				int cweight = cur.weight;

				// 시작점에서 cnum까지 가는 지금까지 기록된 최소비용이
				// 시작점에서 num까지 가는 지금까지 기록된 최소비용에서 cnum으로 가는 비용을 더한 값보다 크다면
				// num에서 cnum으로 가는 것이 더 최단거리인 것이므로 값을 업데이트한다
				if (dist[cnum] > dist[num] + cweight) {
					dist[cnum] = dist[num] + cweight;
					pq.offer(new Node(cnum, dist[cnum]));
				}
			}
		}
	}
}
