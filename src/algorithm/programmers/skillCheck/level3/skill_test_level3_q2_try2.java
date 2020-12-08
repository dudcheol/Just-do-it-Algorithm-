package algorithm.programmers.skillCheck.level3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class skill_test_level3_q2_try2 {
    static int[] dy = {0,1}; //우하
    static int[] dx = {1,0}; //우하
    static int[][] D;
    static boolean[][] map;
    public static int solution(int m, int n, int[][] puddles) {
        map = new boolean[n][m];
        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][1]-1][puddles[i][0]-1] = true;
        }
        D = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(D[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return Integer.compare(o1[2], o2[2]);
        });

        pq.offer(new int[]{0,0,0});
        D[0][0] = 0;

        while(!pq.isEmpty()){
            int[] p = pq.poll();
            int y = p[0];
            int x = p[1];
            int dist = p[2];
            if (y==n-1&&x==m-1)break;
            for (int d = 0; d < 2; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny<0||nx<0||ny>=n||nx>=m||map[ny][nx]) continue;
                if (D[ny][nx] > (dist + 1)%1000000007){
                    D[ny][nx] = (dist + 1)%1000000007;
                    pq.offer(new int[]{ny,nx,D[ny][nx]});
                }
            }
        }

        return D[n-1][m-1]-1;
    }

    public static void main(String[] args) {
        int m=4,n=3,puddles[][]={{2,2}};
        System.out.println(solution(m,n,puddles));
    }
}
