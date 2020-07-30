package algorithm.APSS.dp;

/*
 * 알고리즘 문제해결 전략 - 236p - 합친 LIS - 풀이참고
 * - 틀렸다! -
 *	[오답 원인]
    재귀에 대한 정확한 이해 부족.. "어떤 식으로 만들면 되겠다.."는 생각은 하지만 그걸 어떻게 구현할지가 미흡하다
    재귀 설계시 진행되는 순서를 명확히 해야겠다. 재귀 내 for문 안에 for문을 넣는 방법 말고, for문 밑에 for문을 넣는 것 생각해보자
 *	[다른 사람 코드 참고 -> 내가 취했던 접근 되돌아보기 -> 나는 왜 이 풀이를 떠올리지 못했나?]
    1. 선택지가 여러 개이고 그 중 큰 수를 찾아야 하는 경우, 리턴에 Math.max를 활용하자
     -> 비슷한 패턴의 부분문제로 풀리는 문제는 소스코드마저 비슷하다는 것을 간과했다.
    2. jlis의 첫 idx를 -1로 함으로써 최상단에서 순회해야 하는 상황을 해결 (최상단에서 idx가 0일때 가능한 증가부분수열, 1일때 가능한
        증가 부분수열 ... 이렇게 다 구해야 하므로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _236p_JLIS {
    static int n;
    static int m;
    static int[] aAry = new int[100];
    static int[] bAry = new int[100];
    static int[][] cache = new int[101][101];

    static int jlis(int aIdx, int bIdx) {
        if (cache[aIdx + 1][bIdx + 1] != -1)
            return cache[aIdx + 1][bIdx + 1];

        cache[aIdx + 1][bIdx + 1] = 2;

        long a = aIdx == -1 ? Long.MIN_VALUE : aAry[aIdx];
        long b = bIdx == -1 ? Long.MIN_VALUE : bAry[bIdx];
        long maxElem = Math.max(a, b);

        for (int next = aIdx + 1; next < n; next++) {
            if (aAry[next] > maxElem)
                cache[aIdx+1][bIdx+1] = Math.max(cache[aIdx+1][bIdx+1], 1 + jlis(next, bIdx));
        }
        for (int next = bIdx + 1; next < m; next++) {
            if (bAry[next] > maxElem)
                cache[aIdx+1][bIdx+1] = Math.max(cache[aIdx+1][bIdx+1], 1 + jlis(aIdx, next));
        }
        return cache[aIdx+1][bIdx+1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int C = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < C; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            // 배열이 더 긴값을 a로 결정
            if (st1.countTokens() > st2.countTokens()) {
                int temp = n;
                n = m;
                m = temp;
                for (int i = 0; i < n; i++) {
                    aAry[i] = Integer.parseInt(st2.nextToken());
                }

                for (int i = 0; i < m; i++) {
                    bAry[i] = Integer.parseInt(st1.nextToken());
                }
            } else {
                for (int i = 0; i < n; i++) {
                    aAry[i] = Integer.parseInt(st1.nextToken());
                }

                for (int i = 0; i < m; i++) {
                    bAry[i] = Integer.parseInt(st2.nextToken());
                }
            }

            // 캐시 초기화
            for (int i = 0; i < 101; i++) {
                Arrays.fill(cache[i], -1);
            }
            System.out.println(jlis(-1, -1) - 2); // idx가 -1인 지점부터 카운트했으므로 -1이었을 때 센 값을 뺀 것이 답이다
        }
    }
}
