package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Flatten {
    static int[] map = new int[100];
    static int MAXIDX = 99;
    static int dump;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            dump = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++)
                map[i] = Integer.parseInt(st.nextToken());

            // 실제 덤프를 한 숫자를 저장하는 변수를 만들고
            // 그 변수와 dump를 비교해서 반복문을 빠져나오도록 설계하는게 더 나을듯 싶음.
            for (int i = 0; i <= dump; i++) {
                Arrays.parallelSort(map);
                if ((answer = map[MAXIDX] - map[0]) <= 1) break;
                map[0]++;
                map[MAXIDX]--;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
