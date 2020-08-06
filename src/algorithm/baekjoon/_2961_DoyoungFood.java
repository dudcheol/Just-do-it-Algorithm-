package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2961_DoyoungFood {
    static boolean[] visited;
    static int n;
    static int[][] flavors;
    static int answer;

    static void mixFood(int k, int selectCnt, int idx) {
        if (k == selectCnt) {
            // 선택된 음식들만 계산하기
            int s = 1;
            int b = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    s *= flavors[i][0];
                    b += flavors[i][1];
                }
            }
            answer = Math.min(answer, Math.abs(s - b));
            return;
        }

        for (int i = idx; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            mixFood(k + 1, selectCnt, i + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        flavors = new int[n][];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            flavors[i] = new int[]{s, b};
        }

        for (int i = 1; i <= n; i++) {
            mixFood(0, i, 0);
        }

        System.out.println(answer);
    }
}
