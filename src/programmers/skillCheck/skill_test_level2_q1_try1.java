package programmers.skillCheck;

import java.util.*;

public class skill_test_level2_q1_try1 {
	static boolean solution(String s) {
        boolean answer = false;

        Stack<Character> st = new Stack<>();
        
        for(char _s : s.toCharArray()){
            if(_s=='('){
                st.push(_s);
            } else {
                if(!st.isEmpty() && st.peek()=='('){
                    st.pop();
                } else {
                	return false;
                }
            }
        }
        
        if(!st.isEmpty()) return false;
        else return true;
    }
	
	public static void main(String[] args) {
		System.out.println(solution("()()"));
	}
}
