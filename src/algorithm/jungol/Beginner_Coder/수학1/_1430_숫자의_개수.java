package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1430_숫자의_개수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String res = Integer.toString(Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()));
        int[] cnt = new int[10];
        for (int i = 0; i < res.length(); i++) {
            cnt[res.charAt(i)-'0']++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(cnt[i]);
        }
    }
}
