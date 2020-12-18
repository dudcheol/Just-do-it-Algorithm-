package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2811_소수와_합성수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 5; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num==1) System.out.println("number one");
            else System.out.println(isSosu(num)?"prime number":"composite number");
        }
    }

    private static boolean isSosu(int num) {
        int i = 2;
        while(i<=num/i){
            if(num%i++==0) return false;
        }
        return true;
    }
}
