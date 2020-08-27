package algorithm.etc;

import java.io.*;
import java.util.StringTokenizer;

public class Restaurant {
    static int r, c;
    static char[][] map;
    static int answer;
    static int[] dy = {-1, 0, 1}; //오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선으로 연결
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(new File("src/algorithm/etc/Restaurant_input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 첫째열과 마지막열은 각각 수도관과 식당이므로 비어있음
        for (int i = 0; i < r; i++) {
            connect(i, 0);
        }
        System.out.println(answer);
    }

    public static boolean connect(int y, int x) {
        // 기저 : 이동하다가 마지막 열에 도착하면 종료
        visited[y][x] = true;
        if (x == c - 1) {
            answer++;
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int ny = y + dy[d];
            int nx = x + 1;
            if (ny < 0 || ny >= r) continue;
            if (map[ny][nx] == 'x' || visited[ny][nx]) continue; // 건물이 있으면 갈 수 없음, 이미 파이프가 놓인 자리도 갈 수 없음
            if (connect(ny, nx)) return true;
        }
        return false;
    }
}

/*
5 5
.xx..
..x..
.....
...x.
...x.
 */