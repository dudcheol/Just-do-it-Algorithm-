package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1761_숫자야구 {
    private static int[][] games;
    private static boolean[] visited;
    private static int[] selected;
    private static int cnt, N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        games = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            games[i]=new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }
        visited = new boolean[11];
        selected = new int[3];
        perm(0);
        System.out.println(cnt);
    }

    private static void perm(int k) {
        if (k==3){
            if (go()) cnt++;
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[k] = i;
            perm(k+1);
            visited[i] = false;
        }
    }

    private static boolean go(){
        for (int i = 0; i < N; i++) {
            int num = games[i][0];
            int strike = games[i][1];
            int ball = games[i][2];

            int mStrike=0,mBall=0;

            int tmp = num;
            for (int j = 0; j < 3; j++) {
                int cur = (int)(tmp/Math.pow(10,2-j));
                if(selected[j]==cur){
                    mStrike++;
                }

                for (int k = 0; k < 3; k++) {
                    if(selected[k]==cur) {
                        mBall++;
                        break;
                    }
                }
                tmp%=Math.pow(10,2-j);
            }
            mBall-=mStrike;

            if (mStrike!=strike || mBall!=ball) return false;
        }
        return true;
    }
}
