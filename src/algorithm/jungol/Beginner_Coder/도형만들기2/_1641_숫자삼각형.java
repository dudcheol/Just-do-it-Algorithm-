package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1641_숫자삼각형 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n<=0||n>100||n%2==0||m<=0||m>3){
            System.out.println("INPUT ERROR!");
            return;
        }

        if (m==1){
            int num = 1;
            for (int i = 0; i < n; i++) {
                if (i%2==1)num+=i;
                for (int j = 0; j <= i; j++) {
                    if (i%2==1) sb.append(num--).append(' ');
                    else sb.append(num++).append(' ');
                }
                sb.append('\n');
                if (i%2==1) num+=i+2;
            }
        }else if (m==2){
            int start=0,end=n*2-1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < end-i; j++) {
                    if (j>=start)sb.append(i).append(' ');
                    else sb.append("  ");
                }
                start++;
                sb.append('\n');
            }
        }else if (m==3){
            int end=1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= end; j++) {
                    sb.append(j).append(' ');
                }
                sb.append('\n');
                if (i<n/2) end++;
                else end--;
            }
        }

        System.out.print(sb);
    }
}
