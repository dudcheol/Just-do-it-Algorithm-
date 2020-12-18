package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1658_최대공약수와최소공배수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        // 최대공약수 GCD
        int gcd = gcd(num1, num2);
        System.out.println(gcd);
//        System.out.println(gcd*(num1/gcd)*(num2/gcd));
        System.out.println(num1 / gcd * num2);
    }

    private static int gcd(int num, int rest) {
        if (rest==0){
            return num;
        }
        return gcd(rest, num%rest);
    }
}
