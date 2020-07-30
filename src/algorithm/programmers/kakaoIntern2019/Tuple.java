package algorithm.programmers.kakaoIntern2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 36분
 */
public class Tuple {

    static int[] solution(String s) {
        /**
         * StringTokenizer st = new StringTokenizer(s, "{}");
         * while (st.hasMoreTokens())
         *      System.out.println(st.nextToken());
         *
         * 위와 같은 방법으로 토큰화해서 풀어도 된다. 또는
         *
         * String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
         * replaceAll을 사용하고 정규식을 사용해서 풀어도 된다.
         *
         */

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            if (target == '{') continue;
            // 숫자가 올 경우, '}'가 오기 전까지를 찾는다
            if (target >= '0' && target <= '9') {
                int k = i;
                String onesos = "";
                while (target != '}') {
                    onesos += s.charAt(k);
                    target = s.charAt(++k);
                }
                ArrayList<Integer> onesoList = colonParsing(onesos);
                /**
                 * map의 key를 원소의 수로 두고 푸는것 말고, Collections.sort 사용해서 길이가 짧은 순서로 정렬한 다음 풀어도 됐음
                 */
                map.put(onesoList.size(), onesoList);
                i = k;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            ArrayList<Integer> got = map.get(i + 1);
            for (int g : got) {
                if (ans.contains(g)) continue;
                ans.add(g);
            }
        }

        /* stream 사용하여 리스트를 배열로 변환 */
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    static ArrayList<Integer> colonParsing(String target) {
        StringTokenizer st = new StringTokenizer(target, ",");
        ArrayList<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
//        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
//        String s = "{{20,111},{111}}";

        int[] answer = solution(s);
        for (int ans : answer)
            System.out.print(ans + ", ");
    }
}
