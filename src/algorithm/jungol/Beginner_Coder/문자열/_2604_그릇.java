package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2604_그릇 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dish = br.readLine();

        int answer = 10;
        char pre = dish.charAt(0);
        for (int i = 1; i < dish.length(); i++) {
            if (dish.charAt(i)==pre){
                answer += 5;
            } else {
                answer += 10;
            }
            pre = dish.charAt(i);
        }
        System.out.println(answer);
    }
}
