package algorithm.jungol.Beginner_Coder.도형만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1719_별삼각형2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n<=0||n>100||n%2==0||m<=0||m>4){
            System.out.println("INPUT ERROR!");
            return;
        }

        if (m==1){
            int idx = n/2;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (i>idx) cnt+=2;
                for (int j = 0; j <= i-cnt; j++) {
                    sb.append('*');
                }
                sb.append('\n');
            }
        }else if (m==2){
            int idx = n/2;
            int flag = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n/2+1; j++) {
                    if (j>=idx) sb.append('*');
                    else sb.append(' ');
                }
                sb.append('\n');
                if (idx==0) flag=1;
                idx+=flag;
            }
        }else if (m==3){
            int start=1,end=n,flag=1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= end; j++) {
                    if (j>=start) sb.append('*');
                    else sb.append(' ');
                }
                if (start==end)flag=-1;
                start+=flag;
                end-=flag;
                sb.append('\n');
            }
        }else if (m==4){
            int start=0,end=n/2;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= end; j++) {
                    if (j>=start)sb.append('*');
                    else sb.append(' ');
                }
                sb.append('\n');
                if (i < n/2) start++;
                else end++;
            }
        }

        System.out.print(sb);
    }
}
