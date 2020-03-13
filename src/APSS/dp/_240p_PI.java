package APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 240p - 원주율 외우기(PI)
 * 시작~끝:
 * 걸린시간:
 * - 맞았다! -
 *	[간단한 해법]

 *	[어떤  방식으로 접근했나?]

 *	[해법을 찾는 데 결정적인 깨달음]

 *	[다른 해결 방법이 있다면?]

 * - 틀렸다! -
 *	[오답 원인]

 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?]

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
            for (int i = next; i < size - 2; i++) {
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
