package algorithm.jungol.Intermediate_Coder.분할정복.오답;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1300_숫자구슬_이상한이진탐색 {
    private static int N,M,arr[];
    private static boolean[] visited;
    private static int min, cnts[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N];
        min = Integer.MAX_VALUE;
        cnts = new int[M];

        binarySearch(0,N-1,0);

        System.out.println(min);
        for (int i = 0; i < M; i++) {
            System.out.print(cnts[i]+" ");
        }
    }

    private static void binarySearch(int low, int high, int line) {
        if (line==M-1){
            int max = 0;
            int sum = 0;
            int[] tmpCnt = new int[M];
            int idx = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if(visited[i]){
                    System.out.print(1);
                    sum+=arr[i];
                    tmpCnt[idx++] = ++cnt;
                    max = Math.max(max, sum);
                    sum = 0;
                    cnt = 0;
                } else {
                    sum+=arr[i];
                    ++cnt;
                    System.out.print(0);
                }
            }
            System.out.println();
            tmpCnt[idx] = cnt;
            max = Math.max(max, sum);
            if (min > max){
                min = max;
                cnts = tmpCnt;
            }
            return;
        }

        if (low >= high) return;

        int mid = (low+high)/2;

        binarySearch(low,mid,line);
        binarySearch(mid+1,high,line);
        visited[mid] = true;
        binarySearch(low,mid,line+1);
        binarySearch(mid+1,high,line+1);
        visited[mid] = false;
    }
}
