package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _5658_보물상자비밀번호_NoShift {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String nums = br.readLine();
            nums = nums.concat(nums); // shift 처리하지 않기 위해 자신의 문자열을 한번 더 붙임
            int C = N/4;

            // String비교해도 진법에 상관없이 크기 비교 가능
            TreeSet<String> set = new TreeSet<String>((o1,o2)->{
               return o2.compareTo(o1);
            });

            int c = 0, begin = 0;
            while(c++ < C){ // 회전관련 처리 C번
                // 현 상태에서 각 변의 길이만큼 문자열 비밀번호 추출하여 set에 넣기
                for (int i=begin,j=0 ; j<4 ; i+=C,++j) {
                    set.add(nums.substring(i, i+C));
                }
                begin++;
            }

            // set은 key가 없으므로 특정한 것을 빼낼 수 없음
            int k=0, ans=0;
            for (String s:set) {
                if(++k==K){
                    ans = Integer.parseInt(s, 16); // parseInt시 raidx에 진수를 입력하면 해당 진수로 계산됨
                    break;
                }
            }
            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
