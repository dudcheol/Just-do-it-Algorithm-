package algorithm.baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1107_리모컨 {
    static boolean[] visited;
    static int len;
    static int PAGE;
    static int min;
    private static int M;
    private static ArrayList<Integer> use;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PAGE = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[10];
        Arrays.fill(visited, true);
        if(M>0) { // 고장난 리모컨 버튼이 없는 경우, 3번째 줄 입력이 없어서 런타임에러 발생
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                visited[Integer.parseInt(st.nextToken())] = false;
            }
        }
        use = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            if (visited[i]) use.add(i);
        }

        len = Integer.toString(PAGE).length() + 1;
        min = Integer.MAX_VALUE;

        if (PAGE == 100) { // 움직일 필요 없음
            System.out.println(0);
            return;
        }
        // 사용 가능한 버튼 입력으로 PAGE도달 가능 -> 필요없음. subset에 이미 존재하는 경우임.
//        String pages = Integer.toString(PAGE);
//        boolean isOk = true;
//        for (int i = 0; i < pages.length(); i++) {
//            if(!visited[pages.charAt(i)-'0']){
//                isOk = false;
//                break;
//            }
//        }
//        if(isOk) {
//            min = Math.min(min, pages.length());
//        }
        subset(0, 0, 0);
        min = Math.min(min, Math.abs(PAGE - 100)); // 100부터 PAGE까지 +,-로만 도달했을때
        System.out.println(min);
        return;
    }

    private static void subset(int k, int num, int pos) {
        if (k == len) {
//            System.out.println(num);
            if (pos == 0) return; // 리모컨 숫자 버튼을 한번도 누르지 않음
            min = Math.min(min, Math.abs(PAGE - num) + pos);
            return;
        }
        subset(k + 1, num, pos);

        for (int i = 0; i < use.size(); i++) {
            subset(k + 1, num + use.get(i) * (int)Math.pow(10, pos), pos+1);
        }
    }
}
