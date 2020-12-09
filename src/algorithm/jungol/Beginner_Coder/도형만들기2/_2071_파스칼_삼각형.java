package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2071_파스칼_삼각형 {
    private static int map[][],n,m;
    private static int[][] dy ={
            {},
            {-1,-1}, // 좌상,상
            {1,1}, // 하,우하
            {0,1}, // 우,우하
    };
    private static int[][] dx ={
            {},
            {-1,0}, // 좌상,상
            {0,1}, // 하,우하
            {1,1}, // 우,우하
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        if (m==1){
            for (int i = 0; i < n; i++) {
                map[i][0] = map[i][i] = 1;
            }
            for (int i = 2; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (cnt==1 && map[i][j]==0){
                        int sum = 0;
                        for (int d = 0; d < 2; d++) {
                            sum += map[dy[m][d]+i][dx[m][d]+j];
                        }
                        map[i][j] = sum;
                    }
                    if(map[i][j]==1)cnt++;
                    if (cnt==2) break;
                }
            }
        }else if (m==2){
            for (int i = 0; i < n; i++) {
                map[i][i]=map[i][n-1]=1;
            }
            for (int i = n-3; i >= 0; i--) {
                int cnt = 0;
                for (int j = n-1; j >= 0; j--) {
                    if (cnt==1 && map[i][j]==0){
                        int sum = 0;
                        for (int d = 0; d < 2; d++) {
                            sum += map[dy[m][d]+i][dx[m][d]+j];
                        }
                        map[i][j] = sum;
                    }
                    if(map[i][j]==1)cnt++;
                    if (cnt==2) break;
                }
            }
        }else if (m==3){
            for (int i = 0; i < n; i++) {
                if (i==n-1){
                    Arrays.fill(map[i], 1);
                    break;
                }
                map[i][i]=1;
            }
            for (int j = n-3; j >= 0; j--) {
                int cnt = 0;
                for (int i = n-1; i >= 0; i--) {
                    if (cnt==1 && map[i][j]==0){
                        int sum = 0;
                        for (int d = 0; d < 2; d++) {
                            sum += map[dy[m][d]+i][dx[m][d]+j];
                        }
                        map[i][j] = sum;
                    }
                    if(map[i][j]==1)cnt++;
                    if (cnt==2) break;
                }
            }
        }
        printMap();
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]==0?"":map[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
