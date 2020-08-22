package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1:20~1:52
 */
public class _7699_SuzyTrable {
    static int R, C;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[] visited;
    static int answer = 0;

    static void dfs(int y, int x, int cnt) {
        visited[map[y][x]] = true;
        answer = Math.max(answer, cnt);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[map[ny][nx]]) continue;
            dfs(ny, nx, cnt + 1);
        }
        visited[map[y][x]] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            answer = Integer.MIN_VALUE;
            visited = new boolean['Z' + 1];
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
            }
            dfs(0, 0, 1);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

}














