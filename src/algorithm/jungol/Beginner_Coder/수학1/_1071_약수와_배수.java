package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1071_약수와_배수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());

        int yak=0,bae=0;
        for (int i = 0; i < n; i++) {
            if(m % arr[i] == 0){
                yak+=arr[i];
            }
            if (arr[i] % m ==0){
                bae+=arr[i];
            }
        }

        System.out.println(yak);
        System.out.println(bae);
    }
}
