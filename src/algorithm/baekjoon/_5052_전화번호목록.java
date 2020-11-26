package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _5052_전화번호목록 {
    private static int T;
    private static int N;
    private static String[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            nums = new String[N];
            for (int i = 0; i < N; i++) {
                nums[i] = br.readLine();
            }

            Arrays.sort(nums);

            System.out.println(nums);
        }
    }
}
