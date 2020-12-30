package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1158_삽입정렬 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]){
                    int tmp = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k-1];
                    }
                    arr[j] = tmp;
                }
            }

            for (int j = 0; j < N; j++) {
                System.out.print(arr[j]+" ");
            }
            System.out.println();
        }
    }
}
