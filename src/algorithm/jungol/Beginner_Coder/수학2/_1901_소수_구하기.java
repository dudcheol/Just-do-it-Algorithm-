package algorithm.jungol.Beginner_Coder.수학2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _1901_소수_구하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int test_case = 0; test_case < N; test_case++) {
            int M = Integer.parseInt(br.readLine());
            boolean sosu = false;

            list.clear();
            if (isSosu(M)) {
                System.out.println(M);
                continue;
            }

            for (int i = 1; i <= M-2; i++) {
                if(isSosu(M-i)){
                    list.add(M-i);
                    sosu = true;
                }
                if (isSosu(M+i)){
                    list.add(M+i);
                    sosu = true;
                }
                if (sosu) break;
            }
            StringBuilder sb = new StringBuilder();
            for (int l:list) sb.append(l).append(' ');
            System.out.println(sb);
        }
    }

    private static boolean isSosu(int num) {
        for (int i = 2; i <= num/i; i++) {
            if (num%i==0) return false;
        }
        return true;
    }
}
