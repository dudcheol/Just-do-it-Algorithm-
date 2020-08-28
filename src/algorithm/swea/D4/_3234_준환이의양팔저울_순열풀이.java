package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3234_준환이의양팔저울_순열풀이 {
    static boolean[] pVisited;
    static int[] pSelected;
    static int N;
    static int[] scales;
    static int answer;
    private static boolean[] sVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            pVisited = new boolean[N];
            pSelected = new int[N];
            answer = 0;

            scales = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                scales[i] = Integer.parseInt(st.nextToken());
            }

            // 순열로 순서 먼저 결정
            perm(0);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    private static void perm(int k) {
        if (k == N) {
            // pSelected에는 순열로 인해 선택된 무게추가 순서대로 담겨있음
            // 선택된 순서로 왼쪽, 오른쪽 넣어보기
            put(0, 0, 0);

            // 오답코드
//            sVisited = new boolean[N];
//            powerSet(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (pVisited[i]) continue;
            pVisited[i] = true;
            pSelected[k] = scales[i];
            perm(k + 1);
            pVisited[i] = false;
        }
    }

    private static void put(int left, int right, int k) {
        if (left < right) return;

        if (k == N) {
            answer++;
            return;
        }

        put(left + pSelected[k], right, k + 1); // 왼쪽에 추 놓기
        put(left, right + pSelected[k], k + 1); // 오른쪼겡 추 놓기
    }

    // 오답코드
    private static void powerSet(int k) {
        if (k == N) {
            int lSum = 0;
            int rSum = 0;

            for (int i = 0; i < N; i++) {
                if (sVisited[i]){ // left
                    lSum += pSelected[i];
                } else { // right
                    rSum += pSelected[i];
                }
            }

            if (lSum >= rSum){
                answer++;
            }
            return;
        }

        sVisited[k] = true;
        powerSet(k + 1);

        sVisited[k] = false;
        powerSet(k + 1);
    }
}