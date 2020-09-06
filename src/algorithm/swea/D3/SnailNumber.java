package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * algorithm.swea 1954 달팽이 숫자
 */
public class SnailNumber {
    private static int T, N;
    private static int[][] map;
    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};

    private static void simulate(int k, int y, int x, int dir) {
        map[y][x] = k;

        if (k >= N * N) return;

        int ny = y + dy[dir];
        int nx = x + dx[dir];

        if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != 0)
            simulate(k, y, x, (dir + 1) % 4);
        else simulate(k + 1, ny, nx, dir);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            simulate(1, 0, 0, 0);

            sb.append("#").append(tc).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}

/* test case
10
1
2
3
4
5
6
7
8
9
10
*/