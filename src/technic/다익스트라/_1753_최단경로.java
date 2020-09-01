package technic.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1753_최단경로 {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        int end = V;
        int[][] graph = new int[V + 1][V + 1];
        boolean[] visited = new boolean[V + 1];
        int[] distance = new int[V + 1];
        final int INF = Integer.MAX_VALUE;

        Arrays.fill(distance, INF);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int stt = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (graph[stt][dest] == 0 || graph[stt][dest] > cost)
                graph[stt][dest] = cost;
        }

        // 시작 정점은 무조건 0 (자기 자신이므로)
        distance[start] = 0;

        int min = 0, current = 0;

        for (int i = 1; i < V; i++) {
            // start에서 경유없이 해당 정점으로 갈 수 있는 최단거리 구하기
            min = INF;

            for (int j = 1; j < V; j++) {
                if (!visited[j] && min > distance[j]) {
                    min = distance[j];
                    current = j;
                }
            }

            visited[current] = true;
            if (start == end) break;

            // 경유했을 때 더 빠르면 업데이트하기
            for (int j = 1; j < V; j++) {
                if (!visited[j] && graph[current][j] != 0 && distance[j] > min + graph[current][j]) {
                    distance[j] = min + graph[current][j];
                }
            }
        }

        for (int i = 1; i < distance.length; i++) {
            sb.append(distance[i] == INF ? "INF" : distance[i]).append('\n');
        }

        System.out.print(sb);

    }// end of main
}
