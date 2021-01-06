package algorithm.jungol.Intermediate_Coder.분할정복;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3518_Tutorial_퀵정렬 {
    private static int N, arr[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(arr, 0, N - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        // divide process
        int i = low - 1;
        int pivot = arr[high];
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, high); // pivot의 위치로 스왑

        // output
        for (int j = 0; j < N; j++) {
            System.out.print(arr[j]+" ");
        }
        System.out.println();

        // conquer process
        quickSort(arr, low, i-1);
        quickSort(arr, i+1, high);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
