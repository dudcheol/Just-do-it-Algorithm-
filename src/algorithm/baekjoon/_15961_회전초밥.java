package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _15961_회전초밥 {
    static int N, d, k, c; //접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
    static int[] chobobs;
    //2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        chobobs = new int[N];
        for (int i = 0; i < N; i++) {
            chobobs[i] = Integer.parseInt(br.readLine());
        }

        Queue<Integer> q = new LinkedList<>();

        int idx = 0;
        int answer = 0;
        while (idx < N * 2) {
            int cur = chobobs[idx++ % N];

            if (q.contains(cur)) {
                // 큐에 현재 초밥이 포함되어 있다면 제거해주어야 함
                while (true) {
                    if (cur == q.poll()) break;
                }
            }

            if (q.size() < k) {
                // 큐가 연속해서 먹는 접시의 수(k)보다 작다면 큐에 초밥을 넣음
                q.offer(cur);
            } else {
                q.poll();
                q.offer(cur);
            }

            // 큐 안에 있는 초밥의 종류의 수로 answer 업데이트
            int kind = q.size();
            if (!q.contains(c)) kind += 1;
            answer = Math.max(answer, kind);
        }

        System.out.println(answer);
    }
}
