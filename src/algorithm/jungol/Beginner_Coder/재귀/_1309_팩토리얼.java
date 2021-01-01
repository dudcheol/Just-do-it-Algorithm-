package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1309_팩토리얼 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        factorial(n,n);
    }

    private static void factorial(int n, long res) {
        if (n==1){
            System.out.println("1! = 1");
            System.out.println(res);
            return;
        }
        System.out.println(n+"! = "+n+" * "+(n-1)+"!");
        factorial(n-1, res*(n-1));
    }
}
