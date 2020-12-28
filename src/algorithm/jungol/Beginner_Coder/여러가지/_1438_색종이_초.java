package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1438_색종이_초 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] paper = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int bottom = Integer.parseInt(st.nextToken());

            for (int k = 0; k < 10; k++) {
                for (int j = 0; j < 10; j++) {
                    paper[bottom+k][left+j] = true;
                }
            }
        }
        int cnt=0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (paper[i][j]) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
