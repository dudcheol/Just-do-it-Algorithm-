package algorithm.APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _252p_Tiling2 {
    static int mod = 1000000007;
    static int[] cache = new int[101];

    static int tiling(int width){
        // 기저
        if (width <= 1) return 1;
        // 메모이제이션
        if(cache[width]!=-1) return cache[width];
        return cache[width] = (tiling(width-1) + tiling(width-2)) % mod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());

            Arrays.fill(cache, -1);
            System.out.println(tiling(n));
        }
    }
}
