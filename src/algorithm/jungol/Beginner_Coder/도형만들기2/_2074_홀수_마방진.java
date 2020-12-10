package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2074_홀수_마방진 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int num = 1;

        int y=0;
        int x=n/2;
        map[y][x] = num;
        while(num < n*n){
            if (num%n==0){
                y+=1;
            }else{
                y-=1;
                x-=1;
                if (y<0) y=n-1;
                if (x<0) x=n-1;
                if (y>=n) y=0;
                if (x>=n) x=0;
            }
            map[y][x]=++num;
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
