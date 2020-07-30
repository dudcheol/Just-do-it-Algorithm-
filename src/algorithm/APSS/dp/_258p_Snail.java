package algorithm.APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * _256p_SnailsClimbing의 달팽이 문제에서 약간 응용된 문제
 *  (변경1) 매일 비가 올 확률이 50%에서 75%로 바뀌었다면?
 *  (변경2) 맑은날 1m, 비오는날 2m를 올라갈 수 있음
 */
public class _258p_Snail {
    static int MAX_N = 1000;
    static int n; // 우물의 깊이
    static int m; // 장마 기간의 길이
    static double[][] cache;

    static double climbSnail(int day, int climb) {
        // 기저 : m일이 모두 지난 이후
        if (day == m) return climb >= n ? 1 : 0;
        // 메모이제이션
        if (cache[day][climb] != -1) return cache[day][climb];
        return cache[day][climb] = (0.25 * climbSnail(day + 1, climb + 1)) + (0.75 * climbSnail(day + 1, climb + 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        /**
         * 주의!!
         * 되도록 cache는 한 번만 생성 하도록 한다. 크기가 매우 클 경우 치명적일 수 있기 때문이다.
         * cache의 크기가 정적 임에도 불구하고, 반복문 내에서 매번 생성하면 '시간초과'가 날 수 있다.
         */
        cache = new double[MAX_N][2 * MAX_N]; // 현재 이동한 거리에 위치해 있을 때 m일 안에 n이상 가있을 수 있는 경우의 수를 메모이제이션 함으로써 다음에 동일한 부분문제가 발생할 때 계산을 줄일 수 있음

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            for (double[] c : cache) Arrays.fill(c, -1);

            System.out.println(climbSnail(0, 0));
        }
    }
}
