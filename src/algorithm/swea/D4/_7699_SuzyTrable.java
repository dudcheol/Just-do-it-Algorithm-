package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1:20~1:52
 */
public class _7699_SuzyTrable {
    static int R, C;
    static Character[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static ArrayList<Character> visited;
    static int answer = 0;

    static void dfs(int y, int x, int cnt) {
        visited.add(map[y][x]);
        answer = Math.max(answer, cnt);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited.contains(map[ny][nx])) continue;

            dfs(ny, nx, cnt + 1);
        }
        visited.remove(map[y][x]);
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
            map = new Character[R][C];
            visited = new ArrayList<>();
            answer = Integer.MIN_VALUE;

            for (int i = 0; i < R; i++) {
                String brs = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = new Character(brs.charAt(j));
                }
            }

            dfs(0, 0, 1);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

}














