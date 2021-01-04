package algorithm.jungol.Intermediate_Coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3519_Tutorial_병합정렬 {
    private static int N, arr[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr, 0, N-1, new int[N]);
    }

    private static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        // 1. 기저조건
        if (low>=high) return;

        // 2. 분할(divdie)
        int mid = (low+high)/2;

        // 3. 정복(conquer)
        mergeSort(arr, low, mid, tmp);
        mergeSort(arr, mid+1, high, tmp);

        // 4. 결합(merge)
        int i=low, j=mid+1;
        for (int k = low; k <= high; k++) {
            if (j>high) tmp[k]=arr[i++];
            else if (i>mid) tmp[k]=arr[j++];
            else if (arr[i]<=arr[j]) tmp[k]=arr[i++];
            else tmp[k]=arr[j++];
        }

        // 5. 복사(copy)
        for (int k = low; k <= high; k++) {
            arr[k]=tmp[k];
        }

        // 출력
        for (int k = 0; k < N; k++) {
            System.out.print(arr[k]+" ");
        }
        System.out.println();
    }
}
