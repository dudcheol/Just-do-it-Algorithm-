package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2383_점심식사시간 {
    private static int N, pcnt, min;
    private static boolean[] selected;
    private static int[][] map,stairs,p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            stairs = new int[2][];
            p = new int[11][];
            pcnt = 0;
            min = Integer.MAX_VALUE;
            int sidx = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 0) continue;
                    if (map[i][j] == 1)
                        p[pcnt++] = new int[]{i,j};
                    else
                        stairs[sidx++] = new int[]{i,j,map[i][j]};
                }
            }
            selected = new boolean[pcnt];
            subset(0);
            sb.append('#').append(test_case).append(' ').append(min).append('\n');
        }
        System.out.print(sb);
    }

    private static void subset(int k) {
        if (k == pcnt) {
            simulate();
            return;
        }
        selected[k] = false;
        subset(k+1);
        selected[k] = true;
        subset(k+1);
    }

    private static void simulate() {
        int finish = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return Integer.compare(o1[0],o2[0]);
        });
        for (int i = 0; i < pcnt; i++) {
            if (selected[i]) pq.offer(new int[]{Math.abs(p[i][0]-stairs[0][0]) + Math.abs(p[i][1]-stairs[0][1]),0});
            else pq.offer(new int[]{Math.abs(p[i][0]-stairs[1][0]) + Math.abs(p[i][1]-stairs[1][1]),1});
        }

        Queue<Integer> use1 = new LinkedList<>();
        Queue<Integer> use2 = new LinkedList<>();

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            if (poll[1]==0){ // 1번 계단 간 사람
                int time = poll[0] + 1; // 계단 입구 도착 후, 1분 후에 내려갈 수 있음**
                if (use1.size()==3){ // 계단이 꽉 차있으면**
                    int outTime = use1.poll(); // 가장 처음 계단에 들어온 사람이 나간시간
                    if (outTime > time){ // 나간시간이 다른 사람이 들어온 시간보다 늦으면 들어온 사람은 기다려야 함
                        time += outTime-time;
                    }// 나간 시간이 다른 사람이 들어온 시간보다 빠르거나 같으면 들어온 사람은 기다리지 않아도 됨
                }
                time += stairs[0][2];
                finish = Math.max(finish, time);
                use1.offer(time);
            } else { // 2번 계단 간 사람
                int time = poll[0] + 1;
                if (use2.size()==3){
                    int outTime = use2.poll();
                    if (outTime > time) time+=outTime-time;
                }
                time+=stairs[1][2];
                finish = Math.max(finish, time);
                use2.offer(time);
            }
        }
        min = Math.min(min, finish);
    }
}
