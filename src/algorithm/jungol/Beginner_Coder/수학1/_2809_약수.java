package algorithm.jungol.Beginner_Coder.수학1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _2809_약수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 2 <= N <= 21억

        ArrayList<Integer> list = new ArrayList<>();
        for (int num = 1; num <= Math.sqrt(N); num++) {
            if(N%num==0){
                list.add(num);
                if (N/num != num) list.add(N/num);
            }
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(' ');
        }
        System.out.print(sb);
    }

}
