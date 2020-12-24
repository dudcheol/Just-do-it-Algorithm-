package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2857_세로읽기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder[] sb = new StringBuilder[15];
        for (int i = 0; i < 15; i++) {
            sb[i] = new StringBuilder();
        }

        for (int i = 0; i < 5; i++) {
            char[] c = br.readLine().toCharArray();

            for (int j = 0; j < c.length; j++) {
                sb[j].append(c[j]);
            }
        }

        for (int i = 0; i < 15; i++) {
            System.out.print(sb[i]);
        }
    }
}
