package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _8382_방향전환 {
    private static final int GARO = 0;
    private static final int SERO = 1;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()), x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            sb.append('#').append(test_case).append(' ').append(bfs(x1+100, y1+100, x2+100, y2+100)).append('\n');
        }
        System.out.print(sb);
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[2][201][201];
        q.offer(new int[]{x1,y1,GARO,0});
        q.offer(new int[]{x1,y1,SERO,0});
        visited[GARO][x1][y1] = true;
        visited[SERO][x1][y1] = true;
        while (!q.isEmpty()){
            int[] poll = q.poll();
            int mx = poll[0];
            int my = poll[1];
            int dir = poll[2];
            int cnt = poll[3];
            int dirController = 0;
            if(dir==SERO) dirController += 2;

            if (mx==x2 && my==y2) return cnt;

            for (int d = 0; d < 2; d++) {
                int nx = mx + dx[d+dirController];
                int ny = my + dy[d+dirController];
                if(0<=ny&&ny<=200 && 0<=nx&&nx<=200 && !visited[dir][nx][ny]){
                    visited[dir][nx][ny] = true;
                    q.offer(new int[]{nx,ny,dir==GARO?SERO:GARO,cnt+1});
                }
            }
        }
        return 0;
    }
}
