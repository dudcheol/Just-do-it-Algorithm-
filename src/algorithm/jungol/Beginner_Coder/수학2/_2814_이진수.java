package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2814_이진수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int num = Integer.parseInt(br.readLine(), 2);
//        System.out.println(num);
        String num = br.readLine();
        int answer = 0;
        int cnt = 0;
        for (int i = num.length()-1; i >= 0; i--) {
            if (num.charAt(i)=='1'){
                answer += Math.pow(2, cnt);
            }
            cnt++;
        }
        System.out.println(answer);
    }
}
