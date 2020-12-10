package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1495_대각선_지그재그 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dy = {1, -1}; // 좌하, 우상
        int[] dx = {-1, 1}; // 좌하, 우상

        int[][] map = new int[n][n];
        int num = 1;
        int d = 0;
        int ny = 0, nx = 0;
        for (int k = 0; k < n; k++) {
            if (k % 2 == 0) {
                ny = 0;
                nx = k;
            } else {
                ny = k;
                nx = 0;
            }

            for (int i = 0; i < k + 1; i++) {
                map[ny][nx] = num++;
                ny += dy[d];
                nx += dx[d];
            }
            d = (d + 1) % 2;
        }

        for (int k = 0; k < n-1; k++) {
            if ((n+k)%2==0){//위에서시작
                ny=k+1;
                nx=n-1;
            }else{//아래서시작
                ny=n-1;
                nx=k+1;
            }

            for (int i = 0; i < n-k-1; i++) {
                map[ny][nx] = num++;
                ny += dy[d];
                nx += dx[d];
            }
            d = (d + 1) % 2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
