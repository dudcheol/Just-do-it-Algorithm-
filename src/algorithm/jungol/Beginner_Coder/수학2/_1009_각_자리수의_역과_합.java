package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1009_각_자리수의_역과_합 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String num = br.readLine();
            if (num.equals("0")) break;
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int i = 0; i < num.length(); i++) {
                sb.append(num.charAt(i));
                sum += num.charAt(i)-'0';
            }
            System.out.println(Integer.parseInt(sb.reverse().toString()) + " " +sum);
        }
    }
}
