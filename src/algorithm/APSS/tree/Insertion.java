package algorithm.APSS.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Insertion {
    static List<Integer> solution(int n, int[] move) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++)
            list.add(i);

        // move의 마지막부터 순회
        for (int i = n - 1; i >= 0; i--) {
            // i번째 자리에서 moved 만큼 앞에 있는 수가 원래 i번째에 있었던 수이므로 위치를 바꾼다
            int moved = move[i];
            int target = list.get(i - moved);
            list.remove(i - moved);
            list.add(i, target);
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OutputStream out;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] move = new int[n];
            for (int i = 0; i < n; i++)
                move[i] = Integer.parseInt(st.nextToken());

            List<Integer> answers = solution(n, move);
            String answer = "";
            for (int a : answers) {
                answer += (a + " ");
            }
            if (tc != (TC - 1)) answer += "\n";
            bw.write(answer);
        }
        bw.flush();
        bw.close();
    }
}
