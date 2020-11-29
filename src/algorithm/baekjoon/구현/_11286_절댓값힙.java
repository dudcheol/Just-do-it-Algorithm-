package algorithm.baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _11286_절댓값힙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1,o2)->{
            int ret = Integer.compare(Math.abs(o1), Math.abs(o2));
            if(ret==0) ret = Integer.compare(o1,o2);
            return ret;
        });
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input==0) sb.append(pq.isEmpty()? 0 : pq.poll()).append('\n');
            else pq.offer(input);
        }
        System.out.print(sb);
    }
}
