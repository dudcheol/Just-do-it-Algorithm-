package algorithm.APSS.stakQueueDequeue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 조세푸스문제 큐로 풀기
 */
public class Josephus {
    static List<Integer> findArrive(int n, int k) {
        Queue<Integer> q = new LinkedList<>();

        // q 초기화
        for (int i = 1; i <= n; i++)
            q.offer(i);

        // 1번째 사람이 먼저 자살
        q.poll();

        int cnt = 0;
        while (q.size() > 2) {
            if (cnt == k-1) {
                q.poll(); // k번째 사람이 죽는다
                cnt = 0;
            }
            q.offer(q.poll()); // k번째 사람이 아니므로 죽이지 않고 다시 큐에 넣는다
            cnt++;
        }

        return (List<Integer>)q;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            // 3≤N≤1000, 1≤K≤1000
            int n = Integer.parseInt(st.nextToken().trim());
            int k = Integer.parseInt(st.nextToken().trim());

            List<Integer> answer = findArrive(n, k);
            //두 사람의 번호를 오름차순으로 출력
            Collections.sort(answer);
            System.out.println(answer.get(0) + " " + answer.get(1));
        }
    }
}
