package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1331_문자마름모 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dy = {1,1,-1,-1}; // 좌하,우하,우상,좌상
        int[] dx = {-1,1,1,-1}; // 좌하,우하,우상,좌상

        char[][] map = new char[n*2-1][n*2-1];

        int y=0,x=n-1,cnt=0;
        char c='A';
        map[y][x] = c;
        for (int k = 1; k <= n; k++) {
            cnt = n-k;
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < cnt; i++) {
                    y+=dy[d];
                    x+=dx[d];
                    if (map[y][x]>='A') y+=1;
                    c= c+1>'Z'?'A': (char) (c + 1);
                    map[y][x]=c;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n*2-1; i++) {
            for (int j = 0; j < n*2-1; j++) {
                sb.append(map[i][j]>='A'?map[i][j]:' ').append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
