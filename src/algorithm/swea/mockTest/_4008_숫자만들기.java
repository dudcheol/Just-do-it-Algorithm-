package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4008_숫자만들기 {
    private static int N, opCnt[], nums[], max, min, selected[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            opCnt = new int[4];
            nums = new int[N];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            selected = new int[N-1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                opCnt[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            perm(0);
            sb.append('#').append(test_case).append(' ').append(max-min).append('\n');
        }
        System.out.print(sb);
    }

    private static void perm(int k) {
        if (k==N-1){
            go();
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(opCnt[i]==0)continue;
            opCnt[i]--;
            selected[k] = i;
            perm(k+1);
            opCnt[i]++;
        }
    }

    private static void go() {
        int res = nums[0];
        for (int i = 0; i < N-1; i++) {
            res = calc(res, selected[i] ,nums[i+1]);
        }
        max = Math.max(max, res);
        min = Math.min(min, res);
    }

    private static int calc(int num1, int op, int num2) {
        switch (op){
            case 0:
                return num1+num2;
            case 1:
                return num1-num2;
            case 2:
                return num1*num2;
            case 3:
                return num1/num2;
        }
        return -1;
    }
}
