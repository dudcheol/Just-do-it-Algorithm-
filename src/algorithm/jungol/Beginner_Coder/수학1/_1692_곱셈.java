package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1692_곱셈 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] num1 = br.readLine().toCharArray();
        char[] num2 = br.readLine().toCharArray();
        int[][] cal = new int[4][6];

        int ypos=0;
        int xpos=5;
        for (int i = 2; i >= 0; i--) {
            int cnt=0;
            int tmp =0,mok=0,rest;
            for (int j = 2; j >= 0; j--) {
                tmp = (num1[j]-'0') * (num2[i]-'0') + mok;
                mok = tmp/10;
                rest = tmp%10;
                cal[ypos][xpos-cnt] = rest;
                cnt++;
            }
            if (mok!=0){
                cal[ypos][xpos-cnt] = mok;
            }
            ypos++;
            xpos--;
        }

        StringBuilder sb = new StringBuilder();
        int cnt=0;
        for (int i = 0; i < 3; i++) {
            boolean isNum = false;
            for (int j = 0; j < 6-cnt; j++) {
                if (!isNum && cal[i][j]==0 && j!=5-cnt) continue;
                isNum = true;
                sb.append(cal[i][j]);
                cal[3][j] += cal[i][j];
            }
            cnt++;
            sb.append('\n');
        }
        System.out.print(sb);
        sb.setLength(0);
        int mok=0;
        for (int i = 5; i >= 1; i--) {
            int val = cal[3][i] + mok;
            mok = val/10;
            sb.append(val%10);
        }
        if(mok!=0 || cal[3][0]!=0) sb.append(mok+cal[3][0]);
        System.out.print(sb.reverse());
    }
}
