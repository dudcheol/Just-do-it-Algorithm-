package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _15686_ChickenDelivery {
    static int N, M;
    static int answer;
    static ArrayList<int[]> chickenHouse;
    static ArrayList<int[]> house;
    static int[] selected;
    static int size;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // " " 제시해주는 것이 조금이라도 효율적
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chickenHouse = new ArrayList<>();
        house = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        int tmp;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tmp = Integer.parseInt(st.nextToken());
                if (tmp == 2) { // 치킨집임
                    chickenHouse.add(new int[]{i, j});
                } else if (tmp == 1) {
                    house.add(new int[]{i, j});
                }
            }
        }

        // 완전탐색하여 치킨집을 M개만 선택하면서 발생하는 치킨거리를 구한다
        size = chickenHouse.size();
        selected = new int[M];
        comb(0, 0);

        // 도시의 치킨 거리의 최솟값 출력하기
        System.out.println(answer);
    }

    private static void comb(int k, int idx) {
        if (k == M) {
            // 집을 차례로 뽑으면서
            // 선택한 치킨집과의 거리를 구한다
            // 치킨거리를 업데이트하면서 가장 작은 값을 고른다
            int chickenDist = 0; // 도시의 치킨거리
            for (int[] h : house) {
                int nearChicken = Integer.MAX_VALUE; // 현재 집에서 가장 가까운 치킨거리
                for (int i = 0; i < M; i++) {
                    int[] ch = chickenHouse.get(selected[i]);
                    int dist = Math.abs(h[0] - ch[0]) + Math.abs(h[1] - ch[1]);
                    if (nearChicken > dist) nearChicken = dist;
                }
                chickenDist += nearChicken;
            }
            if (answer > chickenDist) answer = chickenDist;
            return;
        }

        for (int i = idx; i < size; i++) {
            selected[k] = i;
            comb(k + 1, i + 1);
        }
    }
}
