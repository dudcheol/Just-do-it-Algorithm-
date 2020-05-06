package programmers.summerWinterCoding;

import java.util.Arrays;

public class JumpAndTeleport {
    static int N;
    static int[][] cache;

    static int solution(int n) {
        int ans = 0;

        N = n;
        cache = new int[10000][10000];
        for (int[] c : cache)
            Arrays.fill(c, -1);
        ans = minUse(1, 1);

        return ans;
    }

    static int minUse(int pos, int use) {
        // 기저
        if (pos == N) {
            return use;
        } else if (pos > N) {
            return Integer.MAX_VALUE;
        }

        if (cache[pos][use] != -1) return cache[pos][use];

        cache[pos][use] = Integer.MAX_VALUE;
        cache[pos][use] = Math.min(cache[pos][use], minUse(pos + 1, use + 1)); // 앞으로 한 칸 점프
        cache[pos][use] = Math.min(cache[pos][use], minUse(pos * 2, use)); // 현재 위치의  x2 에 해당하는 위치로 순간이동

        return cache[pos][use];
    }

    public static void main(String[] args) {
        int n = 5000;

        System.out.println(solution(n));
    }
}
