package APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _259p_AsymTiling {
    static int n;
    static int[] cache = new int[200]; // 1 <= n <= 100
    static int[] cache2 = new int[100]; // 1 <= n <= 100

    static int asymTiling(int width) {
        //기저
        if (width >= n) {
            if (width == n) return 1;
            else return 0;
        }
        //메모이제이션
        if (cache[width] != -1) return cache[width];
        return cache[width] = asymTiling(width + 1) + asymTiling(width + 2);
    }

    static int asymTiling_half(int width) {
        //기저
        if (width >= n / 2) {
            if (width == n / 2) return 1;
            else return 0;
        }
        //메모이제이션
        if (cache2[width] != -1) return cache2[width];
        return cache2[width] = asymTiling_half(width + 1) + asymTiling_half(width + 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            // cache 초기화
            Arrays.fill(cache, -1);
            Arrays.fill(cache2, -1);

            n = Integer.parseInt(br.readLine());

            if (n % 2 == 0) {
                //n이 짝수일 경우
                int res1 = asymTiling(0);
                int res2 = asymTiling_half(0);
                int answer = (res1 - res2 - 1) % 1000000007;
                System.out.println(res1 + " - " + res2 + " = " + answer); // -1? 전부같은모양일경우
            } else {
                // n이 홀수일 경우
                int res1 = asymTiling(0);
                int res2 = asymTiling_half(0);
                int answer = (res1 - res2 - 1) % 1000000007;
                System.out.println(res1 + " - " + res2 + " = " + answer); // -1? 전부같은모양일경우
            }
        }
    }
}
