package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _2493_Top {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력받으면서 진행하기
        Stack<int[]> s = new Stack<>();

        for (int i = 1; i <= n; i++) {
            int[] cur = {i, Integer.parseInt(st.nextToken())};

            while (!s.isEmpty()) {
                if (s.peek()[1] < cur[1]) {
                    // 스택의 top보다 현재 탑이 더 크다면,
                    // 다음 탑들 중 현재 탑보다 큰 탑이 있을 경우에
                    // 스택의 top보다도 당연히 클 것이므로 수신할 수 없는 탑이 된다.
                    // 따라서 스택에서 제거
                    s.pop();
                } else {
                    // 스택의 top이 수신할 수 있는 탑이므로 해당 번호를 출력
                    sb.append(s.peek()[0]).append(" ");
                    break;
                }
            }

            // 스택이 비어있으면 수신할 수 있는 탑이 없는 것이므로 0
            if (s.isEmpty())
                sb.append("0").append(" ");

            s.push(cur);
        }
        System.out.print(sb);
    }
}
