package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1707_달팽이사각형 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[] dy = {0, 1, 0, -1}; //우하좌상
        int[] dx = {1, 0, -1, 0}; //우하좌상

        int d = 0;
        int cnt = 1;
        int size = n * n;
        int y = 0, x = -1;
        while (cnt <= size) {
            y += dy[d];
            x += dx[d];
            if (y < 0 || x < 0 || y >= n || x >= n || map[y][x] != 0) {
                y -= dy[d];
                x -= dx[d];
                d = (d + 1) % 4;
                continue;
            }
            map[y][x] = cnt++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
