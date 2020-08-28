package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N! , 2거듭제곱 값을 미리 계산해 놓고 재사용
public class _3234_준환이의양팔저울_DP풀이 {
    static int answer;
    static int N;
    static int[] scales;
    static boolean[] selected;

    static int[] fact; // factorial 값을 미리 계산해서 저장해 둔 배열 1! ~ 9!
    static int[] pow; // 2의 거듭제곱 미리 계산
    static int totalWeight; // 모든 추들의 무게 합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            selected = new boolean[N];
            answer = 0;
            scales = new int[N];

            fact = new int[N];
            pow = new int[N];

            // fact, pow 배열에 값 저장
            fact[0] = fact[1] = pow[0] = 1;

            for (int i = 0; i < N - 1; i++) {
                fact[i + 1] = (i + 1) * fact[i]; // 4! = 4 * 3!
                pow[i + 1] = pow[i] * 2;
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                scales[i] = Integer.parseInt(st.nextToken());
                totalWeight += scales[i];
            }

            check(0, 0, 0, totalWeight);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    // remains : 아직 저울에 올라가지 않은 남은 추들의 무게
    // cnt : 저울에 올린 추의 갯수
    private static void check(int cnt, int left, int right, int remains) {
        // remains .. 남아있는 추들을 전부 right에 올려도 left 무게를 넘지 않는다면
        // 하나하나 올리지 않고 한번에 체크
        // 남아있는 추 갯수? N-cnt
        if (remains + right <= left) {
            // 추가 4개 있을 때 구할 수 있는 경우의 수 : pow[4] * fact[4]
            answer += pow[N-cnt] * fact[N-cnt]; // N-cnt개의 추를 가지고 만들어지는 경우의 수를 계산
            return;
        }

        if (cnt == N) { // 모든 추를 올린 경우
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!selected[i]) { // i번째는 사용 중이 아니라면
                selected[i] = true;

                check(cnt + 1, left + scales[i], right, remains - scales[i]);

                if (right + scales[i] <= left)
                    check(cnt + 1, left, right + scales[i], remains - scales[i]);

                selected[i] = false;
            }
        }
    }
}