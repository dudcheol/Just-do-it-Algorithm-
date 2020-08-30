package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class _17135_캐슬디펜스_복습 {
    static int n, m, d;
    static int[][] map;
    static int[] selectedCastle;
    static ArrayList<Enemy> enemies;
    static int answer;

    static class Enemy {
        int y;
        int x;
        boolean isDead;
        int dist;

        public Enemy(int y, int x) {
            this.y = y;
            this.x = x;
            this.isDead = false;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        enemies = new ArrayList<>();
        answer = 0;
        selectedCastle = new int[3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    enemies.add(new Enemy(i, j));
            }
        }
        // 궁수 3명배치 조합으로 해결
        comb(0, 0);
        System.out.println(answer);
    }

    private static void comb(int k, int idx) {
        if (k == 3) { // 궁수는 3명
            // 선택된 캐슬에 궁수가 존재하는 것이므로 리스트의 모든 적이 사라질 때까지 시뮬레이션 진행
            simulate(listDeepCopy(enemies));
            return;
        }
        for (int i = idx; i < m; i++) {
            selectedCastle[k] = i;
            comb(k + 1, idx + 1);
        }
    }

    private static ArrayList<Enemy> listDeepCopy(ArrayList<Enemy> origin) {
        ArrayList<Enemy> ret = new ArrayList<>();
        for (Enemy enemy : origin) {
            ret.add(new Enemy(enemy.y, enemy.x));
        }
        return ret;
    }

    private static void simulate(ArrayList<Enemy> enemies) {
        int killCnt = 0;
        while (!enemies.isEmpty()) {
            // 각 궁수별로 가장 가까운 적 죽이기
            for (int i = 0; i < 3; i++) {
                int curX = selectedCastle[i];
                Collections.sort(enemies, (o1, o2) -> {
                    // 가장 가까운 적 우선
                    int result1 = Math.abs(o1.y - n) + Math.abs(o1.x - curX);
                    int result2 = Math.abs(o2.y - n) + Math.abs(o2.x - curX);
                    if (result1 == result2) {
                        // 거리가 같다면 가장 왼쪽에 있는 적이 앞으로
                        return Integer.compare(o1.x, o2.x);
                    }
                    return Integer.compare(result1, result2);
                });

                Enemy target = enemies.get(0);
                /* collection으로 정렬할 리스트의 수가 2개 미만이면 정렬이 수행되지 않음 */
                int dist = Math.abs(target.y - n) + Math.abs(target.x - curX);
                if (dist <= d) target.isDead = true;
            }

            // 죽인 적 리스트에서 제거 & 모든 적을 아래로 한칸 이동시킴
            for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
                Enemy enemy = iterator.next();
                if (enemy.isDead) {
                    killCnt++;
                    iterator.remove();
                    continue;
                }
                if (++enemy.y >= n) {
                    iterator.remove();
                }
            }
        }
        answer = Math.max(answer, killCnt);
    }
}
