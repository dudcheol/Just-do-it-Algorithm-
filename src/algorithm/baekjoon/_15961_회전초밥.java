package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        // 초밥 가지수 배열 생성
        boolean[] visited = new boolean[d + 1];

        // 회전 초밥 벨트 돌면서 나온 숫자를 index로 visited 처리함
        int max = 0;
        int cur = 0;
        int cursor = 0;
        do {
            int idx = chobobs[cursor];
            if (visited[idx]) {
                if (!visited[c]) cur++;
                max = Math.max(cur, max);
                cur = 0;
                Arrays.fill(visited, false); // 다시 초기화한 뒤 현재것 방문처리
                visited[idx] = true;
                cur++;
            } else {
                cur++;
                visited[idx] = true;
            }
        } while ((++cursor % N) != 0);

        if (max > d) {
            max = d;
        }

        if (max > k) {
            max = k;
        }

        System.out.println(max);

        // cnt늘려가다가 visited == true인거 만나면 중복되는 거니까 answer 업데이트함(단, 큰 값으로)
        // 단, 회전초밥인 것을 감안해서 맨 마지막부터 시작해서 다시 앞으로 가는 경우도 생각해야함 % 쓰면될듯 싶음

        // answer가 가지수보다 크면 가지수가 답 아니면 answer가 답


        //벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.

        //원래 회전 초밥은 손님이 마음대로 초밥을  고르고,
        // 먹은 초밥만큼 식대를 계산하지만,
        // 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다.

        //각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고,
        // 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다.
        // 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다.
    }
}
