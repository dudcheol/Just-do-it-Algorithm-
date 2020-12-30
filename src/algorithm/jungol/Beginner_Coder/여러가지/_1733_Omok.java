package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1733_Omok {
    static int[][] map;
    // 가장 왼쪽부터 출력하라고 했으므로 가장 왼쪽부터 오목이 가능한 경우를 검색하면 됨!!
    static int[] dy = {0, 1, 1, -1}; // 우,하,우하,우상
    static int[] dx = {1, 0, 1, 1}; // 우,하,우하,우상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        map = new int[19][19];

        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                if (map[i][j] != 0) {
                    for (int d = 0; d < 4; d++) {
                        if (dfs(i, j, 1, d)) {
                            // true 이면 오목 발견한 것이므로
                            sb.append(map[i][j]).append('\n')
                                    .append(i + 1).append(' ').append(j + 1);
                            System.out.println(sb);
                            return;
                        }
                    }
                }

            }// end of for j
        }//end of for i
        System.out.println(0);
        return;
    }

    private static boolean dfs(int y, int x, int cnt, int preDir) {
        if (cnt == 5) {
            // 오목이 완성됐는데 6개 연속이면 완성이 아니다
            boolean ret = false;
            // 1) 아래로 연결되는 경우
            int ny = y + dy[preDir];
            int nx = x + dx[preDir];
            if (ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || map[y][x] != map[ny][nx]){}
            else return false;

            // 2) 위로 연결되는 경우
            ny = y - dy[preDir] * 5;
            nx = x - dx[preDir] * 5;
            if (ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || map[y][x] != map[ny][nx]) return true;
            else return false;
        }
        // 이전에 왔던 방향으로 계속 진행
        int ny = y + dy[preDir];
        int nx = x + dx[preDir];
        if (ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || map[y][x] != map[ny][nx])
            return false;
        boolean ret = dfs(ny, nx, cnt + 1, preDir);
        return ret;
    }
}
