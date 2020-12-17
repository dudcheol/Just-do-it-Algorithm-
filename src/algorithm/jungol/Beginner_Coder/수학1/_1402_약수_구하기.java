package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1402_약수_구하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            if (n%i==0){
                if(--k == 0){
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
