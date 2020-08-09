package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1697_HideAndSeek {
    static int n, k;
    static int answer;
    static boolean[] visit = new boolean[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈 위치
        k = Integer.parseInt(st.nextToken()); // 동생 위치
        answer = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int polled = q.poll();

                if (polled == k) {
                    System.out.println(level);
                    return;
                }

                if (polled * 2 <= 100000 && !visit[polled * 2]) {
                    q.offer(polled * 2);
                    visit[polled * 2] = true;
                }

                if (polled + 1 <= 100000 && !visit[polled + 1]) {
                    q.offer(polled + 1);
                    visit[polled + 1] = true;
                }

                if (polled - 1 >= 0 && !visit[polled - 1]) {
                    q.offer(polled - 1);
                    visit[polled - 1] = true;
                }
            }
            level++;
        }
    }
}
