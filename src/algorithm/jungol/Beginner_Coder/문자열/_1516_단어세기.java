package algorithm.jungol.Beginner_Coder.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class _1516_단어세기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();
            if (input.equals("END")) break;
            StringTokenizer st = new StringTokenizer(input);

            TreeMap<String,Integer> map = new TreeMap<String,Integer>((o1,o2)->{
                return o1.compareTo(o2);
            });

            while (st.hasMoreTokens()){
                String cur = st.nextToken();
                map.put(cur, map.getOrDefault(cur, 0)+1);
            }

            for(Map.Entry<String, Integer> m : map.entrySet()){
                sb.append(m.getKey()).append(" : ").append(m.getValue()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
