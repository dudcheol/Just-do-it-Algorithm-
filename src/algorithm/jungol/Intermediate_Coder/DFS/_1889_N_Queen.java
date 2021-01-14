package algorithm.jungol.Intermediate_Coder.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1889_N_Queen {
    private static int N, cnt;
    private static int[] dy = {-1,-1,-1,0,1,1,1,0}; // 8방
    private static int[] dx = {-1,0,1,1,1,0,-1,-1}; // 8방

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cnt = 0;

        for (int i = 0; i < N; i++) {
            dfs(0, i, new boolean[N][N]);
        }

        System.out.println(cnt);
    }

    private static void dfs(int y, int x, boolean[][] visited) {
        if (visited[y][x]) return;
        if (y==N-1){
//            System.out.println("HIT!!!!!!!!!!!!");
//            print(visited);
            cnt++;
            return;
        }

        visited[y][x] = true;

        // 방문처리
        int ny=y, nx=x, d=0;
        while (d<8){
            ny+=dy[d];
            nx+=dx[d];
            if (ny<0||nx<0||ny>=N||nx>=N) {
                d++;
                ny=y;
                nx=x;
                continue;
            }
            visited[ny][nx] = true;
        }

        for (int i = 0; i < N; i++) {
            dfs(y + 1, i, deepcopy(visited));
        }
    }

    private static boolean[][] deepcopy(boolean[][] origin) {
        boolean[][] ret = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(origin[i],0,ret[i],0,N);
        }
        return ret;
    }

    private static void print(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j]?1:0);
            }
            System.out.println();
        }
        System.out.println();
    }
}
