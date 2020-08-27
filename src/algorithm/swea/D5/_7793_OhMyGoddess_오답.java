package algorithm.swea.D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _7793_OhMyGoddess_오답 {
    static int n, m;
    static char[][] map;
    static char[][] tmp;
    static int[] dy = {0, 0, 1, -1}; // 동,서,남,북
    static int[] dx = {1, -1, 0, 0}; // 동,서,남,북
    static int answer;
    static int[] suPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][];
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 'S') {
                        suPos = new int[]{i, j};
                        map[i][j] = '.';
                    }
                }
            }

            simulate();

            sb.append('#').append(tc).append(' ');
            if (answer == Integer.MAX_VALUE) sb.append("GAME OVER");
            else sb.append(answer);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void simulate() {
        //수연이는 말을 타고 1초에 동, 서, 남, 북으로 인접한 칸으로 이동
        dfs(suPos[0], suPos[1], 0);
    }

    private static void dfs(int y, int x, int time) {
        // basis
        if (map[y][x] == 'D') {
            answer = Math.min(answer, time);
            return;
        }

        tmp = deepCopy(map);
        // 동서남북 이동
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m || map[ny][nx] == 'X' || map[ny][nx] == '*') continue;
            // 수연이 이동함
            suPos[0] = ny;
            suPos[1] = nx;
            // 악마의 손길 스킬 발동
            // 여기서 false가 나오면 수지가 이동한 자리에 악마의 스킬이 퍼진 것이므로 이동하지 않는다
            if (!devilSpread()) continue;
            print(map);
            dfs(ny, nx, time + 1);
            map = tmp;
        }
    }

    private static boolean devilSpread() {
        char[][] tmp = deepCopy(map);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '*') {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= m || map[ny][nx] == 'X' || map[ny][nx] == 'D') continue;
                        if (ny == suPos[0] && nx == suPos[1]) return false;
                        tmp[ny][nx] = '*';
                    }
                }
            }
        }
        map = tmp;
        return true;
    }

    private static char[][] deepCopy(char[][] origin) {
        char[][] tmp = new char[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            tmp[i] = map[i].clone();
        }
        return tmp;
    }

    private static void print(char[][] origin) {
        for (int i = 0; i < origin.length; i++) {
            System.out.println(Arrays.toString(origin[i]));
        }
        System.out.println("-----------------");
    }
}

/*
1
5 3
D*S
..X
.X.
.X.
...
 */