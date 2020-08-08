package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1697_HideAndSeek {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수빈 위치
        int k = Integer.parseInt(st.nextToken()); // 동생 위치
        int answer = 0;

        int tmp = 0;
        if (n > k) {
            // 항상 k가 커야함
            tmp = k;
            k = n;
            n = tmp;
        }

        while (n != k) {
            // k가 홀수라면
            if (k % 2 == 1) {
                k++;
                answer++;
            }

            if (k / 2 < n) {
                // 나누기 2를 하지 않고 계속 빼는 식으로 하는것이 이득인가?
                int notDiv = k - n;
                // 나누기 2를 한 상태에서 계속 더해가는 식으로 하는것이 이득인가?
                int div = n - k / 2;

                if (notDiv <= div) {
                    answer += notDiv;
                } else {
                    answer += div + 1;
                }
                break;
            }
            k = k / 2;
            answer++;
        }

        System.out.println(answer);
    }
}
