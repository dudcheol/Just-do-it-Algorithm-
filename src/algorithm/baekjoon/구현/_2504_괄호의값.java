package algorithm.baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _2504_괄호의값 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ary = br.readLine().toCharArray();
        Stack<String> st = new Stack<>();

        for (int i = 0; i < ary.length; i++) {
            String cur = Character.toString(ary[i]);

            if("(".equals(cur) || "[".equals(cur)){
                st.push("(".equals(cur) ? ")" : "]");
            }
            else {
                if(st.isEmpty()) {System.out.println(0); return;}
                String peek = st.peek();
                if (peek.equals(cur)){
                    st.pop();
                    st.push(")".equals(cur) ? "2" : "3");
                } else {
                    if(!isNumber(peek)) {System.out.println(0); return;}

                    int res = 0;
                    while(!st.isEmpty()) {
                        String npop = st.pop();
                        if (!isNumber(npop)) {
                            st.push(")".equals(npop) ? Integer.toString(res * 2) : Integer.toString(res * 3));
                            break;
                        }
                        else
                            res += Integer.parseInt(npop);
                    }
                }
            }
        }
        int answer = 0;
        while(!st.isEmpty()) answer += Integer.parseInt(st.pop());
        System.out.println(answer);
    }

    private static boolean isNumber(String str) {
        return !(str.equals(")") || str.equals("]"));
    }
}
