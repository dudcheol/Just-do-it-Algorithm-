package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1337_달팽이삼각형 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        if (n<0||n>100){
            System.out.println("INPUT ERROR!");
            return;
        }

        int[][] map = new int[100][100];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], -1);
        }
        int[] dy = {1,0,-1}; //우하 좌 상
        int[] dx = {1,-1,0}; //우하 좌 상

        int y = -1;
        int x = -1;
        int dir = 0;
        int num = 0;
        int endCnt = 0;
        loop:while(true){
            y += dy[dir];
            x += dx[dir];
            if (y<0||x<0||y>=n||x>=n||map[y][x]!=-1){
                y -= dy[dir];
                x -= dx[dir];
                dir = (dir+1)%3;
                endCnt++;
                if (endCnt>=3)break loop;
                continue;
            }
            endCnt=0;
            map[y][x]=(num++)%10;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==-1) break;
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
