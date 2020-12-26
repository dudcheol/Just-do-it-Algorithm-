package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _1325_효율적인_해킹 {
    private static int[] cnt;
    private static ArrayList[] vertexs;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        vertexs = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            vertexs[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            vertexs[a].add(b);
        }

        cnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            dfs(i);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < cnt[i])
                max = cnt[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == max)
                sb.append(i).append(' ');
        }

        System.out.println(sb);
    }

    private static void dfs(int cur) {
        ArrayList<Integer> vertex = vertexs[cur];

        cnt[cur]++;
        visited[cur] = true;
        for (int i = 0; i < vertex.size(); i++) {
            int next = vertex.get(i);
            if (visited[next]) continue;
            dfs(next);
        }
    }
}
