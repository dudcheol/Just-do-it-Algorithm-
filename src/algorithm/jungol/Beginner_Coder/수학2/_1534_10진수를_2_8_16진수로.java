package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1534_10진수를_2_8_16진수로 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int dec = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        char[] hex = new char[16];
        for (int i = 0; i < 6; i++) {
            hex[10 + i] = (char) ('A' + i);
        }

        StringBuilder sb = new StringBuilder();
        while (dec > 1) {
            int cur = dec % target;
            if(cur >= 10){
                sb.append(hex[cur]);
            } else {
                sb.append(cur);
            }
            dec /= target;
        }
        if (dec!=0) sb.append(dec);

        System.out.println(sb.reverse());
    }
}
