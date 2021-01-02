package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2567_싸이클 {
    private static int N,P;
    private static int[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        visited = new int[1001];

        dfs(N);

        int cnt = 0;
        for (int i = 0; i <= 1000; i++) { // 나누었을때 0도 있음
            if (visited[i]>1)cnt++;
        }
        System.out.println(cnt);
    }

    private static void dfs(int rest) {
        if (visited[rest]>1){
            return;
        }
        visited[rest]++;
        dfs(rest*N%P);
    }
}
