package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 솔직히 이해 잘 안됨..
public class _1161_하노이1 {
    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        hanoi(N, 1, 3, 2);
    }

    private static void hanoi(int n, int from, int to, int via) {
        if (n==1){
            System.out.println(n+" : "+from+" -> "+to);
            return;
        }

        hanoi(n-1, from, via, to);
        System.out.println(n+" : "+from+" -> "+to);
        hanoi(n-1, via, to, from);
    }
}
