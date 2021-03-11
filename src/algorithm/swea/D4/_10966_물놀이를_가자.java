package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _10966_물놀이를_가자 {
    private static int N,M;
    private static char[][] map;
    private static int[][] dist;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static Queue<int[]> q;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            dist = new int[N][M];
            q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j]=='W'){
                        dist[i][j]=0;
                        q.offer(new int[]{i,j});
                    }
                }
            }

            while (!q.isEmpty()){
                int[] poll = q.poll();
                int py = poll[0];
                int px = poll[1];
                for (int d = 0; d < 4; d++) {
                    int ny=py+dy[d];
                    int nx=px+dx[d];
                    if (ny<0||nx<0||ny>=N||nx>=M||map[ny][nx]=='W') continue;
                    if (dist[ny][nx] > dist[py][px]+1){
                        dist[ny][nx] = dist[py][px]+1;
                        q.offer(new int[]{ny,nx});
                    }
                }
            }

            int sum=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    sum+=dist[i][j];
                }
            }

            sb.append('#').append(test_case).append(' ').append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
