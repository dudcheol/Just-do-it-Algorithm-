package algorithm.jungol.Beginner_Coder.여러가지;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _3427_볼_모으기 {
    private static int N;
    private static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        int min = Integer.MAX_VALUE;
        // 왼->오, R
        boolean countable = false;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (!countable && arr[i] == 'B') countable = true;
            if (countable && arr[i] == 'R') cnt++;
        }
        min = Math.min(min, cnt);

        // 왼->오, B
        countable = false;
        cnt = 0;
        for (int i = 0; i < N; i++) {
            if (!countable && arr[i] == 'R') countable = true;
            if (countable && arr[i] == 'B') cnt++;
        }
        min = Math.min(min, cnt);

        // 오->왼, R
        countable = false;
        cnt = 0;
        for (int i = N-1; i >= 0; i--) {
            if (!countable && arr[i] == 'B') countable = true;
            if (countable && arr[i] == 'R') cnt++;
        }
        min = Math.min(min, cnt);

        // 오->왼, B
        countable = false;
        cnt = 0;
        for (int i = N-1; i >= 0; i--) {
            if (!countable && arr[i] == 'R') countable = true;
            if (countable && arr[i] == 'B') cnt++;
        }
        min = Math.min(min, cnt);

        System.out.println(min);
    }
}
