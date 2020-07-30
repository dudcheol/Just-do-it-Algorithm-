package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ParenthesisMatcing {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String compareSet = "[{(<";

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            int answer = 1;
            Stack<Character> s = new Stack<>();
            for(char input : br.readLine().toCharArray()){
                if (compareSet.contains(input + "")) {
                    s.push(input);
                } else {
                    char match = ' ';

                    switch (input) {
                        case ')':
                            match = '(';
                            break;
                        case '}':
                            match = '{';
                            break;
                        case ']':
                            match = '[';
                            break;
                        case '>':
                            match = '<';
                            break;
                    }

                    if (match == s.peek()) {
                        s.pop();
                    } else {
                        answer = 0;
                        break;
                    }
                }
            }

            if (s.size() != 0) answer = 0;
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}

/*
#1 1
#2 0
#3 1
#4 1
#5 1
#6 0
#7 0
#8 1
#9 0
#10 1
*/
