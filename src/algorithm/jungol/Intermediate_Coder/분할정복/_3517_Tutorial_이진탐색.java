package algorithm.jungol.Intermediate_Coder.분할정복;

import java.io.*;
import java.util.StringTokenizer;

public class _3517_Tutorial_이진탐색 {
    private static int N,Q;
    private static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(binarySearch(0, N-1, Integer.parseInt(st.nextToken()))).append(' ');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static int binarySearch(int low, int high, int target) {
        int mid = (low+high)/2;
        while(low <= high){
            if (arr[mid] == target) return mid;
            if (arr[mid] > target) high = mid-1;
            else low = mid+1;
            mid = (low+high)/2;
        }
        return -1;
    }

    private static int binarySearchRecur(int low, int high, int target) {
        if (low > high) return -1;

        int mid = (low+high)/2;

        if (arr[mid]==target) return mid;

        if (arr[mid] > target) return binarySearch(low, mid-1, target);

        return binarySearch(mid+1, high, target);
    }
}
