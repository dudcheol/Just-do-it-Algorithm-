package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2814_최장경로 {
    static int N,M,g[][],max;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            g = new int[N+1][N+1];
            max = 1;
            visited = new boolean[N+1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                g[from][to] = 1;
                g[to][from] = 1;
            }

            for (int i = 1; i <= N; i++) {
                go(i, 1);
            }

            sb.append('#').append(test_case).append(' ').append(max).append('\n');
        }

        System.out.print(sb);
    }

    private static void go(int node, int len) {
        max = Math.max(max, len);
        visited[node] = true;
        for (int i = 1; i <= N; i++) {
            if(!visited[i] && g[node][i] == 1){
                go(i, len+1);
            }
        }
        visited[node] = false;
    }
}
