package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1880_암호풀기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String de_lower = br.readLine().toLowerCase();
        String de_upper = de_lower.toUpperCase();
        String en = br.readLine();

        char[] convert = new char['z'+1];
        convert[' '] = ' ';
        for (int i = 'a',k=0; i <= 'z'; i++,k++) {
            convert[i] = de_lower.charAt(k);
        }
        for (int i = 'A',k=0; i <= 'Z'; i++,k++) {
            convert[i] = de_upper.charAt(k);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < en.length(); i++) {
            sb.append(convert[en.charAt(i)]);
        }

        System.out.println(sb);
    }
}
