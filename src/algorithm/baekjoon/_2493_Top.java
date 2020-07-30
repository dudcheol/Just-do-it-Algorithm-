package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _2493_Top {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int[] answer = new int[n + 1];

        // 스택 초기화
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(new int[]{i + 1, Integer.parseInt(st.nextToken())});
        }

        Queue<int[]> fails = new LinkedList<>();

        int[] cur = stack.pop();
        while (!stack.isEmpty()) {
            int[] target = stack.pop();

            int size = fails.size();
            for (int i = 0; i < size; i++) {
                int[] fail = fails.poll();
                if (fail[1] <= target[1]) {
                    answer[fail[0]] = target[0];
                } else {
                    fails.offer(fail);
                }
            }

            if (cur[1] <= target[1]) {
                // 송신성공
                answer[cur[0]] = target[0];
            } else {
                // 송신실패
                fails.offer(cur);
            }

            cur = target;
        }

        for (int i=1;i<=n;i++){
            sb.append(answer[i]).append(" ");
        }

        System.out.print(sb);
    }
}
