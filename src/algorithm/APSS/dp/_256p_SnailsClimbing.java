package algorithm.APSS.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 깊이가 n미터인 우물의 맨 밑바닥에 달팽이가 있다. 이 달팽이는 우물의 맨 위까지 기어올라가고 싶다.
 * 날이 맑으면 하루 2미터를, 비가 오면 하루 1미터를 올라간다.
 * m일 간 각 날씨에 비가 올 확률히 정확히 50%라고 가정할 때, m일 안에 달팽이가 우물 끝까지 올라갈 수 있는 확률은 얼마일까?
 * n(1≤n≤1000) m(1≤m≤1000)
 */
public class _256p_SnailsClimbing {
    static int MAX_N = 1000;
    static int n; // 우물의 깊이
    static int m; // 장마 기간의 길이
    static int[][] cache;

    static int climbSnail(int day, int climb) {
        // 기저 : m일이 모두 지난 이후
        if (day == m) return climb >= n ? 1 : 0;
        // 메모이제이션
        if (cache[day][climb] != -1) return cache[day][climb];
        return cache[day][climb] = climbSnail(day + 1, climb + 1) + climbSnail(day + 1, climb + 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cache = new int[MAX_N][2 * MAX_N + 1]; // 현재 이동한 거리에 위치해 있을 때 m일 안에 n이상 가있을 수 있는 경우의 수를 메모이제이션 함으로써 다음에 동일한 부분문제가 발생할 때 계산을 줄일 수 있음
            for (int[] c : cache) Arrays.fill(c, -1);

            System.out.println(climbSnail(0, 0) / Math.pow(2, m));
        }
    }
}
