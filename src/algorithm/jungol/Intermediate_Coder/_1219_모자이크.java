package algorithm.jungol.Intermediate_Coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1219_모자이크 {
    private static int R, C, papaerCnt, wrongCnt;
    private static int min;
    private static int[][] wrong;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        papaerCnt = Integer.parseInt(br.readLine());
        wrongCnt = Integer.parseInt(br.readLine());
        wrong = new int[wrongCnt][2];
        for (int i = 0; i < wrongCnt; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            wrong[i][0] = Integer.parseInt(st.nextToken());
            wrong[i][1] = Integer.parseInt(st.nextToken());
        }
        // wrong 을 열값을 기준으로 정렬
        Arrays.sort(wrong, (o1,o2)->{
            return Integer.compare(o1[1],o2[1]);
        });

        min = C;
        binarySearch(1, C);
        System.out.println(min);
    }

    private static void binarySearch(int low, int high) {
        int mid = (low + high) / 2;

        if (low>high) return;

        // 기저
        if (isOk(mid)) {
            min = Math.min(min, mid);
            binarySearch(low, mid - 1);
        } else {
            binarySearch(mid + 1, high);
        }
    }

    // 주어진 색종이로 모두 덮을 수 있는지 확인
    private static boolean isOk(int len) {
        int paper = papaerCnt;

        // 열값을 이용해서 len X len 커버하기
        int range = wrong[0][1]+len-1;
        paper--;
        for (int i = 1; i < wrongCnt; i++) {
            if (wrong[i][0] > len) return false; // 색종이가 세로높이를 커버할 수 없으면 주어진 색종이는 사용 불가

            if (range < wrong[i][1]){
                if (paper==0) return false;
                paper--; // 이전 종이로 커버할 수 없는 영역에 왔으므로 새로운 종이 사용
                range = wrong[i][1]+len-1; // 새로운 종이로 커버할 수 있는 영역 갱신
            }
        }
        return true;
    }
}
