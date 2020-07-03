package aj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _12 {
    static int T;
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static ArrayList<Integer> visited;
    static int answer;
    static int allRoute;

    static void dfs(int y, int x, int sum) {
        // 기저
        if (y == n - 1 && x == m - 1) {
            allRoute += 1;
            answer = Math.max(answer, sum + map[y][x]);
            return;
        }
        if (y >= n || x >= m) return;

        // 이동하기
        if (!visited.contains(map[y][x])) {
            visited.add(map[y][x]);
            // 오른쪽
            dfs(y, x + 1, sum + map[y][x]);
            // 아래
            dfs(y + 1, x, sum + map[y][x]);
            visited.remove(Integer.valueOf(map[y][x]));
        }

//        // 아래
//        if (!visited.contains(map[y][x])) {
//            visited.add(map[y][x]);
//            dfs(ny, x, sum + map[y][x]);
//            visited.remove(Integer.valueOf(map[y][x]));
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new ArrayList<>();
            answer = -1;
            allRoute = 0;
            dfs(0, 0, 0);

            System.out.println("#" + t + " " + allRoute + " " + answer);
        }
    }
}
