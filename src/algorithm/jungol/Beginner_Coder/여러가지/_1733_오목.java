package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1733_오목 {
    private static int map[][];
    private static int[] dy = {-1,0,1,1}; // 우상, 우, 우하, 하
    private static int[] dx = {1,1,1,0}; // 우상, 우, 우하, 하

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[19][19];
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(map[i][j]!=0 && check(i,j)){
                    System.out.println(map[i][j]);
                    System.out.println((i+1)+" "+(j+1));
                    return;
                }
            }
        }
        System.out.println(0);
    }

    private static boolean check(int y, int x) {
        int color = map[y][x];
        for (int d = 0; d < 4; d++) {
            int ny=y, nx=x, cnt=1;
            for (int k = 0; k < 5; k++) {
                ny+=dy[d];
                nx+=dx[d];
                if (ny<0||nx<0||ny>=19||nx>=19||color != map[ny][nx]) break;
                cnt++;
            }
            if (cnt==5) {
                // 반대편까지 확인
                ny = y-dy[d];
                nx = x-dx[d];
                if (ny<0||nx<0||ny>=19||nx>=19||color != map[ny][nx]) return true;
            }
        }
        return false;
    }
}
