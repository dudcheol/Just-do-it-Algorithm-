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
                String pop = st.pop();
                if (pop.equals(cur)){
                    st.push(")".equals(cur) ? "2" : "3");
                } else {
                    if(!isNumber(pop)) {System.out.println(0); return;}
                    int num = Integer.parseInt(pop);

                    String npop = st.pop();

                    if(!isNumber(npop))
                        st.push(")".equals(npop) ? Integer.toString(num*2) : Integer.toString(num*3));
                    else
                        st.push(Integer.toString(num+Integer.parseInt(npop)));
                }
            }
        }
        System.out.println(st.pop());
    }

    private static boolean isNumber(String str) {
        return !(str.equals(")") || str.equals("]"));
    }
}
