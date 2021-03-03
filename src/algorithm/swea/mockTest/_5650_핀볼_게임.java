package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class _5650_핀볼_게임 {
    private static int N, map[][];
    private static HashMap<Integer, List<int[]>> warms;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[][] dir = { // 블록모양, 진행방향(상하좌우 0123), 이동할방향
            {},
            {1, 3, 0, 2},
            {3, 0, 1, 2},
            {2, 0, 3, 1},
            {1, 2, 3, 0},
            {1, 0, 3, 2}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            warms = new HashMap<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {
                        if(warms.containsKey(map[i][j])){
                            warms.get(map[i][j]).add(new int[]{i,j});
                        } else {
                            warms.put(map[i][j], new ArrayList<>());
                            warms.get(map[i][j]).add(new int[]{i,j});
                        }
//                        warms.getOrDefault(map[i][j], new ArrayList<>()).add(new int[]{i, j});
                    }
                }
            }
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            max = Math.max(max, simulate(i, j, d));
                        }
                    }
                }
            }
            sb.append('#').append(test_case).append(' ').append(max).append('\n');
        }
        System.out.print(sb);
    }

    private static int simulate(int y, int x, int d) {
        int cnt = 0;
        int ny = y, nx = x;
        // d방향으로 계속이동
        while (true) {
            ny += dy[d];
            nx += dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) { // 벽만남
                d = dir[5][d]; // 이동방향 변경
                cnt++;
                continue;
            }
            if ((ny == y && nx == x) || map[ny][nx] == -1) { // 출발위치or블랙홀 끝
                break;
            }
            if (map[ny][nx] >= 1 && map[ny][nx] <= 5) { // 블록만남
                d = dir[map[ny][nx]][d]; // 이동방향 변경
                cnt++;
                continue;
            }
            if (map[ny][nx] >= 6 && map[ny][nx] <= 10) { // 웜홀
                for (int[] pos : warms.get(map[ny][nx])) {
//                    if (pos[0] != ny && pos[1] != nx) { << 실수!! 이 코드는 웜홀이 2,3과 2,4에 있을 때 기능작동이 안됨
                    if (pos[0] != ny || pos[1] != nx) {
                        ny = pos[0];
                        nx = pos[1];
                        break;
                    }
                }
            }
        }
        return cnt;
    }
}
