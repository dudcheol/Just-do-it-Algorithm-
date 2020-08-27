package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1987_Alphabet {
    static int r, c;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0}; //말 상하좌우로 이동
    static int[] dx = {0, 0, -1, 1}; //말 상하좌우로 이동
    static boolean[] visited;
    static int answer;

    //새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다
    //말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][];
        visited = new boolean['Z' + 1];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1);
        System.out.println(answer);
    }

    private static void dfs(int y, int x, int cnt) {
        answer = Math.max(answer, cnt);
        visited[map[y][x]] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= r || nx >= c || visited[map[ny][nx]]) continue;
            dfs(ny, nx, cnt + 1);
        }
        visited[map[y][x]] = false;
    }
}
