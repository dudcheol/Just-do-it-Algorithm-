package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1671_색종이_중 {
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
                if (paper[i][j]){ // 색종이인 부분의 상,하,좌,우 중 색종이가 아닌 부분은 둘레가 되므로 cnt++해준다.
                    if (!paper[i-1][j]) cnt++;
                    if (!paper[i+1][j]) cnt++;
                    if (!paper[i][j-1]) cnt++;
                    if (!paper[i][j+1]) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
