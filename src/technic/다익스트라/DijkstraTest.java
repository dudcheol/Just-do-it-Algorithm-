package technic.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(in.readLine());
        int start = 0;
        int end = V - 1;
        final int INF = Integer.MAX_VALUE;

        int[][] matrix = new int[V][V];
        int[] distance = new int[V]; // 출발지에서 자신까지 오는 최단거리
        boolean[] visited = new boolean[V]; // 처리한 정점 여부 관리

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < V; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(distance, INF);
        distance[start] = 0; // 시작점 간선 비용0. 현재 제일 작은 값. 시작점에서 출발해서 자신까지 이르는 최소 비용(최단 거리)

        int min = 0, current = 0;

        for (int i = 0; i < V; i++) {
            // 1단계 : 방문하지 않은 정점들 중 출발지에서 자신까지 오는 비용이 최단인 정점을 경유지로 선택
            min = INF;

            // 모든 정점을 대상으로 시작점에서 출발해서 최소 비용을 갖는 정점과 그 값 찾기
            for (int j = 0; j < V; j++) {
                if (!visited[j] && min > distance[j]) { // 방문하지 않은 정점 중에 간선 비용이 min값보다 더 작다면
                    min = distance[j];// 최소 간선 비용
                    current = j;// 최소 간선 비용을 갖는 정점의 인덱스. current 번째 정점.
                }
            }

            visited[current] = true;// 선택 정점(현재 최소값을 갖는 정점) 방문 처리
            if (current == end) break; // 선택 정점이 도착정점이면 탈출

            // 2단계 : 선택된 current 정점을 경유지로 해서 아직 방문하지 않은 다른 정점으로의 최단거리비용 계산하여 유리하면 update
            for (int j = 0; j < V; j++) {
                // min ==> distance[current]
                // j에 방문하지 않았고, current에서 j로 가는 경로가 있고,
                // min(집에서 current까지 오는 가장 적은 비용) + current에서 j로 가는 비용이 (즉, 경유지) 기존의 값보다 작은 경우에
                if (!visited[j] && matrix[current][j] != 0 && distance[j] > min + matrix[current][j]) {
                    distance[j] = min + matrix[current][j];// 업데이트한다.
                }
            }
        }
        System.out.println(distance[end]);
    }
}
