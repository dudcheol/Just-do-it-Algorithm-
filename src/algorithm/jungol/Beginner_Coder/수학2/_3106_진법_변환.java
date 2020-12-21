package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3106_진법_변환 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] parseAlpha = new char[36];
        for (int i = 0; i < 26; i++) {
            parseAlpha[10 + i] = (char) ('A' + i);
        }

        int[] alphaToDec = new int['Z'+1];
        for (int i = 1; i <= 9; i++) {
            alphaToDec[i] = i;
        }
        int num=10;
        for (int i = 'A'; i <= 'Z'; i++) {
            alphaToDec[i] = num++;
        }

        for (int test_case = 1; test_case <= 100; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            if (A==0) return;
            String N = st.nextToken();
            int B = Integer.parseInt(st.nextToken());

            // A진수 -> 10진수
            long dec = 0;
            long mul = 1;
            for (int i = N.length()-1; i >= 0; i--) {
                char cur = N.charAt(i);
                if(cur!='0') {
                    if (Character.isDigit(cur)) {
                        dec += Character.getNumericValue(cur) * mul;
                    } else {
                        dec += alphaToDec[cur] * mul;
                    }
                }
                mul *= A;
            }

            // 10진수 -> B진수
            StringBuilder sb = new StringBuilder();
            while (dec > 1) {
                int cur = (int)(dec % B);
                if(cur >= 10){
                    sb.append(parseAlpha[cur]);
                } else {
                    sb.append(cur);
                }
                dec /= B;
            }
            if (dec!=0) sb.append(dec);
            if (sb.length()==0) System.out.println(0);
            else System.out.println(sb.reverse());
        }
    }
}
