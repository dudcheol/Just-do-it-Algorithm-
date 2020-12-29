package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1836_연속부분합_찾기 {
    private static int N, arr[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int sum = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum+=num;
            max = Math.max(sum,max);
            if(sum<=0){
                sum=0;
            }
        }
        // 아무 배열도 선택하지 않은 경우(0)보다 작으면 0출력
        System.out.println(max);
    }
}
