package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1814_삽입정렬_횟수_세기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 1; i < N; i++) {
            int idx = i;
            while(idx>0 && arr[idx-1] > arr[idx]){
                int tmp = arr[idx];
                arr[idx] = arr[idx-1];
                arr[idx-1] = tmp;
                idx--;
                cnt++;
            }

//            for (int j = 0; j < N; j++) {
//                System.out.print(arr[j]+" ");
//            }
//            System.out.println();
        }

        System.out.println(cnt);
    }
}
