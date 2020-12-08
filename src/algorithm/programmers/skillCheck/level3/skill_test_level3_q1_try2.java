package algorithm.programmers.skillCheck.level3;

import java.util.Arrays;

public class skill_test_level3_q1_try2 {
    static int min;
    static int[] visited;

    public static int solution(int N, int number) {
        min = Integer.MAX_VALUE;
        visited = new int[32001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        subset(N, 1 ,N,number);
        return min==Integer.MAX_VALUE?-1:min;
    }

    private static void subset(int res, int cnt, int n, int number) {
        if (res>32000 || visited[res] < cnt) return;
        if (res == number){
            min = Math.min(min, cnt);
            return;
        }
        if (res > number*10) return;
        visited[res] = cnt;
        // 5덧붙힘
        subset(Integer.parseInt(res+""+n), cnt+1, n, number);
        // 5/5
        subset(res/n, cnt+1, n, number);
        // +5
        subset(res+n, cnt+1, n, number);
    }

    public static void main(String[] args) {
//        int N = 5;
//        int number = 12;
//        int N = 2;
//        int number = 11;
        int N = 5;
        int number = 31168;
        System.out.println(solution(N, number));
    }
}
