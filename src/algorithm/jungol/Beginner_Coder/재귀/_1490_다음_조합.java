package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1490_다음_조합 {
    private static int N,K;
    private static int[] arr;
    private static int[] selected;
    private static boolean check;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        arr = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        selected=new int[K];
        check = false;
        if(!comb(0, 0)){
            System.out.println("NONE");
        }
    }

    private static boolean comb(int k, int idx) {
        if (k==K){
            if (!check){
                for (int i = 0; i < K; i++) {
                    if (arr[i]!=selected[i]) return false;
                }
                check=true;
                return false;
            } else {
                for (int i = 0; i < K; i++) {
                    System.out.print(selected[i]+" ");
                }
                return true;
            }
        }

        for (int i = idx; i <= N; i++) {
            selected[k] = i;
            if(comb(k+1, i+1)) return true;
        }
        return false;
    }
}
