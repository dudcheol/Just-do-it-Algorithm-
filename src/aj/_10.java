package aj;

import java.util.Scanner;

public class _10 {
    static int n;
    static int r;
    static boolean[] visited;

    static void recursion(String res){
        // 기저
        if(res.length() == r){
            System.out.println(res);
            return;
        }

        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            visited[i] = true;
            recursion(res+(char)(97+i));
            visited[i] = false;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();

        // 97부터 a
        // char a = 'a';
        // System.out.println(a+0);

        visited = new boolean[n];
        recursion("");
    }
}
