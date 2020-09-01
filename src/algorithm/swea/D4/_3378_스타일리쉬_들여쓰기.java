package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3378_스타일리쉬_들여쓰기 {
    static int p, q;
    static String[] master;
    static String[] me;
    static int r, c, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            master = new String[p];
            me = new String[q];
            for (int i = 0; i < p; i++) {
                master[i] = br.readLine();
            }
            for (int i = 0; i < q; i++) {
                me[i] = br.readLine();
            }

            // 마스터 코드를 통해 r c s 구하기
            readMaster();
        }
    }

    private static void readMaster() {
        String preLine = master[0];
        for (int i = 1; i < p; i++) {
            String curLine = master[i];
            // 이전 라인에서 괄호별 사용횟수를 이용해서 rcs 찾기
            // 현재 라인에서 .의 갯수를 찾는다
            int findIdx = 0;
            for (int j=0;j<curLine.length();j++){
                if (curLine.charAt(j) != '.'){
                    findIdx = j;
                    break;
                }
            }
//            if (findIdx == 0)
        }
    }
}
