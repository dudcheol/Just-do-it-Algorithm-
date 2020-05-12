package programmers.summerWinterCoding;

import java.util.Arrays;

/**
 * 현재 값을 기준으로 이전값과 이후값을 더해가며 문제를 푼다
 * 처음에 생각은 했지만 안될것같아서 그만뒀는데 그게 정답이었다 ㅠㅠ
 * 대강 짐작해보고 안될거같거니~ 싶으면 바로 다른 방법을 찾는 행동도 조심해야할 듯 싶다
 * 대강 짐작하지말고 손으로 써가면서 짐작했던 것이 안되는 이유를 정확히하고 넘어가는 연습이 필요할듯
 */
public class BuyCookie {
    static int[][] cache = new int[2001][2001];

    static int solution(int[] cookie) {
        int answer = -1;

        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }

        int sum = cookie[0];
        for (int i = 0; i < cookie.length - 1; i++) {
            for (int j = i + 1; j < cookie.length; j++) {
                if (sum == cookie[j]) {
                    answer = Math.max(answer, sum);
                } else if (sum > cookie[j]) {
                    int sum2 = cookie[j]; // 두번째 아들에게 줄 수 있는 합계
                    for (int k = j + 1; k < cookie.length; k++) {
                        sum2 += cookie[k];
                        if (sum2 == sum) {
                            answer = Math.max(answer, sum);
                        } else if (sum2 > sum) {
                            k = cookie.length; // 이 스코프의 반복문 종료
                        }
                    }
                }
                if (sum < cookie[j]) {
                    j = cookie.length;
                } else sum += cookie[j];
            }
            sum = cookie[i + 1];
        }

        return answer == -1 ? 0 : answer;
    }

    public static void main(String[] args) {
//        int[] cookie = {1, 1, 2, 3};
        int[] cookie = {1, 2, 4, 5};
//        int[] cookie = {1, 1, 2, 3, 4, 3};

        System.out.println(solution(cookie));
    }
}
