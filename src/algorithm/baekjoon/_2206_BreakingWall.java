package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2206_BreakingWall {
    static int N, M;
    static int[][] map;
//    static int[] dy = {0, 1, 0, -1}; // 우,하,좌,상
//    static int[] dx = {1, 0, -1, 0}; // 우,하,좌,상
    static int answer;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[N][M];
        cache = new int[N][M];

        // 맵, 캐시초기화
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
            Arrays.fill(cache[i], -1);
        }

        // 최단경로 문제 : bfs 접근이지만... 벽을 부수고 이동할 수 있다고 하니까 dfs로 해보자!
        dfs(0, 0, 1, false);
        System.out.println(cache[N - 1][M - 1]);
    }

    private static void dfs(int y, int x, int cost, boolean isBreak) {
        cache[y][x] = cost;

        // basis
        if (y == N - 1 && x == M - 1) {
            return;
        }

        if (x + 1 < M) {
            int cachen = cache[y][x + 1];
            if (cachen == -1 || cachen >= cost) {
                if (map[y][x + 1] == 1) {
                    if (!isBreak) dfs(y, x + 1, cost + 1, true);
                } else {
                    dfs(y, x + 1, cost + 1, isBreak);
                }
            }
        }
ㅡ
        if (y + 1 < N) {
            int cachen = cache[y + 1][x];
            if (cachen == -1 || cachen >= cost) {
                if (map[y + 1][x] == 1) {
                    if (!isBreak) dfs(y + 1, x, cost + 1, true);
                } else {
                    dfs(y + 1, x, cost + 1, isBreak);
                }
            }
        }

        if (x - 1 >= 0) {
            int cachen = cache[y][x - 1];
            if (cachen == -1 || cachen >= cost) {
                if (map[y][x - 1] == 1) {
                    if (!isBreak) dfs(y, x - 1, cost + 1, true);
                } else {
                    dfs(y, x - 1, cost + 1, isBreak);
                }
            }
        }

        if (y - 1 >= 0) {
            int cachen = cache[y - 1][x];
            if (cachen == -1 || cachen >= cost) {
                if (map[y - 1][x] == 1) {
                    if (!isBreak) dfs(y - 1, x, cost + 1, true);
                } else {
                    dfs(y - 1, x, cost + 1, isBreak);
                }
            }
        }

//        for (int d = 0; d < 4; d++) {
//            int ny = y + dy[d];
//            int nx = x + dx[d];
//
//            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
//
//            int cachen = cache[ny][nx];
//            if (cachen != -1 && cachen < cost) continue;
//
//            if (map[ny][nx] == 1) {
//                // 벽을 부수고 이동
//                if (!isBreak)
//                    dfs(ny, nx, cost + 1, true);
//            } else {
//                // 이동하려는 곳이 벽이 아니면 이동
//                dfs(ny, nx, cost + 1, isBreak);
//            }
//        }
    }
}
