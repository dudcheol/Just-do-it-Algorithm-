package algorithm.jungol.Beginner_Coder.자료처리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1221_후위표기법1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < M; i++) {
            char c = st.nextToken().charAt(0);
            switch (c){
                case '+':
                    s.push(s.pop()+s.pop());
                    break;
                case '-':
                    int num1= s.pop();
                    int num2= s.pop();
                    s.push(num2-num1);
                    break;
                case '*':
                    s.push(s.pop()*s.pop());
                    break;
                case '/':
                    int num3=s.pop();
                    int num4=s.pop();
                    s.push(num4/num3);
                    break;
                default:
                    s.push(c-'0');
            }
        }
        System.out.println(s.pop());
    }
}
