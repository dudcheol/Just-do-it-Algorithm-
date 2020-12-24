package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _3699_변장 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            int N = Integer.parseInt(br.readLine());

            HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String val = st.nextToken();
                String key = st.nextToken();
                if (map.containsKey(key)){
                    map.get(key).add(val);
                } else {
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(val);
                    map.put(key, tmp);
                }
            }

            int answer = 1;
            for(ArrayList<String> list : map.values()){
                answer *= list.size()+1;
            }
            sb.append(answer-1).append('\n');
        }
        System.out.print(sb);
    }
}
