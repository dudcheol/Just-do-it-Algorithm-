package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _3124_MST {
    static int v, e;
    static ArrayList<Edge> edges;
    static int[] nodes;
    static long answer;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) {
                return 1;
            } else if (this.cost < o.cost) {
                return -1;
            } else return 0;
        }
    }

    static int find(int x) {
        if (nodes[x] == x) {
            return x;
        }
        return nodes[x] = find(nodes[x]);
    }

    static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return false;
        nodes[yRoot] = xRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            nodes = new int[v + 1];
            answer = 0;

            // make set
            for (int i = 1; i <= v; i++) {
                nodes[i] = i;
            }

            // 연결
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long cost = Long.parseLong(st.nextToken());

                edges.add(new Edge(from, to, cost));
            }

            Collections.sort(edges);

            int cnt = 0;
            for (Edge edge : edges) {
                if (cnt == v - 1) break;

                if (find(edge.from) == find(edge.to)) {
                    continue;
                }

                union(edge.from, edge.to);
                answer += edge.cost;
                cnt++;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}