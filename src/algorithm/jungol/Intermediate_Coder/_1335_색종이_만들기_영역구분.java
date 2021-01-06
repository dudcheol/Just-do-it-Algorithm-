package algorithm.jungol.Intermediate_Coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1335_색종이_만들기_영역구분 {
    private static int N, paper[][], cnt[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                paper[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        cnt = new int[2];
        dividePaper(0, 0, N);
        System.out.println(cnt[0]+"\n"+cnt[1]);
    }

    private static void dividePaper(int r, int c, int size) {
        boolean isSame = true;
        int color = paper[r][c];
        loop:for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                if(paper[i][j] != color){
                    isSame = false;
                    break loop;
                }
            }
        }
        if (isSame) {
            cnt[color]++;
            return;
        }
        int len = size/2;
        dividePaper(r, c, len);
        dividePaper(r, c+len, len);
        dividePaper(r+len, c, len);
        dividePaper(r+len, c+len, len);
    }

    /**
     * 실수
     * 4분할시에 필요한 "좌상", "우하"좌표를 파라미터로 하게되면 오히려 더 헷갈리게 된다.
     * "좌상"좌표만 받고, 변의 길이를 가지고 판단해나가는게 더 쉽다.
     */
//    private static void dividePaper(int sr, int sc, int dr, int dc) {
//        boolean isSame = true;
//        int color = paper[sr][sc];
//        loop:for (int i = sr; i <= dr; i++) {
//            for (int j = sc; j <= dc; j++) {
//                if(paper[i][j] != color){
//                    isSame = false;
//                    break loop;
//                }
//            }
//        }
//        if (isSame) {
//            cnt[color]++;
//            return;
//        }
//        dividePaper(sr, sc, dr/2, dc/2);
//        dividePaper(sr,dc/2+1,dr/2, dc);
//        dividePaper(dr/2+1,sc,dr, dc/2);
//        dividePaper(dr/2+1,dc/2+1,dr, dc);
//    }
}
