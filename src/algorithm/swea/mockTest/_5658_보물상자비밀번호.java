package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _5658_보물상자비밀번호 {
    private static int[] hexToInt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        hexToInt = new int['F'+1];
        for (int i = 0; i<10;i++)
            hexToInt['1'+i] = 1+i;
        for (int i = 0; i < 6; i++)
            hexToInt['A'+i] = 10+i;


        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String num = br.readLine();

            int len = N/4;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int d = 0; d < len; d++) {
                if(d!=0){
                    String sub = num.substring(num.length()-1);
                    num = num.substring(0, num.length()-1);
                    num = sub.concat(num);
                }
                for (int k = 0; k < 4; k++) {
                    Integer offer = convert(num.substring(len*k, len*k+len));
                    if(!pq.contains(offer))
                        pq.offer(offer);
                }
            }
            int answer = 0;
            while (K-- != 0) answer = pq.poll();
            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    private static Integer convert(String hex) {
        int len = hex.length();
        int idx = 0;
        int val = 0;
        for (int i = len-1; i >= 0; i--) {
            val += (hexToInt[hex.charAt(i)] * Math.pow(16, idx++));
        }
        return val;
    }
}
