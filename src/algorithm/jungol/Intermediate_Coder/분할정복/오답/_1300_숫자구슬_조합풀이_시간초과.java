package algorithm.jungol.Intermediate_Coder.분할정복.오답;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1300_숫자구슬_조합풀이_시간초과 {
    private static int N,M,arr[];
    private static int[] selected;
    private static int min;
    private static int[] group;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        selected = new int[M-1];
        min = Integer.MAX_VALUE;

        // M-1개 구분선
        comb(0, 1);

        System.out.println(min);
        int sum=0;
        for (int i = 0; i < M; i++) {
            if (i==M-1){
                System.out.println(N-sum);
            } else {
                System.out.print(group[i]-sum+" ");
                sum=group[i];
            }
        }
    }

    private static void comb(int k, int idx) {
        if (k==M-1){
            int max = 0;
            int start = 0;
            for (int i = 0; i < M-1; i++) {
                int end = selected[i];
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum+=arr[j];
                }
                max = Math.max(max, sum);
                start = selected[i];
            }
            int sum = 0;
            for (int i = start; i < N; i++) {
                sum+=arr[i];
            }
            max = Math.max(max, sum);

            if (min > max){
                min = max;
                group = selected.clone();
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            selected[k] = i;
            comb(k+1, i+1);
        }
    }
}
