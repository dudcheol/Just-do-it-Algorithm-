package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2206_BreakingWall {
    static int N, M;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1}; // 우,하,좌,상
    static int[] dx = {1, 0, -1, 0}; // 우,하,좌,상
    static int answer;
    static boolean[][][] visited;

    static class Pos {
        int y;
        int x;
        int breakWall;
        int count;

        public Pos(int y, int x, int breakWall, int count) {
            this.y = y;
            this.x = x;
            this.breakWall = breakWall;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 벽을 부순 상태와 부수지 않은 상태에서의 방문을 확인
        // visited = new boolean[2][N][M]; : NxM배열이 2개 라는 의미. 이것도 상관없음
        // 헷갈리면 1층, 2층 ... 이나 판처럼 생각해도 좋음!

        // 맵, 캐시초기화
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        // "가중치가 없는 최단경로" 문제 : bfs
        bfs();
    }

    private static void bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0, 0, 1));

        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (p.y == N - 1 && p.x == M - 1) {
                System.out.println(p.count);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                int breakWall = p.breakWall;
                int count = p.count;

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                if (map[ny][nx] == 1) { // 벽을 만났는데
                    if (breakWall == 0 && !visited[ny][nx][1]) { // 벽을 부순적이 없고, 이 곳을 방문한적도 없다면
                        // visited[ny][nx][1] => 부수고나서 방문했는지 확인하는 배열에서 방문한적이 있는 지 없는 지 확인!
                        visited[ny][nx][1] = true; // 벽을 부순다음 방문처리
                        q.offer(new Pos(ny, nx, 1, count + 1));
                    }
                } else { // 빈 칸이라면
                    if (!visited[ny][nx][breakWall]){ // 벽을 부순 여부에 따라 방문한 적도 없었다면
                        visited[ny][nx][breakWall] = true;
                        q.offer(new Pos(ny, nx, breakWall, count + 1));
                    }
                }

                // 교수님 풀이
                // 꺼낸 데이터 가지고 4방 탐색
                // 1) 다음 위치가 벽이 아니고 (그 전에 벽을 부쉈던 아니던 상관없이 그냥 가면 됨)
                // 2) 다음 위치가 벽인 경우 (아직 부순적이 없어야 갈 수 있음)
            } // end of for
        } // end of while

        System.out.println(-1);
    } // end of bfs

/*    private static void dfs(int y, int x, int cost, boolean isBreak) {
        cache[y][x] = cost;

        // basis
        if (y == N - 1 && x == M - 1) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

            int cachen = cache[ny][nx];
            if (cachen != -1 && cachen < cost) continue;

            if (map[ny][nx] == 1) {
                // 벽을 부수고 이동
                if (!isBreak)
                    dfs(ny, nx, cost + 1, true);
            } else {
                // 이동하려는 곳이 벽이 아니면 이동
                dfs(ny, nx, cost + 1, isBreak);
            }
        }
    }*/
}
