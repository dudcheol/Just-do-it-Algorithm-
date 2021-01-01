package algorithm.jungol.Beginner_Coder.재귀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _1459_숫자고르기 {
    private static int N, arr[];
    private static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> list = new ArrayList<>();
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            if(recursion(i, arr[i])){
                list.add(i);
            }
        }

        System.out.println(list.size());
        for(int l : list){
            System.out.println(l);
        }
    }

    private static boolean recursion(int idx, int target) {
        visited[target] = true;

        if (idx==arr[target]) return true;

        if (visited[arr[target]]) return false;
        return recursion(idx, arr[target]);
    }
}
