package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1175_주사위_던지기2 {
    private static int N,M,selected[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[N];
        // 중복순열
        multiPermutation(0);
    }

    private static void multiPermutation(int k) {
        if (k==N){
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum+=selected[i];
            }
            if (sum==M){
                for (int i = 0; i < N; i++) {
                    System.out.print(selected[i]+" ");
                }
                System.out.println();
            }
            return;
        }
        for (int i = 1; i <= 6; i++) {
            selected[k]=i;
            multiPermutation(k+1);
        }
    }
}
