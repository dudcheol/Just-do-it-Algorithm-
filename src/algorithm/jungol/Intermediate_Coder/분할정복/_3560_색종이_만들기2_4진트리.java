package algorithm.jungol.Intermediate_Coder.분할정복;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3560_색종이_만들기2_4진트리 {
    private static int N, paper[][];
    private static StringBuilder sb;

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
        sb = new StringBuilder();
        dividePaper(0, 0, N);
        System.out.println(sb);
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
            sb.append(color);
            return;
        }
        sb.append('X');
        int len = size/2;
        dividePaper(r, c, len);
        dividePaper(r, c+len, len);
        dividePaper(r+len, c, len);
        dividePaper(r+len, c+len, len);
    }
}
