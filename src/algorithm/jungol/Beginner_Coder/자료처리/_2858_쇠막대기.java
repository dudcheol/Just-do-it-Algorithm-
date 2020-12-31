package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _2858_쇠막대기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        Stack<Integer> s = new Stack<>();
        // dot의 갯수가 40개가 되면 ')'==40 이므로 혼돈이 생김
        // 따라서, dot의 갯수를 스택에 넣을땐 '('이 될 수 없는 수를 넣어야 하므로
        // 갯수+'('를 해준 값을 스택에 넣음
        int cnt=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                s.push((int)'(');
            } else {
                if (s.peek() == '(') {
                    s.pop();
                    s.push(1+'(');
                } else {
                    int dot=0;
                    while (!s.isEmpty() && s.peek() != '('){
                        dot+=(s.pop()-'(');
                    }
                    s.pop(); // '(' 제거
                    cnt+=dot+1;
                    s.push(dot+'(');
                }
            }
        }
        System.out.println(cnt);
    }
}
