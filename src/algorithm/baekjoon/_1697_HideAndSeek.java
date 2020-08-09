package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1697_HideAndSeek {
    static int n, k;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈 위치
        k = Integer.parseInt(st.nextToken()); // 동생 위치
        answer = 0;

        int tmp = 0;
        if(n > k){
            tmp = k;
            k = n;
            n = tmp;
        }

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
                q.offer(polled * 2);
                q.offer(polled + 1);
                q.offer(polled - 1);
            }
            level++;
        }
    }
}
