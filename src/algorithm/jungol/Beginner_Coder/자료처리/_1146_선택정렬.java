package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1146_선택정렬 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N-1; i++) {
            int min = arr[i];
            int idx = i;
            for (int j = i; j < N; j++) {
                if (min > arr[j]) {
                    min=arr[j];
                    idx=j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;

            for (int j = 0; j < N; j++) {
                System.out.print(arr[j]+" ");
            }
            System.out.println();
        }
    }
}
