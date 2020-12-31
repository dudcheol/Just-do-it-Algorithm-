package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _13549_숨바꼭질3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
            int ret = Integer.compare(o1[1],o2[1]);
            if (ret==0) Integer.compare(Math.abs(o1[0]-K), Math.abs(o2[0]-K));
            return ret;
        });

        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;
        pq.offer(new int[]{N,0});
        while (true){
            int[] poll = pq.poll();
            int pos = poll[0];
            int time = poll[1];

            if (pos==K){
                System.out.println(time);
                return;
            }

            int right=pos+1, left=pos-1, telpo=pos*2;

            if (right<=100000 && visited[right] > time+1){
                visited[right] = time+1;
                pq.offer(new int[]{right, time+1});
            }
            if (left>=0 && visited[left] > time+1){
                visited[left] = time+1;
                pq.offer(new int[]{left, time+1});
            }
            if ((0<=telpo&&telpo<=100000) && visited[telpo]>time){
                visited[telpo] = time;
                pq.offer(new int[]{telpo, time});
            }
        }
    }
}
