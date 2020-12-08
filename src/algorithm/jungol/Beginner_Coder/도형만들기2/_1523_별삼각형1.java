package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1523_별삼각형1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (1<=n&&n<=100) {
            if (m == 1) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j <= i; j++) {
                        sb.append('*');
                    }
                    sb.append('\n');
                }
            } else if (m == 2) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - i; j++) {
                        sb.append('*');
                    }
                    sb.append('\n');
                }
            } else if (m == 3) {
                int idx = n-1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j <= idx + i; j++) {
                        if (j >= idx - i) sb.append('*');
                        else sb.append(' ');
                    }
                    sb.append('\n');
                }
            } else sb.append("INPUT ERROR!");
        } else sb.append("INPUT ERROR!");

        System.out.print(sb);
    }
}
