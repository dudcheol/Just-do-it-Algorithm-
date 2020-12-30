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
            int tmp = arr[i], j;

            // 0~i-1까지는 이미 정렬된 상태이다.
            // 따라서 i번째 수를 정렬된 배열에 삽입하는 것이므로,
            // i번째 수보다 큰 수들은 계속해서 스왑하고
            // i번째 수보다 작은 수를 만나면 그 수 다음에 오도록 하면 된다.
            for (j = i-1; j >= 0 && arr[j] > tmp; j--) { // for(;조건;)
                arr[j+1] = arr[j]; // i번째 수보다 큰 수이면 계속해서 스왑한다.
            }
            // i번째 수보다 작은 수를 만나거나 0번째로 도달했다면
            // 어차피 그 전의 수들은 정렬되어 있기 때문에 더 확인해봤자 다 작은수들일 것이다.
            // 따라서 처음 만난 작은 수의 다음 자리에 i번째 수를 넣어준다
            arr[j+1] = tmp;

            for (int k = 0; k < N; k++) {
                System.out.print(arr[k]+" ");
            }
            System.out.println();
        }
    }
}
