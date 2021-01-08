package algorithm.jungol.Intermediate_Coder.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _1141_불쾌한날 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> st = new Stack<Integer>();
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());

            while (!st.isEmpty() && cur >= st.peek()) {
                st.pop();
            }

            st.push(cur);

            cnt += st.size()-1;
        }

        System.out.println(cnt);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(br.readLine());
//        int[] arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//
//        Stack<Integer> s = new Stack<>();
//        int idx=0;
//        long sum=0;
//        while(idx<N){
//            if (s.isEmpty()){
//                s.push(arr[idx++]);
//                continue;
//            }
//
//            int top = s.pop();
//            while (idx<N && top > arr[idx]){
//                s.push(arr[idx++]);
//            }
//
//            sum+=s.size();
//            int cnt=0,pre=0;
//            if (!s.isEmpty())pre=s.pop();
//            while (!s.isEmpty()){
//                int pop = s.pop();
//                if (pop > pre){
//                    cnt++;
//                } else {
//                    cnt=0;
//                }
//                sum+=cnt;
//                pre = pop;
//            }
//        }
//
//        System.out.println(sum);
    }
}
