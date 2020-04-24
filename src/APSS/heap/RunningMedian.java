package APSS.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 텅 빈 수열에서 시작해서 각 수가 추가될 때마다 중간값을 계산하는 프로그램을 작성
 */
public class RunningMedian {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 1 <= N <= 200,000

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // minPQ : median 뒤의 절반
            // maxPQ : median 앞의 절반 , n이 홀수일경우 maxPQ에 하나 더 넣음
            PriorityQueue<Long> minPQ = new PriorityQueue<>();
            PriorityQueue<Long> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

            // 오름차순 정렬된 큐 pq 생성
            long num = 1983;
            long answer = 0;
            for (int i = 1; i <= n; i++) {
                if (minPQ.size() == maxPQ.size())
                    maxPQ.offer(num);
                else
                    minPQ.offer(num);

                // maxPQ의 top은 minPQ의 top의 바로 직전의 수가 되어야 한다. 즉, maxPQ <= minPQ
                // 이 규칙을 어기는게 있다면?
                if (!minPQ.isEmpty() && !maxPQ.isEmpty() && maxPQ.peek() > minPQ.peek()) {
                    // 바꿔줘야 한다,, 서로의 top을 바꾸면 어떻게 될까?
                    // minPQ.peek의 maxPQ.peek과 바뀐다면, minPQ.peek은 maxPQ의 맨 앞으로 갈 것이고
                    // maxPQ.peek도 minPQ의 맨 앞으로 갈 것이다. 왜냐면 minPQ는 maxPQ보다 큰 수들을 가지고 있으니까
                    long polled_max = maxPQ.poll();
                    long polled_min = minPQ.poll();

                    maxPQ.offer(polled_min);
                    minPQ.offer(polled_max);
                }

                num = (num * a + b) % 20090711;
                answer = (answer + maxPQ.peek()) % 20090711;
            }

            // 각 테스트 케이스마다 한 줄에 N개의 중간값의 합을 20090711로 나눈 나머지를 출력합니다.
            System.out.println(answer);
        }
    }
}
