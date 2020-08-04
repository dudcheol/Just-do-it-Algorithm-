package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하는 함수를 작성하시오.
 */
public class _1238_Contact {
    static boolean[][] map;
    static boolean[] visited;
    static int n;
    static HashMap<Integer, Integer> hm;
    static int maxDepth;
    static int answer;

//    static void dfs(int depth, int start) {
//        boolean isLastNode = true;
//
//        visited[start] = true;
//        for (int i = 1; i <= 100; i++) {
//            if (map[start][i] && !visited[i]) {
//                isLastNode = false;
//                dfs(depth + 1, i);
//            }
//        }
//
//        if (isLastNode) {
//            hm.put(depth, Math.max(hm.getOrDefault(depth, Integer.MIN_VALUE), start));
//            maxDepth = Math.max(maxDepth, depth);
////            System.out.println(start + " is LastNode. depth = " + depth + " / maxDepth or answer = " + maxDepth + ", " + hm.getOrDefault(depth, -1));
//        }
//    }

    static void bfs(int start) {

        int current = start;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(current);
        visited[current] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            boolean isLastNode = true;
            int size = queue.size();

            while (--size >= 0){
                current = queue.poll();

                for (int i = 1; i <= 100; i++) {
                    if (map[current][i] && !visited[i]) {
                        isLastNode = false;
                        queue.offer(i);
                        visited[i] = true;
                    }
                }

                if (isLastNode) {
                    hm.put(level, Math.max(hm.getOrDefault(level, Integer.MIN_VALUE), current));
                    maxDepth = Math.max(maxDepth, level);
                }
            }
            ++level;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            map = new boolean[101][101];
            visited = new boolean[101];
            hm = new HashMap<>();
            maxDepth = Integer.MIN_VALUE;
            answer = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                map[from][to] = true;
            }

//            dfs(0, start);
            bfs(start);

            answer = hm.get(maxDepth);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}

/*
#1 17
#2 96
#3 49
#4 39
#5 49
#6 1
#7 28
#8 45
#9 59
#10 64
*/