package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1002_최대공약수_최소공배수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int gcd = arr[0];
        int lcm = arr[0];
        for (int i = 1; i < N; i++) {
            gcd = getGcd(gcd, arr[i]);
            lcm = lcm / getGcd(lcm, arr[i]) * arr[i];
        }
        System.out.println(gcd);
        System.out.println(lcm);
    }

    private static int getGcd(int num, int rest) {
        if (rest==0) return num;
        return getGcd(rest, num%rest);
    }
}
