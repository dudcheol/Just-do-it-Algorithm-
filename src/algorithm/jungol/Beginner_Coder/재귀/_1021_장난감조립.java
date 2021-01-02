package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _1021_장난감조립 {
    private static int N,M;
    private static HashMap<Integer,Integer>[] nodes;
    private static int[] node;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        node = new int[N+1];
        nodes = new HashMap[N+1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new HashMap<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            nodes[from].put(to, cnt);
        }

        dfs(N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            if (node[i]==0)continue;
            sb.append(i).append(' ').append(node[i]).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int cur, int mul) {
        if (nodes[cur].size()==0){
            node[cur] += mul;
            return;
        }

        for(Map.Entry<Integer,Integer> n : nodes[cur].entrySet()){
            int no = n.getKey();
            int count = n.getValue();
            dfs(no, mul*count);
        }
    }

}
