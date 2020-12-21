package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2541_문자열_찾기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int koi=0,ioi=0;
        for (int i = 0; i < str.length()-2; i++) {
            String cur = str.substring(i,i+3);
            if (cur.equals("KOI")){
                koi++;
            }else if (cur.equals("IOI")){
                ioi++;
            }
        }
        System.out.println(koi);
        System.out.println(ioi);
    }
}
