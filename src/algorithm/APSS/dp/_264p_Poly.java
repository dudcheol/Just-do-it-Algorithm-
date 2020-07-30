package algorithm.APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 어려워서..거의 보면서 풀었다..
 */
public class _264p_Poly {
    static int MOD = 10 * 1000 * 1000;
    static int[][] cache = new int[101][101];

    // n개의 정사각형으로 이루어졌고, 맨 위 가로줄에 first개의
    // 정사각형을 포함하는 폴리오미노의 수를 반환
    static int poly(int n, int first) {
        // 기저 n == first
        if (n == first) return 1;
        // 메모이제이션
        if (cache[n][first] != -1) return cache[n][first];

        int ret = 0;
        for (int second = 1; second <= n - first; ++second) {
            int add = second + first - 1;
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }
        return cache[n][first] = ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());

            for (int[] c : cache) Arrays.fill(c, -1);

            int answer = 0;
            for (int i = 1; i <= n; i++) {
                answer += poly(n, i);
                answer %= MOD;
            }

            System.out.println(answer);
        }
    }
}
