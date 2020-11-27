package algorithm.baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1025_제곱수찾기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

//        int[][] nums = new int[N][M];
        char[][] nums = new char[N][M];
        for (int i = 0; i < N; i++) {
//            char[] input = br.readLine().toCharArray();
            nums[i] = br.readLine().toCharArray();
//            for (int j = 0; j < M; j++) {
//                nums[i][j] = input[j]-'0';
//            }
        }

        int max = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int di = -N; di < N; di++) { // i의 공차
                    for (int dj = -M; dj < M; dj++) { // j의 공차
                        if (di == 0 && dj == 0) continue;

                        StringBuilder sb = new StringBuilder();
                        int ni = i, nj = j;
                        while (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            sb.append(nums[ni][nj]);
                            double cur = Math.sqrt(Integer.parseInt(sb.toString()));
                            if(cur-(int)cur == 0) max=Math.max(max, (int)Math.pow(cur,2));
                            ni += di;
                            nj += dj;
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}
