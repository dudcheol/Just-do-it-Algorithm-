package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1697_큐 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        for (int test_case = 0; test_case < N; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char cmd = st.nextToken().charAt(0);
            switch (cmd){
                case 'i':
                    q.offer(Integer.parseInt(st.nextToken()));
                    break;
                case 'o':
                    if (q.isEmpty()) sb.append("empty");
                    else sb.append(q.poll());
                    sb.append('\n');
                    break;
                case 'c':
                    sb.append(q.size()).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }
}
