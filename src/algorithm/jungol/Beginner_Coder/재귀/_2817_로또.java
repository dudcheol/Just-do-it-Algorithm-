package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2817_로또 {
    private static int K, nums[], selected[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        nums = new int[K];
        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        selected = new int[6];
        dfs(0, 0);
    }

    private static void dfs(int k, int idx) {
        if (k==6){
            for (int i = 0; i < 6; i++) {
                System.out.print(selected[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < K; i++) {
            selected[k] = nums[i];
            dfs(k+1, i+1);
        }
    }
}
