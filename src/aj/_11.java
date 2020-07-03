package aj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11 {
    static int T;
    static int n, m; // 가로,세로
    static int x, y; // 로봇의 현재 위치
    static int map[][];
    static int k; // 회전판에 존재하는 칸의 개수
    static int pan[]; // 회전판
    static int l; // 성우가 로봇을 움직이는 횟수
    static char[] robotDir; // 이동할 방향
    static int[] rotateDir; // 회전 방향
    static int[] rotateCnt; // 회전시키는 칸 수

    static int[] dy = {0, 0, 1, -1}; // 동서남북 우좌하상
    static int[] dx = {1, -1, 0, 0};
    static int panCursor = 0; // 회전판은 초기에 가장 앞의 숫자를 가리키고 있다.

    static int score;

    static void simulate(int command) {
        // 기저
        if (command == l) return;

        // 명령 이해하기
        int curDir = findDir(robotDir[command]);
        int curRotateDir = rotateDir[command];
        int curRotateCnt = rotateCnt[command];

        // 회전판을 통해 이동할 거리 구하기
        if (curRotateDir == 1) {
            // 시계방향
            int sub = curRotateCnt % k;
            int res = panCursor - sub;
            panCursor = res < 0 ? res + k : res;
        } else {
            // 반시계방향
            panCursor = (panCursor + curRotateCnt) % k;
        }

        int moveRange = pan[panCursor];

        // 로봇 이동시키기
        for (int i = 0; i < moveRange; i++) {
            y += dy[curDir];
            x += dx[curDir];
            score += map[y][x];
            map[y][x] = 0;
        }

        simulate(command + 1);
    }

    static int findDir(char d) {
        switch (d) {
            case 'E':
                return 0;
            case 'W':
                return 1;
            case 'S':
                return 2;
            case 'N':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // test case
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // 맵 채우기
            map = new int[m + 1][n + 1]; // 1부터 시작
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            k = Integer.parseInt(br.readLine());
            pan = new int[k];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                pan[i] = Integer.parseInt(st.nextToken());
            }

            l = Integer.parseInt(br.readLine());
            robotDir = new char[l];
            rotateDir = new int[l];
            rotateCnt = new int[l];
            for (int i = 0; i < l; i++) {
                st = new StringTokenizer(br.readLine());
                robotDir[i] = st.nextToken().charAt(0);
                rotateDir[i] = Integer.parseInt(st.nextToken());
                rotateCnt[i] = Integer.parseInt(st.nextToken());
            }

            score = map[y][x];
            map[y][x] = 0;
            simulate(0);

            // 답 출력
            System.out.println("#" + t + " " + score + " " + x + " " + y);
        }
    }
}
