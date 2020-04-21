package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 240p - 원주율 외우기(PI)
 * 시작~끝: for문의 범위 설정 실수로 엄청 오래걸림
 * - 결국엔 맞았다! -
 *	[다른 해결 방법이 있다면?]
    - 난이도를 반환하는 함수와 난이도의 최소합을 계산하는 함수를 따로 구분해서 구현했다면 더 깔끔했을 것이다
    - 등차수열인지 검사 -> 등차수열&&공차가 1or-1이면 1씩 단조증가,단조감소 -> 난이도 2
                       -> 등차수열&&두 수가 번갈아 등장하면 번갈아등장 -> 난이도 4
                       -> 등차수열&&공차가 1이 아니면 문제에서 요구하는 등차수열 -> 난이도 5
                       -> 아무것도 해당하지 않는다면 난이도 10
 * - 틀렸다! ... 하지만 결국엔 맞았다! -
 *	[오답 원인]
    for문 순회 범위 설정을 너무 막(?) 했다.
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?]
    도저히 풀리지 않아서 답을 확인했다 -> 답을 봐도 답의 풀이가 훨씬 간단하고 직관적이었지만 내 풀이가 틀릴 이유가 없다고 판단했다
    -> 원인이 무엇인지 찾아보았다 -> for문 범위 사이즈를 정확히 고려하지 않았다 ===> 내 풀이 방법이 실수가 잦을 수 있는 풀이 방법이다 반면에 책의 풀이는 범위가 헷갈리니까 아예
    범위를 파라미터로 받아서 그 구간의 난이도가 어떤지를 반환하는 함수를 만들어 푸는 방법을 제시했는데 그 방법이 더 효과적인 것 같다
    * 함수화하는 센스가 필요하다 ... (구간으로 나눠서 푸는 방법도 있다는 것을 알고 넘어가자)
    [좋았던 접근]
    캐시배열의 크기가 10000정도 될 것이라는 추측이 좋았다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _240p_PI {
    static int[] nums;
    static int size;
    static int[] cache = new int[10000];

    static int pi(int idx) {
        // 기저 : 남은 숫자가 1개, 2개이면 잘못 끊어온 것임
        if (idx > size - 3) {
            if (idx == size - 2) return 100000;
            else if (idx == size - 1) return 100000;
            else return 0;
        }

        // 캐시사용
        if (cache[idx] != -1) return cache[idx];

        cache[idx] = 100000;
        int next = idx + 1;

        if (nums[idx] == nums[next]) { //모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
            // 3~5 자리로 끊음
            int cnt = 2;
            for (int i = next + 1; i < size; i++) {
                if (cnt < 5 && nums[idx] == nums[i]) {
                    cnt++;
                    cache[idx] = Math.min(cache[idx], 1 + pi(idx + cnt));
                } else break;
            }
        }

        // 숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
        // 단조 감소
        if (nums[idx] == nums[next] + 1) {
            int cnt = 2;
            for (int i = next + 1, j = 2; i < size /* 이거 사이즈 실수함ㅠㅠ size-1로 해버림 */; i++, j++) {
                if (cnt < 5 && nums[idx] == nums[i] + j) {
                    cnt++;
                    cache[idx] = Math.min(cache[idx], 2 + pi(idx + cnt));
                } else break;
            }
        }
        // 단조 증가
        if (nums[idx] == nums[next] - 1) {
            int cnt = 2;
            for (int i = next + 1, j = 2; i < size; i++, j++) {
                if (cnt < 5 && nums[idx] == nums[i] - j) {
                    cnt++;
                    cache[idx] = Math.min(cache[idx], 2 + pi(idx + cnt));
                } else break;
            }
        }

        if (nums[idx] == nums[next + 1]) { //두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
            // 3개 한번에 처리되므로 한번 실행해주어야함
            int cnt = 2;
            for (int i = idx; i < size - 2; i++) {
                if (cnt < 5 && nums[i] == nums[i + 2]) {
                    cnt++;
                    cache[idx] = Math.min(cache[idx], 4 + pi(idx + cnt));
                } else break;
            }
        }

        if (nums[idx] - nums[next] == nums[next] - nums[next + 1]) { //숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
            // 3개 한번에 처리됨
            int cha = nums[idx] - nums[next];
            int cnt = 2;
            for (int i = next; i < size - 1 /* 이거 하나때문에 몇시간을 날림ㅋㅋㅋㅋㅋㅋ */; i++) {
                /*
                    for문의 범위가 size-2가 아니라 size-1인 이유
                    위에 애들은 검사하는 인덱스가 i와 i+2이 이므로 size-1일 경우 범위를 초과해버린다.
                    하지만 얘는 i와 i+1만을 대조해보므로 size-2로 하면 확인하지 않는 경우가 생겨버린다.
                 */
                if (cnt < 5 && nums[i] - nums[i + 1] == cha) {
                    cnt++;
                    cache[idx] = Math.min(cache[idx], 5 + pi(idx + cnt));
                } else break;
            }
        }

        //그 외의 경우 난이도: 10
        for (int i = 3; i <= 5; i++) {
            cache[idx] = Math.min(cache[idx], 10 + pi(idx + i));
        }

        return cache[idx];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        // 입력반복
        for (int c = 0; c < tc; c++) {
            String input = br.readLine();
            nums = new int[input.length()];
            size = nums.length;
            for (int i = 0; i < size; i++) {
                nums[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
            }
            // 캐시 초기화
            Arrays.fill(cache, -1);
            System.out.println(pi(0));
        }
    }
}
