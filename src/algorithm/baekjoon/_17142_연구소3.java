package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17142_연구소3 {
    private static int N,M,size,min;
    private static int[][] map;
    private static ArrayList<int[]> virusPos;
    private static int[] selected;
    private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virusPos = new ArrayList<int[]>();
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2){
                    virusPos.add(new int[]{i,j});
                }
            }
        }
        size = virusPos.size();
        selected = new int[M];
        visited = new boolean[N][N];

        if (check()) { System.out.println(0); return; }

        selectVirus(0, 0);

        System.out.println(min==Integer.MAX_VALUE?-1:min);
    }

    private static void selectVirus(int k, int idx) {
        if (k==M){
            bfs();
            return;
        }
        for (int i = idx; i < size; i++) {
            selected[k] = i;
            selectVirus(k+1, i+1);
        }
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int[] m = virusPos.get(selected[i]);
            visited[m[0]][m[1]] = true;
            q.offer(new int[]{m[0],m[1]});
        }

        int res = 0;
        boolean isOk = false;
        while(!q.isEmpty()){
            int qSize = q.size();
            while(qSize--!=0) {
                int[] poll = q.poll();
                int y = poll[0];
                int x = poll[1];
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1 || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
            res++;
            if (check()) {
                min = Math.min(min, res);
                return;
            }
        }
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]==0 && !visited[i][j])
                    return false;
            }
        }
        return true;
    }

}
