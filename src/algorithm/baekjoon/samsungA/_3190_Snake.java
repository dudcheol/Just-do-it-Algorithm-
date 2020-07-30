package algorithm.baekjoon.samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2시간 14분 .. 시뮬레이션 문제 너무 오래걸린다
 */
public class _3190_Snake {
    static int[][] map;
    static int N;
    static ArrayList<String[]> turn;
    static int[] right = {0, 1}, left = {0, -1}, up = {-1, 0}, down = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        // 맵 바깥은 -1
        for (int i = 0; i < N + 2; i++)
            for (int j = 0; j < N + 2; j++)
                if (i == 0 || j == 0 || i == N + 1 || j == N + 1) map[i][j] = -1;

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        String[] _turn = new String[2];
        turn = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            turn.add(new String[]{st.nextToken(), st.nextToken()});
        }

        System.out.println(dummy());
    }

    static int dummy() {
        Snake snake = new Snake(new int[]{1, 1});

        int time = 0;
        int dir = 0; // 0:right, 1:
        while (true) {
            // 시간 경과
            time++;

            // 뱀을 이동시킨다
            int[] movedPos = moveFromDir(dir, snake.getHead()); // 이동할 위치
//            System.out.println(movedPos[0] + "," + movedPos[1]);
            if (map[movedPos[0]][movedPos[1]] == 1) { // 사과가 있음
                snake.move(movedPos, true);
                map[movedPos[0]][movedPos[1]] = 0; // 사과를 먹음
            } else if (map[movedPos[0]][movedPos[1]] == -1) { // 벽임
                break;
            } else { // 빈 칸임 그냥 이동
                snake.move(movedPos, false);
            }

            // 뱀이 이동했는데 몸을 만남
            if (snake.isMeetBody()) {
                break;
            }

            // 뱀의 머리를 미리 회전시켜 놓는다
            if (!turn.isEmpty() && time == Integer.parseInt(turn.get(0)[0])) {
                if (turn.get(0)[1].equals("L")) dir--; // 왼쪽 회전
                else dir++; // 오른쪽 회전

                if (dir < 0) dir = 3;
                else if (dir > 3) dir = 0;

                turn.remove(0);
            }
        }

        return time;
    }

    static int[] moveFromDir(int dir, int[] pos) { // 뱀의 이동방향 확인
        int[] movingDir;
        int[] ret = new int[2]; /* pos가 snake body 요소 배열의 주소를 참조하기 때문에 함부로 변경해선 안됨 */

        switch (dir) {
            case 0:
                movingDir = right;
                break;
            case 2:
                movingDir = left;
                break;
            case 3:
                movingDir = up;
                break;
            case 1:
                movingDir = down;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
        for (int i = 0; i < 2; i++) {
            ret[i] = pos[i] + movingDir[i];
        }

        return ret;
    }

    static class Snake {
        ArrayList<int[]> body;
        int[] preCutTail;

        Snake(int[] start) {
            body = new ArrayList<>();
            body.add(start);
        }

        void move(int[] pos, boolean eatApple) {
            if (eatApple) {
                body.add(0, pos);
            } else {
                body.add(0, pos);
                preCutTail = body.remove(body.size() - 1);
            }
        }

        int[] getHead() {
            return body.get(0);
        }

        int[] getTail() {
            return body.get(body.size() - 1);
        }

        boolean isMeetBody() {
            if (body.size() < 4) return false;
            int[] head = {getHead()[0], getHead()[1]};
            for (int i = 1; i < body.size(); i++) {
                if (head[0] == body.get(i)[0] && head[1] == body.get(i)[1]) {
                    return true;
                }
            }
            if (preCutTail != null && preCutTail[0] == head[0] && preCutTail[1] == head[1]) return true;
            return false;
        }
    }
}
