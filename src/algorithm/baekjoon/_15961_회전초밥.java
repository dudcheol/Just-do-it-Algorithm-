package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15961_회전초밥 {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, d, k, c; //접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
        //2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        int[] chobobs = new int[d + 1];

        // 투포인터 알고리즘 사용
        int max = 0;
        int start = 0;
        int end = k - 1;
        int cnt = 0;

        // start와 end 사이의 초밥 종류 초기화
        for (int j = start; j <= end; j++) {
            if (chobobs[input[j]] == 0) cnt++;
            chobobs[input[j]]++;
        }
        max = Math.max(max, chobobs[c] == 0 ? cnt + 1 : cnt);

        for (int i = k; i < N + k - 1; i++) {
            // 포인터 한 칸 씩 오른쪽으로 이동
            if (--chobobs[input[start]] == 0) {
                // 같은 종류 없으므로 종류 - 1;
                cnt--;
            }

            start++;
            start %= N;
            end++;
            end %= N;

            if (chobobs[input[end]]++ == 0) {
                // 오른쪽으로 이동했는데 추가된 초밥이 지금까지 목록에 없다면
                cnt++;
            }

            // 쿠폰을 이미 보유했는지 보유하지 않았는지에 따라 max 값 달라짐
            max = Math.max(max, chobobs[c] == 0 ? cnt + 1 : cnt);
        }

        System.out.println(max);
    }
}

/*
잘못 푼 이유
: 연속하면서 무조건 중복되는 초밥이 없어야 한다고 생각하여 잘못 품
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
*/
