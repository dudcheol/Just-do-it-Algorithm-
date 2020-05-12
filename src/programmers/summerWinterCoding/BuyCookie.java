package programmers.summerWinterCoding;

public class BuyCookie {

    static int solution(int[] cookie) {
        int answer = -1;

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
