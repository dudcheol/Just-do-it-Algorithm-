package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _1740_소수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int num = M; num <= N; num++) {
            if (num==1) continue;
            if (isSosu(num)) list.add(num);
        }
        Collections.sort(list);
        int sum = 0;
        for (int l : list) sum += l;
        if (list.isEmpty()) System.out.println(-1);
        else {
            System.out.println(sum);
            System.out.println(list.get(0));
        }
    }

    private static boolean isSosu(int num) {
        for (int i = 2; i <= num/i; i++) {
            if(num%i==0) return false;
        }
        return true;
    }
}
