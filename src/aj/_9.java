package aj;

import java.util.Scanner;

public class _9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] map = new int[n][n];
        int num = 1;
        boolean change = true;
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int dir = 0;

        map[0][0] = 1;
        int ny = 0;
        int nx = 0;
        // 배열채우기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ny += dy[dir];
                nx += dx[dir];
                for (int k = 0; k < 4; k++) {
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] != 0) {
                        ny -= dy[dir];
                        nx -= dx[dir];
                        dir = (dir + 1) % 4;
                        ny += dy[dir];
                        nx += dx[dir];
                    } else {
                        break;
                    }
                }
                if (map[ny][nx] == 0) map[ny][nx] = ++num;
            }
        }

        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
