package algorithm.jungol.Intermediate_Coder.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _1328_빌딩 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<int[]> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int[] answer = new int[N];
        for (int i = N-1; i >= 0; i--) {
            if (s.isEmpty()){
                s.push(new int[]{i+1, arr[i]});
                answer[i] = 0;
                continue;
            }

            while (!s.isEmpty() && arr[i] >= s.peek()[1]){
                s.pop();
            }

            answer[i] = s.isEmpty()?0:s.peek()[0];

            s.push(new int[]{i+1, arr[i]});
        }

        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.print(sb);
    }
}
