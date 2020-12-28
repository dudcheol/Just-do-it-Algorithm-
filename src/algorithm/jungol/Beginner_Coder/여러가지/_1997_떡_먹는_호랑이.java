package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1997_떡_먹는_호랑이 {
    private static int D,K;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int a=1,b=1;
        while(true){
            int res = recursion(0,a,b);
            if (res == K){
                System.out.println(a);
                System.out.println(b);
                return;
            } else if (res > K){
                b=++a;
                continue;
            }
            b++;
        }
    }

    private static int recursion(int k, int a, int b) {
        if (k==D-2) {
            return b;
        }
        return recursion(k+1, b, a+b);
    }
}
