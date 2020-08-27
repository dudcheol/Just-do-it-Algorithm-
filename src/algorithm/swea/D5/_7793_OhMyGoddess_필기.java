package algorithm.swea.D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
입력 BufferedReader, toCharArray()

BFS 큐
(악마들, 수연) < 같은 레벨에 악마먼저 넣고 수연

BFS는 해당 노드의 자식들을 다 꺼내는 방식이기 때문에 가지고있는 모든 정보를 알 수 있어야 함
따라서 클래스를 정의해서 사용
 */
public class _7793_OhMyGoddess_필기 {
    private static int N, M;
    private static char[][] map;
    private static Queue<Point> q;
    private static int min;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // N 행, 2 <= R <= 50
            M = Integer.parseInt(st.nextToken()); // M 열, 2 <= C <= 50

            q = new LinkedList<Point>();
            Point sPoint = null; // 수연위치
            //수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’
            map = new char[N][];

            for (int i = 0; i < map.length; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '*') {
                        q.offer(new Point(i, j, true));
                    } else if (map[i][j] == 'S') {
                        sPoint = new Point(i, j, false);
                    }
                }
            }
            q.offer(sPoint); // 수연이 마지막에 넣기
            min = Integer.MAX_VALUE;
            bfs(); /// 수연이가 악마에게 잡힐지 GAME OVER/ 여신을 만날 수 있을지 최단거리 출력

            sb.append('#').append(tc).append(' ').append(min == Integer.MAX_VALUE ? "GAME OVER" : min).append('\n');
        } // end of for test case
        System.out.print(sb);
    } // end of main

    private static void bfs() {
        int cnt = 1; // 여신을 만나는 이동횟수
        // 큐에 시작좌표 넣기
        ex:
        while (!q.isEmpty()) {
            int size = q.size(); // 같은 형제들의 개수
            while (--size >= 0) {
                Point point = q.poll();
                int r = point.r; // 반복문 내에서는 지역변수를 사용하는 것이 속도에 더 유리
                int c = point.c;
                boolean isDevil = point.isDevil;

                for (int i = 0; i < dr.length; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                        if (isDevil) { // 악마이면
                            if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
                                map[nr][nc] = '*'; // 악마의 숨결 표시 - 방문처리효과
                                q.offer(new Point(nr, nc, true));
                            }
                        } else { // 수연이면
                            if (map[nr][nc] == 'D') { // 여신을 만나면 종료
                                min = cnt;
                                break ex;
                            } else if (map[nr][nc] == '.'){ // 평범한 지역
                                map[nr][nc] = 'S';
                                q.offer(new Point(nr, nc, false));
                            }
                        }
                    }
                }
            }
            // 바로 위의 while문이 1초에 일어나는 일들임
            cnt++;
        }//end of while (!q.isEmpty()){
    }// end of bfs

    public static class Point {
        int r;
        int c;
        boolean isDevil; // 악마인지, 수연인지

        public Point(int r, int c, boolean isDevil) {
            this.r = r;
            this.c = c;
            this.isDevil = isDevil;
        }
    }

} // end of class