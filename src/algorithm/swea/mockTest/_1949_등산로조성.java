package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1949_등산로조성 {
    private static int N,K,max;
    private static int[][] map;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            max = 0;
            map = new int[N][N];
            int maxh = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxh = Math.max(maxh, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == maxh){
                        visited = new boolean[N][N];
                        dfs(i,j,1, true);
                    }
                }
            }

            sb.append('#').append(test_case).append(' ').append(max).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int y, int x, int len, boolean cut) {
        max = Math.max(max, len);
        visited[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny<0||nx<0||ny>=N||nx>=N||visited[ny][nx]) continue;
            if (map[ny][nx] >= map[y][x]) {
                if (cut) {
                    for (int i = 1; i <= K; i++) {
                        if (map[ny][nx] - i < map[y][x]) {
                            map[ny][nx] -= i;
                            dfs(ny, nx, len + 1, false);
                            map[ny][nx] += i;
                        }
                    }
                }
                continue;
            }
            dfs(ny, nx, len+1, cut);
        }
        visited[y][x] = false;
    }
}
