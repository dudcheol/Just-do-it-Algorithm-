package APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _254p_TriPathCnt {
    static int n;
    static int[][] tri;
    static int[][] cache;
    static int[][] cache2;

    /**
     * y,x 위치에서 구할 수 있는 최대 경로 합
     *
     * @param y
     * @param x
     * @return
     */
    static int getMaxPath(int y, int x) {
        // 기저
        if (x >= n || y >= n)
            return 0;

        // 메모이제이션
        if (cache[y][x] != -1)
            return cache[y][x];

        return cache[y][x] = tri[y][x] + Math.max(getMaxPath(y + 1, x), getMaxPath(y + 1, x + 1));
    }

    /**
     * y,x 위치에서 갈 수 있는 최대 경로의 개수
     *
     * @param y
     * @param x
     * @return
     */
    static int getTriPathCnt(int y, int x) {
        // 기저
        if (x >= n || y >= n)
            return 1;
        if (getMaxPath(y + 1, x) == 0 || getMaxPath(y + 1, x + 1) == 0) {
            return 1;
        }

        // 메모이제이션
        if (cache2[y][x] != -1) return cache2[y][x];

        if (getMaxPath(y + 1, x) > getMaxPath(y + 1, x + 1)) {
            return cache2[y][x] = getTriPathCnt(y + 1, x);
        } else if (getMaxPath(y + 1, x) < getMaxPath(y + 1, x + 1)) {
            return cache2[y][x] = getTriPathCnt(y + 1, x + 1);
        } else {
            return cache2[y][x] = getTriPathCnt(y + 1, x) + getTriPathCnt(y + 1, x + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            n = Integer.parseInt(br.readLine());
            tri = new int[n][n];
            cache = new int[n][n];
            cache2 = new int[n][n];

            for (int[] c : cache)
                Arrays.fill(c, -1);

            for (int[] c2 : cache2)
                Arrays.fill(c2, -1);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j <= i; j++) {
                    tri[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println(getTriPathCnt(0, 0));
        }
    }
}
