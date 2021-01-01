package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1169_주사위_던지기1 {
    private static int N,M,selected[];
    private static boolean visited[];
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[7];
        selected = new int[N];
        sb = new StringBuilder();

        if (M==1){
            duplPerm(0); // 순서O ,중복 O 중복순열
        }else if (M==2){
            duplComb(0, 1); // 순서X ,중복 O 중복조합
        }else {
            perm(0); // 순서X ,중복 X 조합
        }

        System.out.print(sb);
    }

    private static void duplPerm(int k) {
        if (k==N){
            for (int i = 0; i < N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= 6; i++) {
            selected[k] = i;
            duplPerm(k+1);
        }
    }

    private static void duplComb(int k, int idx) {
        if (k==N){
            for (int i = 0; i < N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = idx; i <= 6; i++) {
            selected[k]=i;
            duplComb(k+1, i);
        }
    }

    private static void perm(int k) {
        if (k==N){
            for (int i = 0; i < N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= 6; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[k] = i;
            perm(k+1);
            visited[i] = false;
        }
    }
}
