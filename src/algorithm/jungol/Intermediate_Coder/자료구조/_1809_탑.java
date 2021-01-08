package algorithm.jungol.Intermediate_Coder.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1809_탑 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N+1];
        Stack<Integer> s = new Stack<>(); // index 저장
        for (int i = 1; i <= N; i++) {
            while (!s.isEmpty() && arr[s.peek()] < arr[i]){
                s.pop();
            }
            sb.append(s.isEmpty()?0:s.peek()).append(' ');
            s.push(i);
        }

        System.out.println(sb);
    }
}
