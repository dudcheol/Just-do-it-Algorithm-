package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1329_별삼각형3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        if (n<=0||n>100||n%2==0){
            System.out.println("INPUT ERROR!");
            return;
        }

        int space=0,fill=1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < space+fill; j++) {
                if (j<space) sb.append(' ');
                else sb.append('*');
            }
            sb.append('\n');
            if (i<n/2){
                space++;
                fill+=2;
            } else {
                space--;
                fill-=2;
            }
        }

        System.out.print(sb);
    }
}
