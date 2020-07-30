package algorithm.APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _259p_AsymTiling {
    static int n;
    static int MOD = 1000000007;
    static int[] cache = new int[200]; // 1 <= n <= 100

    static int tiling(int width) {
        //기저
        if (width <= 1) return 1;

        //메모이제이션
        if (cache[width] != -1) return cache[width];
        return cache[width] = (tiling(width - 1) + tiling(width - 2)) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            // cache 초기화
            Arrays.fill(cache, -1);

            n = Integer.parseInt(br.readLine());

            /**
             * 오답노트 : 좋은시도임! 접근자체가 좋았음. 하지만 생각해낸 접근법을 구현하는 것이 부족했음
             * (ㄱ) n이 홀수인 경우는 중앙에 width/2+1인 부분에 I 모양의 타일만 놓을 수 있고
             * (ㄴ) n이 짝수인 경우는 중앙 구분 없이 양쪽이 같은 경우가 있다.
             * (ㄷ) 여기서 놓친부분은 짝수인 경우, = 모양이 width/2+1~width/2+2인 부분에 있을 수 있다는 점이다. ex) === < 이런경우도 좌우대칭임
             * 따라서 타일링할 수 있는 전체 경우에서 (ㄱ)을 빼거나 (ㄴ),(ㄷ)을 빼면 전체에서 대칭인 경우를 제외한 나머지 "비대칭인 경우"를 구할 수 있다.
             */
            // 전체 타일링 경우의 수 구하기
            int total = tiling(n);
            int answer = 0;

            if (n % 2 == 1) {
                //n이 홀수일 경우
                answer = (total - tiling(n / 2) + MOD) % MOD; // (ㄱ)
            } else {
                // n이 짝수일 경우
                answer = (total - tiling(n / 2) + MOD) % MOD; // (ㄴ)
                answer = (answer - tiling(n / 2 - 1) + MOD) % MOD; // (ㄷ)
            }

            System.out.println(answer);
        }
    }
}
