package algorithm.swea.D5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1812_수정이의타일자르기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            long M = Integer.parseInt(st.nextToken());
            long m = (long) Math.pow(M,2);
            int[] tiles = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                tiles[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(tiles);

            long tArea = 0L;
            long tLen = 0L;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                long len = (long) Math.pow(2, tiles[i]);
                long area = (long) Math.pow(len,2);
                tLen += len;
                tArea += area;
                if (tLen >= M){ // 상점타일보다 길이가 커지면
                    if (tArea > m){ // 넓이까지 넘어간다면
                        cnt++; // 타일 수 증가
                        tArea = area;
                        tLen = len;
                    } else if (tArea == m){
                        cnt++;
                        tArea = 0;
                        tLen = 0;
                    }
                }
            }
            if(tArea>0) cnt++;

            sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
