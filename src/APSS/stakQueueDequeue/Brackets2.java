package APSS.stakQueueDequeue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 입력을 고려하지 못했다.
 * 처음 입력이 ),},] 일 경우도 생각해야 한다.
 */
public class Brackets2 {
    static boolean isCorrect(String str) {
        Stack<Character> st = new Stack<>();

        for (char s : str.toCharArray()) {
            switch (s) {
                case ')':
                    if (!st.isEmpty() && st.peek() == '(') st.pop();
                    else return false;
                    break;
                case '}':
                    if (!st.isEmpty() && st.peek() == '{') st.pop();
                    else return false;
                    break;
                case ']':
                    if (!st.isEmpty() && st.peek() == '[') st.pop();
                    else return false;
                    break;
                default:
                    st.push(s);
            }
        }

        return st.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < TC; tc++) {
            String str = br.readLine().trim();

            System.out.println(isCorrect(str) ? "YES" : "NO");
        }
    }
}
