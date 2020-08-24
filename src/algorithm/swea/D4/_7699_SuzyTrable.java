package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1:20~1:52
 */
public class _7699_SuzyTrable {
    static int R, C;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " "); // delim을 명시하는 것이 좀 더 시간적인 효율을 높일 수 있음
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][]; // new char[R][C]로 C만큼의 배열 생성을 생략하므로서 시간효율을 높일 수 있다
            visited = new boolean['Z' + 1];
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray(); // toCharArray는 내부적으로 System.arraycopy(~)를 사용하여 배열을 통째로 복사하므로 빠르다
            }

            /* 절차 작성하기 */
            // 시작지점
            visited[map[0][0]] = true;
            // 방문체크
            answer = 1;
            // dfs 출발
            dfs(0, 0, 1);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        } // end of for test case
        System.out.print(sb);
    } // end of main

    /**
     * dfs
     *
     * @param r    r행 좌표
     * @param c    c열 좌표
     * @param step 현재까지 진행한 칸 수
     */
    private static void dfs(int r, int c, int step) {
        if (answer == 26) return; // 이것이 핵심 "가지치기"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (answer < step) answer = step; // 정답 최댓값 업데이트

        // 펼쳐서 푸는 것이 dy dx보다 시간이 적게듦
        // +@ bit 연산자 사용하여 visited 구현하기
        if (0 <= r - 1 && !visited[map[r - 1][c]]) { // 상
            visited[map[r - 1][c]] = true; // 방문체크
            dfs(r - 1, c, step + 1);
            visited[map[r - 1][c]] = false; // 방문체크 해제
        }

        if (r + 1 < R && !visited[map[r - 1][c]]) { // 하
            visited[map[r + 1][c]] = true; // 방문체크
            dfs(r + 1, c, step + 1);
            visited[map[r + 1][c]] = false; // 방문체크 해제
        }

        if (0 <= c - 1 && !visited[map[r - 1][c]]) { // 좌
            visited[map[r][c - 1]] = true; // 방문체크
            dfs(r, c - 1, step + 1);
            visited[map[r][c - 1]] = false; // 방문체크 해제
        }

        if (c + 1 < R && !visited[map[r - 1][c]]) { // 하
            visited[map[r][c + 1]] = true; // 방문체크
            dfs(r, c + 1, step + 1);
            visited[map[r][c + 1]] = false; // 방문체크 해제
        }
    }

} // end of class














