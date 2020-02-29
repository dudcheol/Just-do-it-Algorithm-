package programmers.skillCheck;

import java.util.*;

public class skill_test_level2_q1_try1 {
	static boolean solution(String s) {
        boolean answer = true;

        Stack<Character> st = new Stack<>();
        
        for(char _s : s.toCharArray()){
            if(st.isEmpty()) {
                if(_s=='(') {
                    st.push(_s);
                    continue;
                }
                else return false;
            }
            if(_s=='('){
                st.push(_s);
            } else {
                if(st.peek()=='('){
                    st.pop();
                }
            }
        }
        
        if(st.isEmpty()) answer = true;
        else answer = false;
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution("()()"));
	}
}
