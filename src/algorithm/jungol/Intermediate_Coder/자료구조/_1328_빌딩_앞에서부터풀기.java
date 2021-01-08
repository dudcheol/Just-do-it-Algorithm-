package algorithm.jungol.Intermediate_Coder.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _1328_빌딩_앞에서부터풀기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int[] answer = new int[N];

        s.push(0); // 스택엔 arr의 인덱스를 저장한다
        for (int i = 1; i < N; i++) {
            while (!s.isEmpty() && arr[s.peek()] < arr[i]){
                answer[s.pop()] = i+1;
            }
            s.push(i);
        }

        while (!s.isEmpty()){
            answer[s.pop()] = 0;
        }

        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.print(sb);
    }
}
