package algorithm.swea.D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1247_OptimalPath {
    static int n; // 고객의 수
    static Point company;
    static Point home;
    static Point[] customers;
    static boolean[] visited;
    static int[] selected;
    static int answer;

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            n = Integer.parseInt(br.readLine());
            visited = new boolean[n];
            selected = new int[n];
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            customers = new Point[n];

            for (int i = 0; i < n; i++) {
                customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // 총 이동거리가 가장 짧은 경로 구하기
            perm(0);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    private static void perm(int k) {
        if (k == n) {
            // 회사에서 출발해서 고객 모두 방문하고 집으로 돌아가는 경로 찾기
            int path = Math.abs(company.x - customers[selected[0]].x) + Math.abs(company.y - customers[selected[0]].y);
            for (int i = 0; i < n - 1; i++) {
                path +=
                        Math.abs(customers[selected[i]].x - customers[selected[i + 1]].x)
                                + Math.abs(customers[selected[i]].y - customers[selected[i + 1]].y);
            }
            path += Math.abs(home.x - customers[selected[n - 1]].x) + Math.abs(home.y - customers[selected[n - 1]].y);
            answer = Math.min(answer, path);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[k] = i;
            perm(k + 1);
            visited[i] = false;
        }
    }
}

/*
#1 200
#2 304
#3 265
#4 307
#5 306
#6 366
#7 256
#8 399
#9 343
#10 391
 */
