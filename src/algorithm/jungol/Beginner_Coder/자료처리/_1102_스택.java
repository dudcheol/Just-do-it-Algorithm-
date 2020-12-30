package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1102_스택 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();

        for (int test_case = 0; test_case < N; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char cmd = st.nextToken().charAt(0);
            switch (cmd){
                case 'i':
                    s.push(Integer.parseInt(st.nextToken()));
                    break;
                case 'o':
                    if (s.isEmpty()) sb.append("empty");
                    else sb.append(s.pop());
                    sb.append('\n');
                    break;
                case 'c':
                    sb.append(s.size()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}
