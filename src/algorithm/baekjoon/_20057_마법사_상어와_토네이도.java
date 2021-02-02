package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20:20~21:30
public class _20057_마법사_상어와_토네이도 {
    private static int N, map[][], answer;
    private static int[] dy = {0,1,0,-1};//좌하우상
    private static int[] dx = {-1,0,1,0};
    private static double[][][] spread = {
            {
                    {0, 0, 0.02, 0, 0},
                    {0, 0.1, 0.07, 0.01, 0},
                    {0.05, 0, 0, 0, 0},
                    {0, 0.1, 0.07, 0.01, 0},
                    {0, 0, 0.02, 0, 0},
            },
            {
                    {0,0,0,0,0},
                    {0,0.01,0,0.01,0},
                    {0.02, 0.07, 0, 0.07, 0.02},
                    {0, 0.1, 0, 0.1, 0},
                    {0, 0, 0.05, 0, 0},
            },
            {
                    {0, 0, 0.02, 0, 0},
                    {0, 0.01, 0.07, 0.1, 0},
                    {0, 0, 0, 0, 0.05},
                    {0, 0.01, 0.07, 0.1, 0},
                    {0, 0, 0.02, 0, 0},
            },
            {
                    {0,0,0.05,0,0},
                    {0,0.1,0,0.1,0},
                    {0.02,0.07,0,0.07,0.02},
                    {0,0.01,0,0.01,0},
                    {0,0,0,0,0},
            }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 재귀를 이용해서 풀면 더 쉬웠을 것임
         * 파라미터: 좌표, 방향
         */
        // 달팽이모양으로 토네이도 움직임
        int d = 0;
        int y = N / 2, x = N / 2;
        int cnt = 0;
        int limit = 0;
        while (true) {
            if (limit % 2 == 0) {
                cnt++;
            }

            // cnt만큼 반복
            for (int r = 0; r < cnt; r++) {
                y += dy[d];
                x += dx[d];
                if (y < 0 || x < 0 || y >= N || x >= N) continue;
                // 이동하는 경우임
                spread(y, x, d);
                if (y==0&&x==0){
                    System.out.println(answer);
                    return;
                }
            }

            limit++;
            d = (d + 1) % 4; // 방향변경

//            print();
        }
    }

    private static void spread(int y, int x, int d) {
        //y,x가 중심이됨
        int sum = 0;
        for (int i = y - 2, k = 0; i <= y + 2; i++, k++) {
            for (int j = x - 2, l = 0; j <= x + 2; j++, l++) {
                if (i < 0 || j < 0 || i >= N || j >= N) { // 겪자밖으로 나간 모래
                    answer += (int) (map[y][x] * spread[d][k][l]);
                } else {
                    map[i][j] += (int) (map[y][x] * spread[d][k][l]);
                }
                sum += (int) (map[y][x] * spread[d][k][l]);
            }
        }
        // 알파
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (ny<0||nx<0||ny>=N||nx>=N){
            answer += map[y][x] - sum;
        } else {
            map[ny][nx] += map[y][x] - sum;
        }
        map[y][x] = 0; // 모든 모래가 알파로 이동
    }


    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
